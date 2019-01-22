package com.example.jere.firebase_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button leftButton;
    private Button centerButton;
    private Button rightButton;
    private Button nextButton;
    private Button crashButton;
    private static Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(getApplication());
        Log.d("AnalyticLogger", "trackingId: " + "UA-132877157-1");
        mTracker = analytics.newTracker("UA-132877157-1");

        mTracker.setScreenName("gcm-MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        // acquire device token
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String deviceToken = instanceIdResult.getToken();
                Log.d("deviceToken", "onCreate: " + deviceToken);
                // Do whatever you want with your token now
                // i.e. store it on SharedPreferences or DB
                // or directly send it to server
            }
        });

        findViewId();
        clickEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Track Screens", "onResume: " + "MainActivity");

    }

    private void findViewId() {
        leftButton = findViewById(R.id.left_button);
        centerButton = findViewById(R.id.center_button);
        rightButton = findViewById(R.id.right_button);
        nextButton = findViewById(R.id.next_button);
        crashButton = findViewById(R.id.crash_button);
    }

    private void clickEvent() {
        leftButton.setOnClickListener(this);
        centerButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        crashButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        String name = "";
        switch(v.getId()) {
            case R.id.left_button:
                Toast.makeText(this, "left_button", Toast.LENGTH_SHORT).show();
                name = "left_button";
                break;
            case R.id.center_button:
                Toast.makeText(this, "center_button", Toast.LENGTH_SHORT).show();
                name = "center_button";
                break;
            case R.id.right_button:
                Toast.makeText(this, "right_button", Toast.LENGTH_SHORT).show();
                name = "right_button";
                break;
            case R.id.next_button:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                name = "next_button";
                break;
            case R.id.crash_button:
                Toast.makeText(this, "Force a crash", Toast.LENGTH_SHORT).show();
                Crashlytics.getInstance().crash(); // Force a crash
                name = "force_crash";
                break;
            default:
                break;
        }
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("click-event")
                .setAction("gcm-event-" + name)
                .setLabel(name)
                .build());
        Log.d("gcm_analytic", "onClick: " + name);
    }
}
