package com.example.ws;
import android.Manifest;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// MainActivity.java

public class MainActivity extends AppCompatActivity {
    // Declare member variables
    private EditText etPhoneNumber;
    private Button btnSave;
    private Button btnSend;
    private String savedPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSave = findViewById(R.id.btnSave);
        btnSend = findViewById(R.id.btnSend);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the entered phone number
                savedPhoneNumber = etPhoneNumber.getText().toString();

                // Enable the "Send SOS Message" button
                btnSend.setEnabled(true);
                Toast.makeText(MainActivity.this, "Phone number saved", Toast.LENGTH_SHORT).show();
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a phone number is saved
                if (savedPhoneNumber != null && !savedPhoneNumber.isEmpty()) {
                    // Send the SOS message using the saved phone number
                    sendSOSMessage(savedPhoneNumber);
                    Toast.makeText(MainActivity.this, "SOS message sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No phone number saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to handle sending the SOS message
    private void sendSOSMessage(String phoneNumber) {
        // Implement the SMS sending functionality here
        String message = "Help! I need assistance."; // Replace with the desired SOS message

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(MainActivity.this, "SOS message sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Failed to send SOS message", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
