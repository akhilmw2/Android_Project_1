package com.example.android_project_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button dialerButton = findViewById(R.id.button1);
        Button messageButton = findViewById(R.id.button2);
        phoneInput = findViewById(R.id.edit_phone);


        // setting on click listener on dialer button
        dialerButton.setOnClickListener(v -> {
            String phoneNumber = phoneInput.getText().toString();
            if (isPhoneNumberValid(phoneNumber)) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid number: " + phoneNumber, Toast.LENGTH_SHORT).show();
            }
        });


        // setting on click listener on message button
        messageButton.setOnClickListener(v -> {
            String phoneNumber = phoneInput.getText().toString();
            if (isPhoneNumberValid(phoneNumber)) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                intent.putExtra("sms_body", "Hello Akhil Nair");
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid number: " + phoneNumber, Toast.LENGTH_SHORT).show();
            }
        });


    }


    // handles state change when orientation changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText phoneNumberInput = findViewById(R.id.edit_phone);
        outState.putString("phoneNumber", phoneNumberInput.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText phoneNumberInput = findViewById(R.id.edit_phone);
        phoneNumberInput.setText(savedInstanceState.getString("phoneNumber"));
    }


    // Method to check if phone number format is correct or not?
    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("\\d{10}") || phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}");
    }


}