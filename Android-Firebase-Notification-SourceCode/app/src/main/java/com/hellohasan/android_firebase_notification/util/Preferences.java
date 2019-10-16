package com.hellohasan.android_firebase_notification.util;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences {

    public static final String VERSION_CODE = "version_code";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static Preferences preferences = new Preferences();

    private Preferences(){}


    public static Preferences getInstance(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPreferences_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return preferences;
    }


    public long getVersionCode(){
        return sharedPreferences.getLong(VERSION_CODE, -1);
    }

    public void setVersionCode(long versionCode){
        editor.putLong(VERSION_CODE, versionCode);
        editor.apply();
        editor.commit();
    }

}
