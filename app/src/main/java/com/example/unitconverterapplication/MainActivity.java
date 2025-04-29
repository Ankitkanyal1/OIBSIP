package com.example.unitconverterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button calc, unitConverter, watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = findViewById(R.id.btcalc);
        unitConverter = findViewById(R.id.btcon);
        watch = findViewById(R.id.btstopwatch);

        calc.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Calculator.class));
        });

        unitConverter.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Converter.class));
        });

        watch.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StopwatchActivity.class));
        });
    }
}
