package com.example.unitconverterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.unitconverterapplication.helper.ConversionHelper;

public class Converter extends AppCompatActivity {

    Spinner spinnerFrom, spinnerTo;
    EditText inputValue;
    Button convertBtn;
    ImageView backBtn;
    TextView resultText;

    String[] units = {"Centimeter", "Meter", "Kilometer", "Gram", "Kilogram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);

        LottieAnimationView animationView=findViewById(R.id.animationView);
        animationView.playAnimation();

        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        inputValue = findViewById(R.id.inputValue);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);
        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(v->{
            startActivity(new Intent(Converter.this, MainActivity.class));
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = spinnerFrom.getSelectedItem().toString();
                String to = spinnerTo.getSelectedItem().toString();
                String input = inputValue.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(Converter.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(input);
                double result = ConversionHelper.convert(value, from, to);

                if (result == -1.0) {
                    Toast.makeText(Converter.this, "Cannot convert between different unit types!", Toast.LENGTH_SHORT).show();
                } else {
                    resultText.setText("Result: " + result + " " + to);
                }
            }
        });
    }
}
