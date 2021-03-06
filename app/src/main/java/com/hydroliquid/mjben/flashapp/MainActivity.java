package com.hydroliquid.mjben.flashapp;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // Visibility Variables
    boolean isShowingAnswers = false;
    boolean answerOn = false;
    boolean firstQuestion = false;
    boolean secondQuestion = false;
    boolean greenOn = false;

    // Database Variables
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DataBase Variables
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        // Quick Access
        TextView flashQuest = findViewById(R.id.flashcard_question);
        TextView flashAnswer = findViewById(R.id.flashcard_anwser);
        TextView answer1 = findViewById(R.id.flashcard_answer1);
        TextView answer1Wrong = findViewById(R.id.flashcard_answerWrong1);
        TextView answer2 = findViewById(R.id.flashcard_answer2);
        TextView answer2Wrong = findViewById(R.id.flashcard_answerWrong2);
        TextView answer3 = findViewById(R.id.flashcard_answer3);
        TextView answer3Correct = findViewById(R.id.flashcard_answerCorrect3);
        ImageView toggle = findViewById(R.id.toggle_choices_visibility);
        Button createCardBut = findViewById(R.id.plus_button);
        Button forwardNextButton = findViewById(R.id.forwardNextButton);
        Button backNextButton = findViewById(R.id.backNextButton);
        Button trashCan = findViewById(R.id.trashCan);
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
        createCardBut.setOnClickListener(this);
        forwardNextButton.setOnClickListener(this);
        backNextButton.setOnClickListener(this);
        trashCan.setOnClickListener(this);
        // Check dataBase
        if (allFlashcards != null && allFlashcards.size() > 0)
        {
            flashQuest.setText(allFlashcards.get(0).getQuestion());
            flashAnswer.setText(allFlashcards.get(0).getAnswer());
            answer1.setText(allFlashcards.get(0).getWrongAnswer1());
            answer1Wrong.setText(allFlashcards.get(0).getWrongAnswer1());
            answer2.setText(allFlashcards.get(0).getWrongAnswer2());
            answer2Wrong.setText(allFlashcards.get(0).getWrongAnswer2());
            answer3.setText(allFlashcards.get(0).getAnswer());
            answer3Correct.setText(allFlashcards.get(0).getAnswer());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        TextView mainQuestion = findViewById(R.id.flashcard_question);
        TextView mainAnswer = findViewById(R.id.flashcard_anwser);
        TextView answer3 = findViewById(R.id.flashcard_answer3);
        TextView correctAnswer = findViewById(R.id.flashcard_answerCorrect3);
        TextView answer1 = findViewById(R.id.flashcard_answer1);
        TextView wrongAnswer1 = findViewById(R.id.flashcard_answerWrong1);
        TextView answer2 = findViewById(R.id.flashcard_answer2);
        TextView wrongAnswer2 = findViewById(R.id.flashcard_answerWrong2);

        if(requestCode == 100)
        {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            String answerW1 = data.getExtras().getString("answerW1");
            String answerW2 = data.getExtras().getString("answerW2");
            flashcardDatabase.insertCard(new Flashcard(question, answer, answerW1, answerW2));
            allFlashcards = flashcardDatabase.getAllCards();

            mainQuestion.setText(question);
            mainAnswer.setText(answer);
            answer3.setText(answer);
            correctAnswer.setText(answer);
            answer1.setText(answerW1);
            wrongAnswer1.setText(answerW1);
            answer2.setText(answerW2);
            wrongAnswer2.setText(answerW2);
        }
        else if(requestCode == 99)
        {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            String answerW1 = data.getExtras().getString("answerW1");
            String answerW2 = data.getExtras().getString("answerW2");
            //flashcardDatabase.insertCard(new Flashcard(question, answer, answerW1, answerW2));
            //allFlashcards = flashcardDatabase.getAllCards();

            mainQuestion.setText(question);
            mainAnswer.setText(answer);
            answer3.setText(answer);
            correctAnswer.setText(answer);
            answer1.setText(answerW1);
            wrongAnswer1.setText(answerW1);
            answer2.setText(answerW2);
            wrongAnswer2.setText(answerW2);
        }
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    @Override
    public void onClick(View v)
    {
        final TextView flashQuest = findViewById(R.id.flashcard_question);
        final TextView flashAnswer = findViewById(R.id.flashcard_anwser);
        final TextView answer1 = findViewById(R.id.flashcard_answer1);
        final TextView answer1Wrong = findViewById(R.id.flashcard_answerWrong1);
        final TextView answer2 = findViewById(R.id.flashcard_answer2);
        final TextView answer2Wrong = findViewById(R.id.flashcard_answerWrong2);
        final TextView answer3 = findViewById(R.id.flashcard_answer3);
        final TextView answer3Correct = findViewById(R.id.flashcard_answerCorrect3);
        final ImageView toggle = findViewById(R.id.toggle_choices_visibility);
        final Button createCardBut = findViewById(R.id.plus_button);
        final Button trashCan = findViewById(R.id.trashCan);
        // next button animations
        final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(),
                R.anim.left_out);
        final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(),
                R.anim.right_in);
        //  previous button animation
        final Animation leftInAnim = AnimationUtils.loadAnimation(v.getContext(),
                R.anim.left_in);
        final Animation rightOutAnim = AnimationUtils.loadAnimation(v.getContext(),
                R.anim.right_out);
        // Anim motion
        leftOutAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                // this method is called when the animation first starts
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                if(animation == leftOutAnim) {
                    flashQuest.startAnimation(rightInAnim);
                    answer1.startAnimation(rightInAnim);
                    answer2.startAnimation(rightInAnim);
                    answer3.startAnimation(rightInAnim);
                }
                else {
                    flashQuest.startAnimation(rightOutAnim);
                    answer1.startAnimation(rightOutAnim);
                    answer2.startAnimation(rightOutAnim);
                    answer3.startAnimation(rightOutAnim);
                }
                // this method is called when the animation is finished playing
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
                // we don't need to worry about this method
            }
        });
        switch (v.getId())
        {
            case R.id.flashcard_question:
            {
                if(!answerOn)
                {
                    // Setting reveal Animation
                    // get the center for the clipping circle
                    int cx = flashAnswer.getWidth() / 2;
                    int cy = flashAnswer.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(flashAnswer, cx, cy, 0f, finalRadius);

                    // set visibility
                    flashQuest.setVisibility(View.INVISIBLE);
                    flashAnswer.setVisibility(View.VISIBLE);

                    // Call anim
                    anim.setDuration(3000);
                    anim.start();

                    answerOn = true;
                }
            }
            break;
            case R.id.flashcard_anwser:
            {
                if(answerOn)
                {
                    // Setting reveal Animation
                    // get the center for the clipping circle
                    int cx = flashQuest.getWidth() / 2;
                    int cy = flashQuest.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim2 = ViewAnimationUtils.createCircularReveal(flashQuest, cx, cy, 0f, finalRadius);

                    // set visability
                    flashQuest.setVisibility(View.VISIBLE);
                    flashAnswer.setVisibility(View.INVISIBLE);

                    // Call anim
                    anim2.setDuration(3000);
                    anim2.start();

                    answerOn = false;
                }
            }
            break;
            case R.id.flashcard_answer1:
            {
                if(!firstQuestion)
                {
                    answer1.setVisibility(View.INVISIBLE);
                    answer1Wrong.setVisibility(View.VISIBLE);
                    if(!greenOn)
                    {
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
                if(firstQuestion)
                {
                    answer1.setVisibility(View.VISIBLE);
                    answer1Wrong.setVisibility(View.INVISIBLE);
                    if(greenOn)
                    {
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
                if(!secondQuestion)
                {
                    answer2.setVisibility(View.INVISIBLE);
                    answer2Wrong.setVisibility(View.VISIBLE);
                    if(!greenOn)
                    {
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
                if(secondQuestion)
                {
                    answer2.setVisibility(View.VISIBLE);
                    answer2Wrong.setVisibility(View.INVISIBLE);
                    if(greenOn)
                    {
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
                if(!greenOn)
                {
                    answer3.setVisibility(View.INVISIBLE);
                    answer3Correct.setVisibility(View.VISIBLE);
                    greenOn = true;
                }
            }
            break;
            case R.id.flashcard_answerCorrect3:
            {
                if(greenOn)
                {
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
                }else
                {
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
            case R.id.plus_button:
            {
                Intent CreateCard = new Intent(MainActivity.this,
                        CreateCardActivity.class);
                MainActivity.this.startActivityForResult(CreateCard, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
            break;
            case R.id.edit_button:
            {
                Intent EditCard = new Intent(MainActivity.this,
                        CreateCardActivity.class);
                EditCard.putExtra("question", flashQuest.getText().toString());
                MainActivity.this.startActivityForResult(EditCard, 99);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
            break;
            case R.id.forwardNextButton: {
                try {
                    currentCardDisplayedIndex++;
                    if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                        currentCardDisplayedIndex = 0;
                    } else {
                        flashQuest.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        flashAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        answer1.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        answer1Wrong.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        answer2.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        answer2Wrong.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        answer3.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        answer3Correct.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        flashQuest.startAnimation(leftOutAnim);
                        answer1.startAnimation(leftOutAnim);
                        answer2.startAnimation(leftOutAnim);
                        answer3.startAnimation(leftOutAnim);

                    }
                } catch (Exception e) {
                    currentCardDisplayedIndex = currentCardDisplayedIndex;
                }
            }
            break;
            case R.id.backNextButton: {
                try {
                    if (currentCardDisplayedIndex == allFlashcards.size() - 1
                            && currentCardDisplayedIndex != 0)
                    {
                        currentCardDisplayedIndex--;
                    }
                    else if (currentCardDisplayedIndex == 0)
                    {
                        currentCardDisplayedIndex = allFlashcards.size() - 1;
                    }
                    else
                    {
                        flashQuest.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        flashAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        answer1.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        answer1Wrong.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        answer2.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        answer2Wrong.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        answer3.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        answer3Correct.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        flashQuest.startAnimation(leftInAnim);
                        answer1.startAnimation(leftInAnim);
                        answer2.startAnimation(leftInAnim);
                        answer3.startAnimation(leftInAnim);
                    }
                } catch (Exception e) {
                    currentCardDisplayedIndex = currentCardDisplayedIndex;
                }

            }
            break;
            case R.id.trashCan: {
                try {
                    if(allFlashcards.size() <= 0) {
                        Toast.makeText(getApplicationContext(),
                                "No other FlashCards", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        flashcardDatabase.deleteCard(flashQuest.getText().toString());
                        allFlashcards = flashcardDatabase.getAllCards();
                        Toast.makeText(getApplicationContext(),
                                "FlashCard Deleted", Toast.LENGTH_SHORT).show();
                        currentCardDisplayedIndex--;
                        if(currentCardDisplayedIndex <= allFlashcards.size() -1)
                        {
                            currentCardDisplayedIndex =0;
                        }
                        setFlashcardDatabase();

                    }
                }
                catch (Exception e)
                {
                    currentCardDisplayedIndex = currentCardDisplayedIndex;
                }

            }
            break;
        }
        if(greenOn)
        {
            isShowingAnswers = true;
        }else
        {
            isShowingAnswers = false;
            greenOn = false;
        }

    }

    public void setFlashcardDatabase()
    {

        // set the question and answer TextViews with data from the database

        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion()); // only get an error when accessing the database
        ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
        ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
        ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());

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
