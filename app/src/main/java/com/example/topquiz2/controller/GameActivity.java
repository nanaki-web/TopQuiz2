package com.example.topquiz2.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz2.R;
import com.example.topquiz2.model.Question;
import com.example.topquiz2.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //declaration/créer un attribut
    TextView mTextView;
    Button mGameButton1;
    Button mGameButton2;
    Button mGameButton3;
    Button mGameButton4;
    //creation de l'attribut de classe de type questionBank et initialise immédiatement
    QuestionBank mQuestionBank = generateQuestions();
    Question mCurrentQuestion;//pour garder en mémoire la question en cours
    private int mRemainingQuestionCount;//decide du nombre de question que comprend une partie
    private int mScore;//score du joueur
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";//créer une clé /valeur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //lier/connecter a la activity_game.xml
        mTextView = findViewById(R.id.game_activity_textview_question);
        mGameButton1 = findViewById(R.id.game_activity_button_1);
        mGameButton2 = findViewById(R.id.game_activity_button_2);
        mGameButton3 = findViewById(R.id.game_activity_button_3);
        mGameButton4 = findViewById(R.id.game_activity_button_4);

        mGameButton1.setOnClickListener(this);//this est implémenter avec onclick
        mGameButton2.setOnClickListener(this);
        mGameButton3.setOnClickListener(this);
        mGameButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();//initialise sa valeur mCurrentQuestion
        //on appelle la méthode displayQuestion
        displayQuestion(mCurrentQuestion);
        mRemainingQuestionCount = 3;//la partie comporte 4 questions
        mScore = 0; //initialise la valeur
    }

    //Créer méthode DisplayQuestion qui avec la question en paramètre met un jour l'affichage
    private void displayQuestion(final Question question) {
        mTextView.setText(question.getQuestion());//methode setText() met à jour l'affichage (textview et button)
        mGameButton1.setText(question.getChoiceList().get(0));
        mGameButton2.setText(question.getChoiceList().get(1));
        mGameButton3.setText(question.getChoiceList().get(2));
        mGameButton4.setText(question.getChoiceList().get(3));
    }


    //Création de la methode pour generer les question
    private QuestionBank generateQuestions() {//renvoie un objet de type QuestionBank
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        //renvoie un object QuestionBank avec les 3 questions
        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    //surchage de la methode Onclick (implements)
    @Override
    public void onClick(View view) {
        int index;//creation de l'index

        //valeur modifier en fonction du bouton qu'on clique
        if (view == mGameButton1) {
            index = 0;
        } else if (view == mGameButton2) {
            index = 1;
        } else if (view == mGameButton3) {
            index = 2;
        } else if (view == mGameButton4) {
            index = 3;
        } else {
            throw new IllegalStateException(("Unknow cliked view : " + view));
        }

        //la valeur est comparer avec l'index de la bonne réponse
        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;
        //condition de victoire de la partie
        if(mRemainingQuestionCount > 0){
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        }else{
            //nécessaire d'utiliser un objet spécifique pour "construire" la boîte de dialogue. C'est la sous-classe Builder qui s'en charge ;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //definire le titre de la boite de dialogue
            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)//definir le texte du message
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();//crée une instance avec intent
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);//lie le score Intent avec la clé associé BUNDLE_EXTRA_SCORE
                            setResult(RESULT_OK, intent);//nous précisons au système Android que l'activité s'est correctement terminée, et nous lui indiquons en second paramètre notre Intent (qui contient le score) ;

                            finish();//l'activité courante est terminer ,revenir a la précédente

                        }
                    })
                    // demandons à l'instance de Builder de construire la boîte de dialogue avec les paramètres  prédéfinis
                    .create()
                    .show();//affiche la boite de dialogue

        }
    }


}