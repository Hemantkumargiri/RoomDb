package com.example.pip.model;



import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<Data> fromJsonString(String value) {
        if (value == null) {
            return null;
        }
        Type listType = new TypeToken<List<Data>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    
    @TypeConverter
    public static String toJsonString(List<Data> list) {
        if (list == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }
        
}
