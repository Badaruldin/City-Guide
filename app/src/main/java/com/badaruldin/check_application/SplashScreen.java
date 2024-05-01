package com.badaruldin.check_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    protected int time=3000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        new Handler().postDelayed(this::run, time);
    }

    private void run() {

        sharedPreferences = getSharedPreferences("xyz", MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("first", true);

        if (isFirst) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first", false);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), OnBoarding.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), user_dashboard.class));
            finish();
        }

    }
}