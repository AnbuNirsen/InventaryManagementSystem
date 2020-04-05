package com.example.inventarymanagementsystem.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.inventarymanagementsystem.R;
import com.example.inventarymanagementsystem.room.entities.Catagory;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.revealedittext.tools.UITools;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.ISpinnerSelectedView;

/**
 * Created by michael on 1/8/17.
 */

public class ProductListAdapter extends BaseAdapter implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private ArrayList<String> mBackupCatageryList;
    private ArrayList<String> catageryList;
    private StringFilter mStringFilter = new StringFilter();

    public ProductListAdapter(Context context, ArrayList<String> catageryList) {
        mContext = context;
        this.catageryList = catageryList;
        mBackupCatageryList = catageryList;
    }

    @Override
    public int getCount() {
        return catageryList == null ? 0 : catageryList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (catageryList != null && position > 0)
            return catageryList.get(position - 1);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        if (catageryList == null && position > 0)
            return catageryList.get(position).hashCode();
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            letters.setImageDrawable(getTextDrawable(catageryList.get(position-1)));
            dispalyName.setText(catageryList.get(position-1));
        }
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            letters.setImageDrawable(getTextDrawable(catageryList.get(position-1)));
            dispalyName.setText(catageryList.get(position-1));
        }
        return view;
    }

    @Override
    public View getNoSelectionView() {
        View view = View.inflate(mContext, R.layout.view_list_no_selection_item, null);
        return view;
    }

    private TextDrawable getTextDrawable(String displayName) {
        TextDrawable drawable = null;
        if (!TextUtils.isEmpty(displayName)) {
            int color2 = ColorGenerator.MATERIAL.getColor(displayName);
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .textColor(Color.WHITE)
                    .toUpperCase()
                    .endConfig()
                    .round()
                    .build(displayName.substring(0, 1), color2);
        } else {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .endConfig()
                    .round()
                    .build("?", Color.GRAY);
        }
        return drawable;
    }

    @Override
    public Filter getFilter() {
        return mStringFilter;
    }

    public class StringFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mBackupCatageryList.size();
                filterResults.values = mBackupCatageryList;
                return filterResults;
            }
            final ArrayList<String> filterStrings = new ArrayList<>();
            for (String catagory : mBackupCatageryList) {
                if (catagory.toLowerCase().contains(constraint)) {
                    filterStrings.add(catagory);
                }
            }
            filterResults.count = filterStrings.size();
            filterResults.values = filterStrings;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            catageryList = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    }

    private class ItemView {
        public ImageView mImageView;
        public TextView mTextView;
    }

    public enum ItemViewType {
        ITEM, NO_SELECTION_ITEM;
    }
}