package com.example.inventarymanagementsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.base.BaseActivity;
import com.example.inventarymanagementsystem.room.entities.UserHistory;
import com.example.inventarymanagementsystem.room.repository.InventoryRepo;
import com.example.inventarymanagementsystem.ui.adapter.PastHistoryAdapter;
import com.example.inventarymanagementsystem.ui.models.PastHistory;
import com.example.inventarymanagementsystem.ui.models.PastHistoryHeading;
import com.example.inventarymanagementsystem.ui.models.PastHistoryProductItem;
import com.example.inventarymanagementsystem.utils.Constants;
import com.example.inventarymanagementsystem.utils.SharedPrefManager;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.inventarymanagementsystem.utils.Constants.PAST_HISTORY;

public class PastHistoryScreen extends BaseActivity {
    private RecyclerView rv_past_history;
    private PastHistoryAdapter pastHistoryAdapter;
    private List<PastHistory> pastHistoryList = new ArrayList<>();
    private List<UserHistory> pastUserHistoryList = new ArrayList<>();
    private InventoryRepo inventoryRepo;
    private SharedPrefManager sharedPrefManager;
    private Button history_button;
    HashMap<String,List<PastHistoryProductItem>> pastHistory = new HashMap<>();

    private static final String TAG = "PastHistoryScreen";
    private static final int STORAGE_PERMISSION_CODE = 101;

    private int count =0;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_history_screen);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv_past_history = findViewById(R.id.rv_past_history);
//        history_button = findViewById(R.id.history_button);
        inventoryRepo = new InventoryRepo(this);


        sharedPrefManager = new SharedPrefManager(this);

        if(getIntent().getSerializableExtra(PAST_HISTORY)!=null){
            pastHistoryList = (List<PastHistory>) getIntent().getSerializableExtra(PAST_HISTORY);
            pastHistoryAdapter = new PastHistoryAdapter(pastHistoryList);
            rv_past_history.setAdapter(pastHistoryAdapter);
        }else{
            inventoryRepo.getHistory(sharedPrefManager.getString(Constants.USER_PHNO));
            compositeDisposable.add(
                inventoryRepo.userHistoryListObserver.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<UserHistory>>() {
                    @Override
                    public void accept(List<UserHistory> userHistories) throws Exception {
                        Set<String> offsetDate = new HashSet<>();

                        for(UserHistory userHistory: userHistories){
                            String dateValue = userHistory.getBillDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            offsetDate.add(dateValue);
                        }


                        for(String offsetDateTime:offsetDate){
                            List<PastHistoryProductItem> pastHistoryProductItems = new ArrayList<>();
                            for(UserHistory userHistory:userHistories){
                                String dateValue = userHistory.getBillDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                if(dateValue.equals(offsetDateTime)){
                                    pastHistoryProductItems.add(new PastHistoryProductItem(userHistory.getProductName(),userHistory.getProductQuantity(),userHistory.getProductPrice(),userHistory.getProductId(),userHistory.getProductQuantity(),Integer.parseInt(userHistory.getTotalPrice())));
                                }
                            }
                            pastHistory.put(offsetDateTime,pastHistoryProductItems);
                        }


                        for (Map.Entry<String,List<PastHistoryProductItem>> entry : pastHistory.entrySet()) {
                            pastHistoryList.add(new PastHistory(entry.getKey(),sharedPrefManager.getString(Constants.USER_NAME),entry.getValue()));
                        }



                        pastHistoryAdapter = new PastHistoryAdapter(pastHistoryList);
                        rv_past_history.setAdapter(pastHistoryAdapter);

                    }
                })
            );
            initListDatas();
        }




//        initListDatas();

//        history_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inventoryRepo.updateUserHistory(pastUserHistoryList.get(count));
//            }
//        });




    }
    public void initListDatas(){
        pastUserHistoryList.add(new UserHistory(1,"9952102045",OffsetDateTime.parse("2020-04-05T21:44:32.102+05:30"),"Ashok","Five Star","3","20","Candies","CANDY101","101","60",OffsetDateTime.parse("2020-04-05T21:42:14.039+05:30"),OffsetDateTime.parse("2020-04-23T21:42:14.038+05:30")));
    }


    public void createQrCode(){
        if(pastHistory.size()>0){
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                //Convert Map to JSON
                String text = mapper.writeValueAsString(pastHistoryList); // Whatever you need to encode in the QR code
                //Print JSON output
                System.out.println(text);

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    shareImageUri(saveImageExternal(bitmap));
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
            catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void shareImageUri(Uri uri){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT,"Please Scan the QR to know the customer History!");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/png");
        startActivity(intent);
    }

    private Uri saveImageExternal(Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), image, "shareImage", null);
        return Uri.parse(path);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.qr_code_generator:
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(PastHistoryScreen.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(PastHistoryScreen.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(PastHistoryScreen.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(PastHistoryScreen.this, new String[]{permission}, requestCode);
            }
        } else {
            createQrCode();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Write external Storage
                case STORAGE_PERMISSION_CODE:
                    createQrCode();
                    break;
            }
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
