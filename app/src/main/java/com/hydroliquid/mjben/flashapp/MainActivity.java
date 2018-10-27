package com.hydroliquid.mjben.flashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity
{
    boolean isShowingAnswers = false;

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

                    isShowingAnswers = true;
                }
                else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource
                            (R.drawable.ic_question_mark_circle);

                    isShowingAnswers = false;
                }

            }
        });



        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!isShowingAnswers) {
                    findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                }else{
                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                }

            }
        });




    }
}
