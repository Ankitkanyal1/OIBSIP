package com.example.unitconverterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    TextView textView;
    StringBuilder input=new StringBuilder();
    double firstNum=0,secNum=0;
    String op="";
    boolean isNewOp=true;

    ImageView backArrow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        textView=findViewById(R.id.tvResult);
        int[] buttonId={ R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3,
                R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7,
                R.id.bt8, R.id.bt9, R.id.bt00, R.id.btdot};

        for(int id:buttonId){
            Button b=findViewById(id);
            b.setOnClickListener(this::onNumberClick);
        }

        findViewById(R.id.btplus).setOnClickListener(v->onOperationClick("+"));
        findViewById(R.id.btMin).setOnClickListener(v->onOperationClick("-"));
        findViewById(R.id.btMod).setOnClickListener(v->onOperationClick("%"));
        findViewById(R.id.btDiv).setOnClickListener(v->onOperationClick("/"));
        findViewById(R.id.btX).setOnClickListener(v->onOperationClick("*"));

        findViewById(R.id.btEq).setOnClickListener(v->onEqualClick());
        findViewById(R.id.btC).setOnClickListener(v->onClearClick());
        findViewById(R.id.btAc).setOnClickListener(v->onAllClearClick());

        backArrow=findViewById(R.id.back);
        backArrow.setOnClickListener(v->{
            startActivity(new Intent(Calculator.this, MainActivity.class));
                });
    }

    private void onClearClick() {
        if(input.length()>0){
            input.setLength(input.length()-1);
            textView.setText(input.toString());
        }
    }

    private void onAllClearClick() {
        input.setLength(0);
        firstNum=secNum=0;
        op="";
        textView.setText("0");
    }

    private void onEqualClick() {
        try {
            secNum = Double.parseDouble(input.toString());
        } catch (Exception e) {
            secNum = 0;
        }
        double result = 0;
        switch (op) {
            case "+":
                result = firstNum + secNum;
                break;
            case "-":
                result = firstNum - secNum;
                break;
            case "*":
                result = firstNum * secNum;
                break;
            case "/":
                result= (secNum!=0)?firstNum/secNum:0;
                break;
            case "%":
                result=firstNum%secNum;
                break;
        }
        textView.setText(String.valueOf(result));
        input.setLength(0);
        input.append(result);
        isNewOp=true;
    }
    private void onOperationClick(String s) {
        try {
            firstNum=Double.parseDouble(input.toString());
        }
        catch (Exception e){
            firstNum=0;
        }
        op=s;
        isNewOp=true;
        textView.setText(input.toString() + " " + op);
    }

    private void onNumberClick(View view) {
        Button button=(Button) view;
        if(isNewOp){
            input.setLength(0);
            isNewOp=false;
        }
        input.append(button.getText().toString());
        textView.setText(input.toString());
    }
}








