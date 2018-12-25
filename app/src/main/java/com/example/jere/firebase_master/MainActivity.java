package com.example.jere.firebase_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button leftButton;
    private Button centerButton;
    private Button rightButton;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        clickEvent();
    }

    private void findViewId() {
        leftButton = findViewById(R.id.left_button);
        centerButton = findViewById(R.id.center_button);
        rightButton = findViewById(R.id.right_button);
    }

    private void clickEvent() {
        leftButton.setOnClickListener(this);
        centerButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
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
            default:
                Toast.makeText(this, "right_button", Toast.LENGTH_SHORT).show();
                mFirebaseAnalytics.logEvent("right_button", bundle);
                break;
        }
    }
}
