package com.gitrepos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class Application extends android.app.Application {

    private static Context sApplicationContext;
    private static Activity activity;

    @Override
    public void onCreate()
    {
        super.onCreate();
        sApplicationContext = this.getApplicationContext();
        activity= (Activity) getActivity();

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(GRConstants.PREF_NAME, Context.MODE_PRIVATE);
        GRPreferenceManager.initializePreferenceManager(sharedPreferences);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getContext()
    {
        return sApplicationContext;
    }
    public static Context  getActivity()
    {
        return activity;
    }
}
