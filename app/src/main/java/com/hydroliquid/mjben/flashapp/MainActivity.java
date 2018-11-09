package com.hydroliquid.mjben.flashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // Visibility Variables
    boolean isShowingAnswers = false;
    boolean answerOn = false;
    boolean firstQuestion = false;
    boolean secondQuestion = false;
    boolean greenOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Quick Access
        TextView flashQuest = findViewById(R.id.flashcard_question);
        TextView flashAnswer = findViewById(R.id.flashcard_anwser);
        TextView answer1 = findViewById(R.id.flashcard_answer1);
        TextView answer1Wrong = findViewById(R.id.flashcard_answerWrong1);
        TextView answer2 = findViewById(R.id.flashcard_answer2);
        TextView answer2Wrong = findViewById(R.id.flashcard_answerWrong2);
        TextView answer3 = findViewById(R.id.flashcard_answer3);
        TextView answer3Correct = findViewById(R.id.flashcard_answerCorrect3);
        ImageView toggle = ((ImageView) findViewById(R.id.toggle_choices_visibility));
        // Set Listeners
        flashQuest.setOnClickListener(this);
        flashAnswer.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer1Wrong.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer2Wrong.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer3Correct.setOnClickListener(this);
        toggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView flashQuest = findViewById(R.id.flashcard_question);
        TextView flashAnswer = findViewById(R.id.flashcard_anwser);
        TextView answer1 = findViewById(R.id.flashcard_answer1);
        TextView answer1Wrong = findViewById(R.id.flashcard_answerWrong1);
        TextView answer2 = findViewById(R.id.flashcard_answer2);
        TextView answer2Wrong = findViewById(R.id.flashcard_answerWrong2);
        TextView answer3 = findViewById(R.id.flashcard_answer3);
        TextView answer3Correct = findViewById(R.id.flashcard_answerCorrect3);
        ImageView toggle = ((ImageView) findViewById(R.id.toggle_choices_visibility));

        switch (v.getId()){
            case R.id.flashcard_question:
            {
                if(!answerOn) {
                    flashQuest.setVisibility(View.INVISIBLE);
                    flashAnswer.setVisibility(View.VISIBLE);
                    answerOn = true;
                }
            }
            break;
            case R.id.flashcard_anwser:
            {
                if(answerOn){
                    flashQuest.setVisibility(View.VISIBLE);
                    flashAnswer.setVisibility(View.INVISIBLE);
                    answerOn = false;
                }
            }
            break;
            case R.id.flashcard_answer1:
            {
                if(!firstQuestion){
                    answer1.setVisibility(View.INVISIBLE);
                    answer1Wrong.setVisibility(View.VISIBLE);
                    if(!greenOn) {
                        answer3.setVisibility(View.INVISIBLE);
                        answer3Correct.setVisibility(View.VISIBLE);
                        greenOn = true;
                    }
                    firstQuestion = true;
                }
            }
            break;
            case R.id.flashcard_answerWrong1:
            {
                if(firstQuestion){
                    answer1.setVisibility(View.VISIBLE);
                    answer1Wrong.setVisibility(View.INVISIBLE);
                    if(greenOn) {
                        answer2.setVisibility(View.VISIBLE);
                        answer2Wrong.setVisibility(View.INVISIBLE);
                        answer3.setVisibility(View.VISIBLE);
                        answer3Correct.setVisibility(View.INVISIBLE);
                        firstQuestion = false;
                        secondQuestion = false;
                        greenOn = false;
                    }
                    firstQuestion = false;
                }
            }
            break;
            case R.id.flashcard_answer2:
            {
                if(!secondQuestion){
                    answer2.setVisibility(View.INVISIBLE);
                    answer2Wrong.setVisibility(View.VISIBLE);
                    if(!greenOn) {
                        answer3.setVisibility(View.INVISIBLE);
                        answer3Correct.setVisibility(View.VISIBLE);
                        greenOn = true;
                    }
                    secondQuestion = true;
                }
            }
            break;
            case R.id.flashcard_answerWrong2:
            {
                if(secondQuestion){
                    answer2.setVisibility(View.VISIBLE);
                    answer2Wrong.setVisibility(View.INVISIBLE);
                    if(greenOn) {
                        answer1.setVisibility(View.VISIBLE);
                        answer1Wrong.setVisibility(View.INVISIBLE);
                        answer3.setVisibility(View.VISIBLE);
                        answer3Correct.setVisibility(View.INVISIBLE);
                        firstQuestion = false;
                        secondQuestion = false;
                        greenOn = false;
                    }
                    secondQuestion = false;
                }
            }
            break;
            case R.id.flashcard_answer3:
            {
                if(!greenOn){
                    answer3.setVisibility(View.INVISIBLE);
                    answer3Correct.setVisibility(View.VISIBLE);
                    greenOn = true;
                }
            }
            break;
            case R.id.flashcard_answerCorrect3:
            {
                if(greenOn){
                    answer1.setVisibility(View.VISIBLE);
                    answer1Wrong.setVisibility(View.INVISIBLE);
                    answer2.setVisibility(View.VISIBLE);
                    answer2Wrong.setVisibility(View.INVISIBLE);
                    answer3.setVisibility(View.VISIBLE);
                    answer3Correct.setVisibility(View.INVISIBLE);
                    firstQuestion = false;
                    secondQuestion = false;
                    greenOn = false;
                }
            }
            break;
            case R.id.toggle_choices_visibility:
            {
                if(!isShowingAnswers)
                {
                    answer1.setVisibility(View.INVISIBLE);
                    answer1Wrong.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.INVISIBLE);
                    answer2Wrong.setVisibility(View.VISIBLE);
                    answer3.setVisibility(View.INVISIBLE);
                    answer3Correct.setVisibility(View.VISIBLE);
                    isShowingAnswers = true;
                    firstQuestion = true;
                    secondQuestion = true;
                    greenOn = true;
                }else{
                    answer1.setVisibility(View.VISIBLE);
                    answer1Wrong.setVisibility(View.INVISIBLE);
                    answer2.setVisibility(View.VISIBLE);
                    answer2Wrong.setVisibility(View.INVISIBLE);
                    answer3.setVisibility(View.VISIBLE);
                    answer3Correct.setVisibility(View.INVISIBLE);
                    isShowingAnswers = false;
                    firstQuestion = false;
                    secondQuestion = false;
                    greenOn = false;
                }
            }
            break;
        }
        if(greenOn){
            isShowingAnswers = true;
        }else{
            isShowingAnswers = false;
            greenOn = false;
        }
    }

}
// Old code
/*
((ImageView) findViewById(R.id.toggle_choices_visibility)).setOnClickListener
        (new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(!isShowingAnswers) {
            ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource
                    (R.drawable.ic_check_mark);
            if(!firstQuestion || !secondQuestion) {
                findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);
                if(!firstQuestion || !secondQuestion) {
                    firstQuestion = false;
                    secondQuestion = true;
                }
                else{
                    firstQuestion = true;
                    secondQuestion = false;
                }
            }
            else if(firstQuestion || secondQuestion) {
                findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
                if(firstQuestion) {
                    firstQuestion = true;
                    secondQuestion = false;
                }
                else{
                    firstQuestion = false;
                    secondQuestion = true;
                }
            }
            isShowingAnswers = true;
        }
        else {
            ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource
                    (R.drawable.ic_question_mark_circle);
            if(!firstQuestion || !secondQuestion) {
                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
                if(firstQuestion) {
                    secondQuestion = true;
                }
                else{
                    firstQuestion = true;
                }
            }
            else if(firstQuestion || secondQuestion) {
                findViewById(R.id.flashcard_answerWrong1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answerWrong2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
                if(firstQuestion) {
                    secondQuestion = false;
                }
                else{
                    firstQuestion = false;
                }
            }
            isShowingAnswers = false;
        }
    }
});
// Main Question and Answer
flashQuest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(!answerOn) {
            findViewById(R.id.flashcard_anwser).setVisibility(View.VISIBLE);

            flashQuest.setVisibility(View.INVISIBLE);

            answerOn = true;

        }
    }
});
findViewById(R.id.flashcard_anwser).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(answerOn) {
            findViewById(R.id.flashcard_anwser).setVisibility(View.INVISIBLE);

            flashQuest.setVisibility(View.VISIBLE);
            answerOn = false;
        }
    }
});
// End main Question and Answer
// Answer 1 button
findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(!answerOn) {
            findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

            findViewById(R.id.flashcard_answerWrong1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
            if(!firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
            answerOn = true;
        }
        else if(firstQuestion || secondQuestion) {
            findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

            findViewById(R.id.flashcard_answerWrong1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);

            answerOn = true;
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
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
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }

            answerOn = true;
        }
        else if(!firstQuestion || !secondQuestion){
            findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

            findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
            answerOn = false;
        }
    }
});
// End Answer 1 button
// Answer 2
findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(!answerOn) {
            findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

            findViewById(R.id.flashcard_answerWrong2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
            answerOn = true;
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
        }
        else if(firstQuestion || secondQuestion) {
            findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);

            findViewById(R.id.flashcard_answerWrong2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
            answerOn = true;
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
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
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
            answerOn = true;
        }
        else if(!firstQuestion || !secondQuestion){
            findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

            findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
            answerOn = false;
        }
    }
});
// End Answer 2
// Answer 3
findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(!answerOn) {
            findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.VISIBLE);
            answerOn = true;
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
        }

    }
});
findViewById(R.id.flashcard_answerCorrect3).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(answerOn){
            findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

            findViewById(R.id.flashcard_answerWrong1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerWrong2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_answerCorrect3).setVisibility(View.INVISIBLE);
            if(firstQuestion) {
                secondQuestion = true;
            }
            else{
                firstQuestion = true;
            }
            answerOn = false;
        }
    }
});
*/
// End Answer 3
