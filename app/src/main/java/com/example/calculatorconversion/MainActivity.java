package com.example.calculatorconversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

  final Conversion conversion = new Conversion();

  // Default is length
  String currentCalc = "length";
  static final String VOLUME = "volume";
  static final String LENGTH = "length";

  ArrayList<String> lengthList;
  ArrayList<String> volumeList;

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

    // Menu item
    MenuItem settingsItem = findViewById(R.id.settings);

    // Add lists for length and volume
    lengthList = new ArrayList<>(Arrays.asList("Yards", "Meters", "Miles"));
    volumeList = new ArrayList<>(Arrays.asList("Liters", "Gallons", "Quarts"));

    clearButton.setOnClickListener(e -> {
      textFieldFrom.setText("");
      textFieldTo.setText("");
      dismissKeyboard();
    });

    textFieldFrom.setOnFocusChangeListener((View v, boolean hasFocus) -> {
      if (hasFocus) {
        textFieldTo.setText("");
      }
    });

    textFieldTo.setOnFocusChangeListener((View v, boolean hasFocus) -> {
      if (hasFocus) {
        textFieldFrom.setText("");
      }
    });

    calculateButton.setOnClickListener(e -> {
      double num, convertValue;

      // See if from focus
      if (textFieldFrom.hasFocus() && !textFieldFrom.getText().toString().equals("")) {

        String key = fromUnitText.getText().toString() + toUnitText.getText().toString();
        num = Double.parseDouble(textFieldFrom.getText().toString());
        convertValue = conversion.getConversionMap().get(currentCalc).get(key);
        textFieldTo.setText(Double.toString(num * convertValue));

      } else if (textFieldTo.hasFocus() && !textFieldTo.getText().toString().equals("")) {

        String key = toUnitText.getText().toString() + fromUnitText.getText().toString();
        num = Double.parseDouble(textFieldTo.getText().toString());
        convertValue = conversion.getConversionMap().get(currentCalc).get(key);
        textFieldFrom.setText(Double.toString(num * convertValue));
      }

      dismissKeyboard();
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

      dismissKeyboard();
    });


  }

  // From https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
  void dismissKeyboard() {
    try {
      InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    } catch (Exception e) {
      // keyboard already hidden
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.navbar, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.settings:
        Intent switchToSettings = new Intent(MainActivity.this, Settings.class);
        startActivity(switchToSettings);
    }
    return super.onOptionsItemSelected(item);
  }
}


