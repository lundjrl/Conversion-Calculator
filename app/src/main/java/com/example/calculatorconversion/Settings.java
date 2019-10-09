package com.example.calculatorconversion;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Settings extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    TextView toUnitText = findViewById(R.id.fromUnitText);
    TextView fromUnitText = findViewById(R.id.toUnitText);

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

  }
}
