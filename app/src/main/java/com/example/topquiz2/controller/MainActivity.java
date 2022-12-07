package com.example.topquiz2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz2.R;

public class MainActivity extends AppCompatActivity {
// declaration des variables pour interagir avec les élements
    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigner une valeur a nos éléments (branchement acex l'id de l'élément du activity-main)
        mGreetingTextView = findViewById(R.id.main_textview_greeting);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);

        //déactive le bouton
        mPlayButton.setEnabled(false);
        //être notifier quand l'utilisateur tape du texte
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //detecte quand l'utilisateur commence à écrire.
            @Override
            public void afterTextChanged(Editable editable) {
            //active/desactive le bouton en fonction de la longueur de caractère de l'editText.Si la valeur est vide ,le bouton est déactiver.
                mPlayButton.setEnabled(!editable.toString().isEmpty());
            }
        });
        //detecter un clique sur le bouton.
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // au clique ,on démarre la seconde activity . on crée l'objet Intent avec ses deux paramétres.
                //Les Intents permettent de lancer de nouvelles Activity grâce à la méthode startActivity().
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                //ce qu'il permet de démarrer la nouvelle activité (startActivity)
                startActivity(gameActivityIntent);
            }
        });
    }


}