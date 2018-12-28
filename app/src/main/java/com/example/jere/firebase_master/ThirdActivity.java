package com.example.jere.firebase_master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * @author jere
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        // for test setCurrentScreen user engagement
        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, "lastActivity", null);
    }
}
