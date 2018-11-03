package com.hydroliquid.mjben.flashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity
{
    boolean isShowingAnswers = false;
    boolean answerOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageView) findViewById(R.id.toggle_choices_visibility)).setOnClickListener
                (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isShowingAnswers) {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource
                            (R.drawable.ic_check_mark);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);
                    isShowingAnswers = true;
                }
                else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource
                            (R.drawable.ic_question_mark_circle);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
                    isShowingAnswers = false;
                }
            }
        });
        
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!answerOn) {
                    findViewById(R.id.flashcard_anwser).setVisibility(View.INVISIBLE);

                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);

                    answerOn = true;
                }
            }
        });
        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!answerOn) {
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

                    findViewById(R.id.flashcard_answerWrong1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);

                    answerOn = true;
                }
            }
        });
        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!answerOn) {
                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

                    findViewById(R.id.flashcard_answerWrong2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
                    answerOn = true;
                }
            }
        });
        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!answerOn) {
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
                    answerOn = true;
                }

            }
        });

        findViewById(R.id.flashcard_anwser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(answerOn) {
                    findViewById(R.id.flashcard_anwser).setVisibility(View.VISIBLE);

                    findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);

                    answerOn = false;
                }
            }
        });
        findViewById(R.id.flashcard_answerWrong1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(answerOn) {
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

                    findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);

                    answerOn = false;
                }
            }
        });

        findViewById(R.id.flashcard_answerWrong2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(answerOn) {
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

                    findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
                    answerOn = false;
                }
            }
        });

        findViewById(R.id.flashcard_answerCorrect3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!answerOn){
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
                    answerOn = false;
                }
            }
        });
    }
}
