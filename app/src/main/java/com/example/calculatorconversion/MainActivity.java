package com.example.calculatorconversion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  final Conversion conversion = new Conversion();

  // Default is length
  String currentCalc = "length";
  static final String VOLUME = "volume";
  static final String LENGTH = "length";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Fields
    TextView textFieldFrom = findViewById(R.id.textFieldFrom);
    TextView textFieldTo = findViewById(R.id.textFieldTo);

    // Units
    TextView converterUnit = findViewById(R.id.converterUnit);
    TextView fromUnitText = findViewById(R.id.fromUnitText);
    TextView toUnitText = findViewById(R.id.toUnitText);

    // Buttons
    Button calculateButton = findViewById(R.id.calculateButton);
    Button clearButton = findViewById(R.id.clearButton);
    Button modeButton = findViewById(R.id.modeButton);

    clearButton.setOnClickListener(e -> {
      textFieldFrom.setText("");
      textFieldTo.setText("");
    });

    textFieldFrom.setOnClickListener(e ->
      textFieldTo.setText(""));

    textFieldTo.setOnClickListener(e ->
      textFieldFrom.setText(""));

    calculateButton.setOnClickListener(e -> {
      double num, convertValue;

      // See if from focus
      if (!textFieldFrom.getText().toString().equals("")) {

        String key = fromUnitText.getText().toString() + toUnitText.getText().toString();
        num = Double.parseDouble(textFieldFrom.getText().toString());
        convertValue = conversion.getConversionMap().get(currentCalc).get(key);
        textFieldTo.setText(Double.toString(num * convertValue));

      } else if (!textFieldTo.getText().toString().equals("")){

        String key = toUnitText.getText().toString() + fromUnitText.getText().toString();
        num = Double.parseDouble(textFieldTo.getText().toString());
        convertValue = conversion.getConversionMap().get(currentCalc).get(key);
        textFieldFrom.setText(Double.toString(num * convertValue));
      }

    });

    modeButton.setOnClickListener(e -> {
      switch (currentCalc) {
        case LENGTH:
          currentCalc = VOLUME;
          converterUnit.setText("Volume Converter");
          fromUnitText.setText("Gallons");
          toUnitText.setText("Liters");
          break;
        case VOLUME:
          currentCalc = LENGTH;
          converterUnit.setText("Length Converter");
          fromUnitText.setText("Yards");
          toUnitText.setText("Meters");
      }
    });


  }

}
