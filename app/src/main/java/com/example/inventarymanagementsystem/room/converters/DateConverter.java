package com.example.inventarymanagementsystem.room.converters;

import androidx.room.TypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;


public class DateConverter {
    public DateConverter() {
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public static OffsetDateTime toOffsetDateTime(String value){
        return formatter.parse(value, OffsetDateTime::from);
    }

    @TypeConverter
    public static String stringtoOffsetDateTime(OffsetDateTime date){
        return formatter.format(date);
    }

}
