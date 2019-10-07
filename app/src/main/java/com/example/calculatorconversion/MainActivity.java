package com.example.calculatorconversion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fields
        TextView textFieldFrom = (TextView) findViewById(R.id.textFieldFrom);
        TextView textFieldTo = (TextView) findViewById(R.id.textFieldTo);

        // Units
        TextView converterUnit = (TextView) findViewById(R.id.converterUnit);
        TextView fromUnitText = (TextView) findViewById(R.id.fromUnitText);
        TextView toUnitText = (TextView) findViewById(R.id.toUnitText);

        // Buttons
        Button calculateButton = (Button) findViewById(R.id.calculateButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button modeButton = (Button) findViewById(R.id.modeButton);


        clearButton.setOnClickListener( e -> {
            textFieldFrom.setText("");
            textFieldTo.setText("");
        });

        calculateButton.setOnClickListener( e -> {
            double fromNum = Double.parseDouble(textFieldFrom.getText().toString());
            double toNum = Double.parseDouble(textFieldTo.getText().toString());
        });

        modeButton.setOnClickListener( e -> {

        });



    }

}


//    Intent switchToSettings = new Intent(MainActivity.this, Settings.class);
//    startActivity (switchToSettings);