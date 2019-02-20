package com.example.jere.firebase_master;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author jere
 */
public class MyFirebaseUtils {

    public static final String DEVICE_TOKEN = "DEVICE_TOKEN";

    public static void storeDeviceToken(Context context, String token) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(DEVICE_TOKEN, token);
        editor.apply();
    }

    public static String getDeviceToken(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(DEVICE_TOKEN, null);
    }

}
