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

/**
 * Calculator Conversion app by Zachary Thomas and James Lund
 */

public class MainActivity extends AppCompatActivity {

  final Conversion conversion = new Conversion();

  // Default is length
  String currentCalc = "LENGTH";
  static final String VOLUME = "VOLUME";
  static final String LENGTH = "LENGTH";

  TextView toUnitText;
  TextView converterUnit;
  TextView fromUnitText;

  public static final int SETTINGS = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Fields
    TextView textFieldFrom = findViewById(R.id.textFieldFrom);
    TextView textFieldTo = findViewById(R.id.textFieldTo);

    // Units
    converterUnit = findViewById(R.id.converterUnit);
    fromUnitText = findViewById(R.id.toUnitText);
    toUnitText = findViewById(R.id.fromUnitText);

    // Buttons
    Button calculateButton = findViewById(R.id.calculateButton);
    Button clearButton = findViewById(R.id.clearButton);
    Button modeButton = findViewById(R.id.modeButton);

    Intent payload = getIntent();
    if (payload.hasExtra("fromUnitText")) {
      fromUnitText.setText(payload.getStringExtra("fromUnitText"));
    }
    if (payload.hasExtra("toUnitText")) {
      toUnitText.setText(payload.getStringExtra("toUnitText"));
    }

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
  public boolean onOptionsItemSelected(MenuItem settingsItem) {
    switch (settingsItem.getItemId()) {
      case R.id.settings:
        Intent switchToSettings = new Intent(MainActivity.this, Settings.class);
        switchToSettings.putExtra("fromUnitTextSettings", fromUnitText.getText());
        switchToSettings.putExtra("toUnitTextSettings", toUnitText.getText());
        switchToSettings.putExtra("currentCalc", currentCalc);

        startActivityForResult(switchToSettings, SETTINGS);
    }
    return super.onOptionsItemSelected(settingsItem);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == SETTINGS) {

      fromUnitText.setText(data.getStringExtra("fromUnitText"));
      toUnitText.setText(data.getStringExtra("toUnitText"));
    }

  }

}