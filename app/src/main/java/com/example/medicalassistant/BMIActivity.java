package com.example.medicalassistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView bmiResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);

        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Get the weight and height from the EditText fields
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());

        // Calculate BMI
        double bmi = weight / (height * height);

        // Display the result
        bmiResultTextView.setText("Your BMI is: " + String.format("%.2f", bmi));
    }
}
