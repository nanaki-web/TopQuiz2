package com.example.topquiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    mGreetingTextView = findViewById(R.id.main_textview_greeting);
    mNameEditText = findViewById(R.id.main_edittext_name);
    mPlayButton = findViewById(R.id.main_button_play);
    mPlayButton.setEnabled(false);

}