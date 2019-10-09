package com.example.calculatorconversion;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;

public class Settings extends AppCompatActivity {

  Spinner toSpinner;
  Spinner fromSpinner;

  TextView fromUnitText;
  TextView toUnitText;

  ArrayList<String> lengthList;
  ArrayList<String> volumeList;
  ArrayAdapter<String> adapter;

  String currentCalc;
  static final String VOLUME = "VOLUME";
  static final String LENGTH = "LENGTH";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Add lists for length and volume
    lengthList = new ArrayList<>(Arrays.asList("Yards", "Meters", "Miles"));
    volumeList = new ArrayList<>(Arrays.asList("Liters", "Gallons", "Quarts"));

    toUnitText = findViewById(R.id.fromUnitText);
    fromUnitText = findViewById(R.id.toUnitText);

    toSpinner = findViewById(R.id.toSpinner);
    fromSpinner = findViewById(R.id.fromSpinner);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//            R.array.vices, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selection = (String) adapterView.getItemAtPosition(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    Intent payload = getIntent();
    if (payload.hasExtra("fromUnitText")) {
      fromUnitText.setText(payload.getStringExtra("fromUnitText"));
    }
    if (payload.hasExtra("toUnitText")) {
      toUnitText.setText(payload.getStringExtra("toUnitText"));
    }
    if (payload.hasExtra("currentCalc")) {
      currentCalc = payload.getStringExtra("currentCalc");
    }

    switch (currentCalc) {
      case VOLUME:
        adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, volumeList);
        break;
      case LENGTH:
        adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, lengthList);
        break;
    }

    fromSpinner.setAdapter(adapter);
    toSpinner.setAdapter(adapter);

  }
}
