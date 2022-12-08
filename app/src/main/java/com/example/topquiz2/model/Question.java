package com.example.topquiz2.model;

import java.util.List;

public class Question {


    private final String mQuestion;//texte de la question
    private final List<String> mChoiceList;//listes des réponses
    private final int mAnswerIndex;//index de la réponse

    //constructeur
    public Question(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    //getters
    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
}
