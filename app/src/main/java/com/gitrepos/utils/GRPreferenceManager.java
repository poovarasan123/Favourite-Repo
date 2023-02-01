package com.gitrepos.utils;

import android.content.SharedPreferences;

import com.gitrepos.model.RepositoryModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GRPreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    public static void initializePreferenceManager(SharedPreferences preferences)
    {
        sharedPreferences = preferences;
        gson = new Gson();
    }

    public static void setString(String key, String value){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        if ( value == null){
            editor.putString(key,"").apply();
        }else{
            editor.putString(key, value).apply();
        }
    }

    public static String getString(String key, String defultValue){
        return sharedPreferences.getString(key, defultValue);
    }

    public static void setInteger(String key, int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key, value).apply();

    }

    public static Integer getInteger(String key, int value){
        return sharedPreferences.getInt(key, value);
    }

    public static void setBoolean(String key, boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
    }

    public static boolean getBoolean(String key, boolean value){
        return sharedPreferences.getBoolean(key, value);
    }

    public static void setArrayList(String key, List<RepositoryModel> repositoryModels){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, gson.toJson(repositoryModels));
        editor.apply();
    }

    public static ArrayList<RepositoryModel> getArraylist(String key){
        String repositories = sharedPreferences.getString(key, null);
        Type taskListType = new TypeToken<ArrayList<RepositoryModel>>(){}.getType();
        ArrayList<RepositoryModel> task = gson.fromJson(repositories, taskListType);
        if (task!=null)return task;
        else return new ArrayList<>();
    }

    public static void clearAllPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
