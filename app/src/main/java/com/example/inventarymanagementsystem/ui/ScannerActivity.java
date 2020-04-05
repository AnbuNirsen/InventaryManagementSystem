package com.example.inventarymanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.inventarymanagementsystem.MainActivity;
import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.ui.models.PastHistory;
import com.example.inventarymanagementsystem.ui.models.PastHistoryProductItem;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.inventarymanagementsystem.utils.Constants.PAST_HISTORY;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        List<PastHistory> pastHistoryList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(rawResult.getText());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String dateString = jsonObject.getString("date");
                String nameString = jsonObject.getString("name");
                List<PastHistoryProductItem> myPastHistoryList = new ArrayList<>();
                JSONArray pastHistoryArray = jsonObject.getJSONArray("pastHistoryList");
                for(int j=0;j<pastHistoryArray.length();j++){
                    JSONObject pastHistoryObject = pastHistoryArray.getJSONObject(j);
                    String productId = pastHistoryObject.getString("productId");
                    String productName = pastHistoryObject.getString("productName");
                    String productPrice = pastHistoryObject.getString("productPrice");
                    String productQnty = pastHistoryObject.getString("productQnty");
                    String qntyType = pastHistoryObject.getString("qntyType");
                    int totalAmount = pastHistoryObject.getInt("totalAmount");
                    myPastHistoryList.add(new PastHistoryProductItem(productName,productQnty,productPrice,productId,qntyType,totalAmount));
                }
                pastHistoryList.add(new PastHistory(dateString,nameString,myPastHistoryList));
            }
            if(pastHistoryList.size()>0){
                Log.d("<====>",pastHistoryList.toString());
                Intent intent = new Intent(this,PastHistoryScreen.class);
                intent.putExtra(PAST_HISTORY, (Serializable) pastHistoryList);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}
