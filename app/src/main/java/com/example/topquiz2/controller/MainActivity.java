package com.example.topquiz2.controller;

import androidx.annotation.Nullable;
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
import com.example.topquiz2.model.User;

public class MainActivity extends AppCompatActivity {
// declaration des variables pour interagir avec les élements
    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42; //doit etre unique, définir l'identifiant de la GameActivity

    //recupération du resultat du score
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigner une valeur a nos éléments (branchement acex l'id de l'élément du activity-main)
        mGreetingTextView = findViewById(R.id.main_textview_greeting);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);

        //crée un attribut de type User et initialise(en fonction de la classe User)
        //sert à créer un User
        User mUser = new User();


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
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);

                //mémorisez le prénom du joueur lorsqu'il clique sur le bouton
                mUser.setFirstName(mNameEditText.getText().toString());
            }
        });
    }


}