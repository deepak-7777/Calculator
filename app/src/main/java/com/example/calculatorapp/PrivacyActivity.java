package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {
    LinearLayout privacy;
    TextView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_privacy);

        backBtn = findViewById(R.id.backPrivacy);
        privacy = findViewById(R.id.privacy);

        backBtn.setOnClickListener(v -> finish());

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PrivacyActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}