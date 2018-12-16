package com.hydroliquid.mjben.flashapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCardActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText passQuest;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        // Buttons
        Button cancelCardBut = findViewById(R.id.x_mark);
        Button saveCardBut = findViewById(R.id.save_mark);
        cancelCardBut.setOnClickListener(this);
        saveCardBut.setOnClickListener(this);

        String question = getIntent().getStringExtra("flashQuest");
        passQuest = findViewById(R.id.create_Question);
    }

    public void onClick(View v)
    {
        Button cancelCardBut = findViewById(R.id.x_mark);
        // EditText
        EditText newQuestion = findViewById(R.id.create_Question);
        EditText newAnswer = findViewById(R.id.create_Answer);
        EditText newWrongAnswer1 = findViewById(R.id.create_Answer2);
        EditText newWrongAnswer2 = findViewById(R.id.create_Answer3);



        switch (v.getId())
        {
            case R.id.x_mark:
            {
                Intent CancelCard = new Intent(CreateCardActivity.this,
                        MainActivity.class);

                CreateCardActivity.this.startActivity(CancelCard);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
            case R.id.save_mark:
            {
                //Intent SaveCard = new Intent(CreateCardActivity.this,
                //        MainActivity.class);
                Intent data = new Intent();
                data.putExtra("question", newQuestion.getText().toString());
                data.putExtra("answer", newAnswer.getText().toString());
                data.putExtra("answerW1", newWrongAnswer1.getText().toString());
                data.putExtra("answerW2", newWrongAnswer2.getText().toString());
                setResult(RESULT_OK, data);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
                //CreateCardActivity.this.startActivity(SaveCard);

            }
        }
    }
}
