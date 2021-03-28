package edu.crosscutting.utils;

import com.google.gson.Gson;

public abstract class Json {

    public static String toString(Object object) {
        return (new Gson()).toJson(object);
    }

    public static <T> T fromString(String jsonString, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, clazz);
    }


}
