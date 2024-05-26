package com.example.quize;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestionTextView;
    TextView questionTextview;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalquestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        totalquestionTextView = findViewById(R.id.idTVQuestion);
        questionTextview = findViewById(R.id.idTVQus_write);
        ansA = findViewById(R.id.idBAns_A);
        ansB = findViewById(R.id.idBAns_B);
        ansC = findViewById(R.id.idBAns_C);
        ansD = findViewById(R.id.idBAns_D);
        submitBtn = findViewById(R.id.idBSubmit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalquestionTextView.setText("Total question :"+totalquestion);

        loadNewQuestion();

    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if (clickedButton.getId()==R.id.idBSubmit){
            if (selectedAnswer.equals(QuestionAnswer.correctanswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }


    void loadNewQuestion(){

        if (currentQuestionIndex == totalquestion){
            finishQuiz();
            return;
        }
        questionTextview.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz() {
        String passStatus = "";
        if (score > totalquestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(" Score is " + score + " out of " + totalquestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}