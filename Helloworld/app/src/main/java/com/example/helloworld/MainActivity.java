package com.example.helloworld;


import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    TextView textViewGreeting, textViewVolume, textViewSummary;
    ImageView imageView;
    CheckBox checkboxSubscribe;
    RadioGroup radioGroupColors;
    Switch switchNotifications;
    SeekBar seekBarVolume;
    Spinner spinnerCountry;
    ProgressBar progressBar;
    Button buttonSubmit;

    String[] countries = {"USA", "Canada", "UK", "India", "Australia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewVolume = findViewById(R.id.textViewVolume);
        textViewSummary = findViewById(R.id.textViewSummary);
        imageView = findViewById(R.id.imageView);
        checkboxSubscribe = findViewById(R.id.checkboxSubscribe);
        radioGroupColors = findViewById(R.id.radioGroupColors);
        switchNotifications = findViewById(R.id.switchNotifications);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        progressBar = findViewById(R.id.progressBar);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewVolume.setText("Volume: " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

            simulateProgressBar();

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String greeting = "Hello, " + name + "!";
            textViewGreeting.setText(greeting);

            boolean isSubscribed = checkboxSubscribe.isChecked();
            String subscription = isSubscribed ? "Subscribed" : "Not Subscribed";

            int selectedColorId = radioGroupColors.getCheckedRadioButtonId();
            RadioButton selectedColor = findViewById(selectedColorId);
            String color = selectedColor != null ? selectedColor.getText().toString() : "None";

            boolean notificationsEnabled = switchNotifications.isChecked();
            int volume = seekBarVolume.getProgress();
            String country = spinnerCountry.getSelectedItem().toString();

            String summary = "Name: " + name + "\n" +
                    "Greeting: " + greeting + "\n" +
                    "Subscription: " + subscription + "\n" +
                    "Favorite Color: " + color + "\n" +
                    "Notifications: " + (notificationsEnabled ? "Enabled" : "Disabled") + "\n" +
                    "Volume: " + volume + "\n" +
                    "Country: " + country;

            textViewSummary.setText(summary);

            simulateProgressBar(); // simulate progress again on button click
        });
    }

    private void simulateProgressBar() {
        progressBar.setProgress(0);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int progressStatus = 0;
            public void run() {
                if (progressStatus < 100) {
                    progressStatus += 5;
                    progressBar.setProgress(progressStatus);
                    handler.postDelayed(this, 100);
                }
            }
        };
        handler.post(runnable);
    }
}
