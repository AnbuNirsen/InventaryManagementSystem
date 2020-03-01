package com.example.inventarymanagementsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static String SHARED_PREF_NAME = "INVNTARY_PREF_NAME";

    public SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void putString(String key,String value){
       editor.putString(key,value);
       editor.commit();
    }

    public void putBoolean(String key,Boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }
    public String getString(String key){
        return sharedPreferences.getString(key,null);
    }
}
