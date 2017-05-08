package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class QuizActivity extends Activity {

    private static final String TAG_EID = "eId";
    private static final String TAG_TID = "tId";

    int qNo = 0;
    int score = 0;
    int eId, tId;
    Question question;
    List<Question> questions;
    long timeElapsed;
    CountDownTimer timer;

    TextView textViewQuestion, textViewScore, textViewBest, textViewCurrentScore, textViewTimer;
    TextView textViewTime1, textViewTime2;
    Button buttonOptA, buttonOptB, buttonOptC, buttonOptD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = (TextView) findViewById(R.id.questionTextV);
        textViewScore = (TextView) findViewById(R.id.scoreTextV);
        textViewTimer = (TextView) findViewById(R.id.timerTextV);
        textViewBest = (TextView) findViewById(R.id.bestScoreTextV);
        textViewTime1 = (TextView) findViewById(R.id.time1TextV);
        textViewTime2 = (TextView) findViewById(R.id.time2TextV);
        textViewCurrentScore = (TextView) findViewById(R.id.currentScoreTextV);
        buttonOptA = (Button) findViewById(R.id.optAButton);
        buttonOptB = (Button) findViewById(R.id.optBButton);
        buttonOptC = (Button) findViewById(R.id.optCButton);
        buttonOptD = (Button) findViewById(R.id.optDButton);

        Intent in = getIntent();
        eId = Integer.parseInt(in.getStringExtra(TAG_EID));
        tId = Integer.parseInt(in.getStringExtra(TAG_TID));

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
            textViewCurrentScore.setText("Score : " + Integer.toString(score));
            textViewQuestion.setText(question.getQuestion());
            buttonOptA.setText(question.getOptA());
            buttonOptB.setText(question.getOptB());
            buttonOptC.setText(question.getOptC());
            buttonOptD.setText(question.getOptD());
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        final long totalSeconds = 3600*3;
        long intervalSeconds = 1;

        timer = new CountDownTimer(totalSeconds*1000, intervalSeconds*1000 ) {
            @Override
            public void onTick(long l) {
                timeElapsed = (totalSeconds*1000 - l)/1000;
                textViewTimer.setText(String.format("Time : %s", String.format("%02d:%02d:%02d", timeElapsed / 3600, (timeElapsed % 3600) / 60, timeElapsed % 60)));

            }

            @Override
            public void onFinish() {
                Log.d("Done", "Time is up");
            }
        };

        timer.start();

    }

    public void onClickOptA(View view){

        if(buttonOptA.getText().toString().equals(question.getAnswer())){
            score++;
            textViewCurrentScore.setText("Score : " + Integer.toString(score));
            next(view);
        }
        else{
            finish(view);
        }
    }

    public void onClickOptB(View view){

        if(buttonOptB.getText().toString().equals(question.getAnswer())){
            score++;
            textViewCurrentScore.setText("Score : " + Integer.toString(score));
            next(view);
        }
        else{
            finish(view);
        }
    }

    public void onClickOptC(View view){

        if(buttonOptC.getText().toString().equals(question.getAnswer())){
            score++;
            textViewCurrentScore.setText("Score : " + Integer.toString(score));
            next(view);
        }
        else{
            finish(view);
        }
    }

    public void onClickOptD(View view){

        if(buttonOptD.getText().toString().equals(question.getAnswer())){
            score++;
            textViewCurrentScore.setText("Score : " + Integer.toString(score));
            next(view);
        }
        else{
            finish(view);
        }
    }

    public void next(View view){
        if(qNo < questions.size()){
            question = questions.get(qNo);
            qNo++;
            textViewQuestion.setText(question.getQuestion());
            buttonOptA.setText(question.getOptA());
            buttonOptB.setText(question.getOptB());
            buttonOptC.setText(question.getOptC());
            buttonOptD.setText(question.getOptD());
        }
        else{
            finish(view);
        }

    }

    public void finish(View view){

        textViewQuestion.setVisibility(View.GONE);
        buttonOptA.setVisibility(View.GONE);
        buttonOptB.setVisibility(View.GONE);
        buttonOptC.setVisibility(View.GONE);
        buttonOptD.setVisibility(View.GONE);
        textViewScore.setVisibility(View.VISIBLE);
        textViewBest.setVisibility(View.VISIBLE);
        textViewTimer.setVisibility(View.GONE);
        textViewCurrentScore.setVisibility(View.GONE);

        timer.cancel();

        DatabaseHandler db = new DatabaseHandler(this);
        Performance performance;

        if(db.hasPerformance(eId, tId)){
            performance = db.getPerformance(eId, tId);
            if(score > performance.getBestScore()){
                performance.setBestScore(score);
                performance.setTimeTaken(Long.toString(timeElapsed));
                db.updatePerformance(performance);
            }
            else if(score == performance.getBestScore()){
                if(timeElapsed < Long.parseLong(performance.getTimeTaken())){
                    performance.setTimeTaken(Long.toString(timeElapsed));
                    db.updatePerformance(performance);
                }
            }
        }
        else{
            performance = new Performance(score, Long.toString(timeElapsed), eId, tId);
            db.addPerformance(performance);
        }

        textViewScore.setText(Integer.toString(score));
        textViewTime1.setText(String.format("%02d:%02d:%02d", timeElapsed / 3600, (timeElapsed % 3600) / 60, timeElapsed % 60));
        textViewBest.setText("Best : " + Integer.toString(performance.getBestScore()));
        Long timeBestScore = Long.parseLong(performance.getTimeTaken());
        textViewTime2.setText(String.format("%02d:%02d:%02d", timeBestScore / 3600, (timeBestScore % 3600) / 60, timeBestScore % 60));
    }
}
