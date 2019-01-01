package com.example.jere.firebase_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button leftButton;
    private Button centerButton;
    private Button rightButton;
    private Button nextButton;
    private Button crashButton;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseInstanceId.getInstance().getInstanceId();
//        FirebaseInstanceId.getInstance().getToken();

        findViewId();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        clickEvent();

//        FirebaseMessaging.getInstance().subscribeToTopic("news")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = getString(R.string.msg_subscribed);
//                        if (!task.isSuccessful()) {
//                            msg = getString(R.string.msg_subscribe_failed);
//                        }
//                        Log.d("msg: ", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAnalytics.setCurrentScreen(this, "MainActivity", null );
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
        switch(v.getId()) {
            case R.id.left_button:
                Toast.makeText(this, "left_button", Toast.LENGTH_SHORT).show();
                mFirebaseAnalytics.logEvent("left_button", bundle);
                break;
            case R.id.center_button:
                Toast.makeText(this, "center_button", Toast.LENGTH_SHORT).show();
                mFirebaseAnalytics.logEvent("center_button", bundle);
                break;
            case R.id.right_button:
                Toast.makeText(this, "right_button", Toast.LENGTH_SHORT).show();
                mFirebaseAnalytics.logEvent("right_button", bundle);
                break;
            case R.id.next_button:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.crash_button:
                Toast.makeText(this, "Force a crash", Toast.LENGTH_SHORT).show();
                Crashlytics.getInstance().crash(); // Force a crash
                break;
            default:
                break;
        }
    }
}
