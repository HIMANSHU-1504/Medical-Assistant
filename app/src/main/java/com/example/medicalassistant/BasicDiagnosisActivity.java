package com.example.medicalassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BasicDiagnosisActivity extends AppCompatActivity {

    private EditText userIssueEditText;
    private Button submitButton;
    private TextView suggestedMedicineTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_diagnosis);

        // Initialize UI elements
        userIssueEditText = findViewById(R.id.userIssueEditText);
        submitButton = findViewById(R.id.submitButton);
        suggestedMedicineTextView = findViewById(R.id.suggestedMedicineTextView);

        // Set a click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from the EditText
                String userIssue = userIssueEditText.getText().toString().toLowerCase();

                // Perform keyword matching (simplified for demonstration)
                String suggestedMedicine = suggestMedicine(userIssue);

                // Display the suggested medicine in the TextView
                suggestedMedicineTextView.setText("Suggested Medicine: " + suggestedMedicine);
            }
        });
    }

    // Function to suggest medicine based on keywords (simplified)
    private String suggestMedicine(String userIssue) {
        // Implement your keyword recognition and medication suggestion logic here
        if (userIssue.contains("fever") && userIssue.contains("cold")) {
            return "Paracetamol";
        } else if (userIssue.contains("headache")) {
            return "Aspirin";
        } // Add more keyword-based suggestions as needed

        // Default suggestion if no keywords match
        return "No suggestion available";
    }
}