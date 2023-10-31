package com.example.medicalassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {
    private EditText medicineEditText;
    private TimePicker timePicker;
    private Button showDateTimePickerButton; // Button to show the date and time picker
    private Button createReminderButton;
    private int selectedHour;
    private int selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        medicineEditText = findViewById(R.id.medicineEditText);
        timePicker = findViewById(R.id.timePicker);
        showDateTimePickerButton = findViewById(R.id.showDateTimePickerButton);
        createReminderButton = findViewById(R.id.createReminderButton);

        showDateTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the date and time picker dialog
                showDateTimePicker();
            }
        });

        createReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleReminder();
            }
        });
    }

    // Function to show the date and time picker dialog
    private void showDateTimePicker() {
        selectedHour = timePicker.getCurrentHour();
        selectedMinute = timePicker.getCurrentMinute();

        // You can use a DatePickerDialog for date and a TimePickerDialog for time
        // For simplicity, you can use a single TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay;
                selectedMinute = minute;
            }
        }, selectedHour, selectedMinute, false);

        timePickerDialog.show();
    }

    // Function to schedule the reminder
    private void scheduleReminder() {
        String medicineName = medicineEditText.getText().toString();
        if (medicineName.isEmpty()) {
            Toast.makeText(this, "Please enter a medicine name", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinute);

        long reminderTime = calendar.getTimeInMillis();

        // Create an intent to trigger the reminder
        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
        intent.putExtra("medicineName", medicineName); // Pass medicine name to the BroadcastReceiver
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        // Schedule the reminder using AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent);

        Toast.makeText(this, "Reminder scheduled for " + medicineName, Toast.LENGTH_SHORT).show();
    }
}
