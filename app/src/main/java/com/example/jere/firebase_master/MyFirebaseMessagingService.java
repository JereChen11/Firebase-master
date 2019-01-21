package com.example.jere.firebase_master;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * @author jere
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MessagingService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "onNewToken: " + s);

        // Store device Token.
        storeToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            //handle the data message here
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            //getting the title and the body
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

        }

    }

    public void storeToken(String token) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", token);
        editor.apply();
    }

}
