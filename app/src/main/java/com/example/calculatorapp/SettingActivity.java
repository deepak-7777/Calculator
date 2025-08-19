package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    TextView backBtn;
    LinearLayout privacySettings, aboutCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);

        backBtn = findViewById(R.id.backSetting );
        aboutCalculator = findViewById(R.id.aboutCalculator);
        privacySettings = findViewById(R.id.privacySettings);

        backBtn.setOnClickListener(v -> finish());

        aboutCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
            }
        });

        privacySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, PrivacyActivity.class));
            }
        });
    }
}