package com.example.inventarymanagementsystem.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.time.OffsetDateTime;

public class OffsetCreator {
    public static OffsetDateTime offsetCreatormethod(String offsetString){
        return OffsetDateTime.parse(offsetString);
    }


}
