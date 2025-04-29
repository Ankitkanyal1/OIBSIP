package com.example.unitconverterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.unitconverterapplication.R;

public class StopwatchActivity extends AppCompatActivity {

    private TextView display, status;
    private ImageButton playBt, resetbt;
    private LinearLayout lapContainer;
    private LottieAnimationView swirlyAnimation;

    private boolean isRunning = false;
    private long startTime = 0L;
    private long timeBuffer = 0L;
    private long updatedTime = 0L;
    private int lapCount = 0;
    ImageView back;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        public void run() {
            long currentTime = System.currentTimeMillis();
            long timeInMilliseconds = currentTime - startTime;
            updatedTime = timeBuffer + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int milliseconds = (int) (updatedTime % 1000);
            secs = secs % 60;

            display.setText(String.format("%02d:%02d.%02d", mins, secs, milliseconds / 10));
            handler.postDelayed(this, 60);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);

        display = findViewById(R.id.display);
        status = findViewById(R.id.status);
        playBt = findViewById(R.id.playBt);
        resetbt = findViewById(R.id.resetbt);
        lapContainer = findViewById(R.id.lapContainer);
        swirlyAnimation = findViewById(R.id.animation);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> {
            startActivity(new Intent(StopwatchActivity.this,MainActivity.class));
        });
        swirlyAnimation.pauseAnimation();

        playBt.setOnClickListener(v -> {
            if (!isRunning) {
                startTimer();
            } else {
                pauseTimer();
            }
        });

        resetbt.setOnClickListener(v -> resetTimer());

        // Add Lap functionality on long press play button
        playBt.setOnLongClickListener(v -> {
            if (isRunning) {
                recordLap();
            }
            return true;
        });
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable, 0);
        swirlyAnimation.playAnimation();
        status.setText("Running");
        playBt.setBackgroundResource(R.drawable.pausebt);
        isRunning = true;
    }

    private void pauseTimer() {
        timeBuffer += System.currentTimeMillis() - startTime;
        handler.removeCallbacks(runnable);
        swirlyAnimation.pauseAnimation();
        status.setText("Paused");
        playBt.setBackgroundResource(R.drawable.playbt);
        isRunning = false;

        // Record lap on pause
        recordLap();
    }


    private void resetTimer() {
        handler.removeCallbacks(runnable);
        updatedTime = 0L;
        startTime = 0L;
        timeBuffer = 0L;
        isRunning = false;
        lapCount = 0;
        display.setText("00:00.00");
        status.setText("Reset");
        swirlyAnimation.pauseAnimation();
        playBt.setBackgroundResource(R.drawable.playbt);
        lapContainer.removeAllViews(); // clear laps
    }

    private void recordLap() {
        lapCount++;

        // Get the current displayed time
        String currentTime = display.getText().toString();

        TextView lapText = new TextView(this);
        lapText.setText(String.format("Lap %d\n%s", lapCount, currentTime));
        lapText.setTextColor(getResources().getColor(android.R.color.black));
        lapText.setTextSize(18f);
        lapText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lapText.setPadding(16, 8, 16, 8);
        lapText.setBackgroundResource(R.drawable.lap_item_bg); // optional, for styling

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);
        lapText.setLayoutParams(params);

        lapContainer.addView(lapText);
        findViewById(R.id.lapScrollView).setVisibility(View.VISIBLE);
    }


}
