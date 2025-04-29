package com.example.unitconverterapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private List<SlideItem> slideItems;
    private Button getStartedButton; // declare private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPager);
        getStartedButton = findViewById(R.id.getStartedButton); // first initialize

        // Adding slides
        slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.raw.getstarted1, "CALCULATOR", "Quick and easy calculations at your fingertips. Perfect for everyday math - fast, simple, and reliable."));
        slideItems.add(new SlideItem(R.raw.getstarted2, "All-in-One Converter", "Effortlessly convert between meters, centimeters, kilometers, grams, and kilograms. Accurate and instant conversions."));
        slideItems.add(new SlideItem(R.raw.stopwatch, "Precise Stopwatch", "Track time with accuracy. Start, stop, and reset with ease. Ideal for workouts, tasks, or challenges."));

        viewPager.setAdapter(new SliderAdapter(slideItems, this));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == slideItems.size() - 1) {
                    getStartedButton.setVisibility(View.VISIBLE);
                } else {
                    getStartedButton.setVisibility(View.GONE);
                }
            }
        });


        // When Get Started Button is clicked
        getStartedButton.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false); // Save that intro is completed
            editor.apply();

            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        });
    }
}
