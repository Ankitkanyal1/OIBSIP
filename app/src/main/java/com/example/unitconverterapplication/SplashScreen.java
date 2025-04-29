package com.example.unitconverterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        // Delay and move to MainActivity
        new Handler().postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE); //Access a small storage in app memory
            boolean isFirstTime = preferences.getBoolean("isFirstTime", true); //	Check if user has completed intro or not

            if (isFirstTime) {
                // First time: Show Intro
                startActivity(new Intent(SplashScreen.this, IntroActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)); //now you can go back to splashscreen from IntroActivity
            } else {
                // Not first time: Go directly to Home
                startActivity(new Intent(SplashScreen.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)); //now you can go back to splashscreen from MainActivity
            }
        }, 2000); // 2 seconds delay
    }
}
