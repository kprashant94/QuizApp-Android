package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class QuestAnsActivity extends Activity {

    private static final String TAG_EID = "eId";
    private static final String TAG_TID = "tId";

    private static final String COLOR_GREEN = "#339933";
    private static final String COLOR_RED = "#ff0000";
    private static final String COLOR_OPTION = "#ffffff";

    int qNo = 0;
    int score = 0;
    Question question;
    List<Question> questions;
    int qId;

    TextView textViewQuestion, textViewAnswer, textViewExplanation, textViewScore;
    Button buttonNext, buttonFinish, buttonAddToNotes, buttonOptA, buttonOptB, buttonOptC, buttonOptD, buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_ans);

        textViewQuestion = (TextView) findViewById(R.id.questionTextV);
        buttonOptA = (Button) findViewById(R.id.optAButton);
        buttonOptB = (Button) findViewById(R.id.optBButton);
        buttonOptC = (Button) findViewById(R.id.optCButton);
        buttonOptD = (Button) findViewById(R.id.optDButton);
        textViewAnswer = (TextView) findViewById(R.id.answerTextV);
        textViewExplanation = (TextView) findViewById(R.id.explanationTextV);
        textViewScore = (TextView) findViewById(R.id.scoreTextV);
        buttonNext = (Button) findViewById(R.id.nextButton);
        buttonFinish = (Button) findViewById(R.id.finishButton);
        buttonAddToNotes = (Button) findViewById(R.id.addToNotesButton);
        buttonHome = (Button) findViewById(R.id.homeButton);

        Intent in = getIntent();
        int eId = Integer.parseInt(in.getStringExtra(TAG_EID));
        int tId = Integer.parseInt(in.getStringExtra(TAG_TID));

        DatabaseHandler db = new DatabaseHandler(this);

        if(eId == -1 & tId == -1){
            questions = db.getQuestions();
        }
        else if(tId == -1){
            questions = db.getQuestionsExam(eId);
        }
        else if(eId == -1){
            questions = db.getQuestionsTopic(tId);
        }
        else{
            questions = db.getQuestionsExamTopic(eId, tId);
        }

        if(questions.size() > 0) {
            question = questions.get(qNo);
            qNo++;

            qId = question.getId();
            textViewQuestion.setText(question.getQuestion());
            buttonOptA.setText(question.getOptA());
            buttonOptB.setText(question.getOptB());
            buttonOptC.setText(question.getOptC());
            buttonOptD.setText(question.getOptD());
            textViewAnswer.setText("Correct : " + question.getAnswer());
            textViewExplanation.setText("Explanation : " + question.getExplanation());
        }
    }

    public void onClickOptA(View view){

        buttonAddToNotes.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.VISIBLE);
        textViewAnswer.setVisibility(View.VISIBLE);
        textViewExplanation.setVisibility(View.VISIBLE);

        buttonOptA.setEnabled(false);
        buttonOptB.setEnabled(false);
        buttonOptC.setEnabled(false);
        buttonOptD.setEnabled(false);

        if(buttonOptA.getText().toString().equals(question.getAnswer())){
            score++;
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        else{
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_RED));
        }

        if(buttonOptB.getText().toString().equals(question.getAnswer())){
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptC.getText().toString().equals(question.getAnswer())){
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptD.getText().toString().equals(question.getAnswer())){
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
    }

    public void onClickOptB(View view){

        buttonAddToNotes.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.VISIBLE);
        textViewAnswer.setVisibility(View.VISIBLE);
        textViewExplanation.setVisibility(View.VISIBLE);

        buttonOptA.setEnabled(false);
        buttonOptB.setEnabled(false);
        buttonOptC.setEnabled(false);
        buttonOptD.setEnabled(false);


        if(buttonOptA.getText().toString().equals(question.getAnswer())){
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptB.getText().toString().equals(question.getAnswer())){
            score++;
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        else{
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_RED));
        }

        if(buttonOptC.getText().toString().equals(question.getAnswer())){
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        if(buttonOptD.getText().toString().equals(question.getAnswer())){
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
    }

    public void onClickOptC(View view){

        buttonAddToNotes.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.VISIBLE);
        textViewAnswer.setVisibility(View.VISIBLE);
        textViewExplanation.setVisibility(View.VISIBLE);

        buttonOptA.setEnabled(false);
        buttonOptB.setEnabled(false);
        buttonOptC.setEnabled(false);
        buttonOptD.setEnabled(false);


        if(buttonOptA.getText().toString().equals(question.getAnswer())){
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptB.getText().toString().equals(question.getAnswer())){
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptC.getText().toString().equals(question.getAnswer())){
            score++;
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        else{
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_RED));
        }

        if(buttonOptD.getText().toString().equals(question.getAnswer())){
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
    }

    public void onClickOptD(View view){

        buttonAddToNotes.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.VISIBLE);
        textViewAnswer.setVisibility(View.VISIBLE);
        textViewExplanation.setVisibility(View.VISIBLE);

        buttonOptA.setEnabled(false);
        buttonOptB.setEnabled(false);
        buttonOptC.setEnabled(false);
        buttonOptD.setEnabled(false);


        if(buttonOptA.getText().toString().equals(question.getAnswer())){
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptB.getText().toString().equals(question.getAnswer())){
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptC.getText().toString().equals(question.getAnswer())){
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        if(buttonOptD.getText().toString().equals(question.getAnswer())){
            score++;
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        else{
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_RED));
        }
    }

    public void onClickButtonNext(View view){
        if(qNo < questions.size()){
            question = questions.get(qNo);
            qNo++;
            qId = question.getId();
            textViewQuestion.setText(question.getQuestion());
            buttonOptA.setText(question.getOptA());
            buttonOptB.setText(question.getOptB());
            buttonOptC.setText(question.getOptC());
            buttonOptD.setText(question.getOptD());
            textViewAnswer.setText("Correct : " + question.getAnswer());
            textViewExplanation.setText("Explanation : " + question.getExplanation());

            buttonOptA.setEnabled(true);
            buttonOptB.setEnabled(true);
            buttonOptC.setEnabled(true);
            buttonOptD.setEnabled(true);

            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_OPTION));
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_OPTION));
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_OPTION));
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_OPTION));

            textViewAnswer.setVisibility(View.GONE);
            textViewExplanation.setVisibility(View.GONE);
            buttonAddToNotes.setVisibility(View.GONE);
            buttonNext.setVisibility(View.GONE);
            buttonFinish.setVisibility(View.GONE);
        }
        else{
            onClickButtonFinish(view);
        }

    }

    public void onClickButtonFinish(View view){
        textViewQuestion.setVisibility(View.GONE);
        textViewAnswer.setVisibility(View.GONE);
        textViewExplanation.setVisibility(View.GONE);
        buttonOptA.setVisibility(View.GONE);
        buttonOptB.setVisibility(View.GONE);
        buttonOptC.setVisibility(View.GONE);
        buttonOptD.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);
        buttonFinish.setVisibility(View.GONE);
        buttonAddToNotes.setVisibility(View.GONE);

        textViewScore.setText("Correct : " + Integer.toString(score) + "/" + Integer.toString(qNo));
        textViewScore.setVisibility(View.VISIBLE);
        buttonHome.setVisibility(View.VISIBLE);

    }

    public void onClickButtonAddToNotes(View view){

        DatabaseHandler db = new DatabaseHandler(this);
        db.addQuestionNote(qId);

        Toast.makeText(getApplicationContext(), "Added to your notes", Toast.LENGTH_SHORT ).show();
    }

    public void onClickButtonHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}