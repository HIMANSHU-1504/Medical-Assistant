package com.example.medicalassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReminderBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the reminder action here (e.g., show a notification)
        Toast.makeText(context, "It's time for your reminder!", Toast.LENGTH_SHORT).show();
    }
}

