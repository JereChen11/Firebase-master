package com.example.jere.firebase_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * @author jere
 */
public class SecondActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(getApplication());
        Log.d("AnalyticLogger", "trackingId: " + "UA-132877157-1");
        mTracker = analytics.newTracker("UA-132877157-1");

        mTracker.setScreenName("gcm-SecondActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Track Screens", "onResume: " + "SecondActivity");
    }
}
