package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotesActivity extends Activity {

    private static final String Key_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_Answer = "answer";
    private static final String KEY_EXPLANATION = "explanation";
    private static final String KEY_OPT_A = "optA";
    private static final String KEY_OPT_B = "optB";
    private static final String KEY_OPT_C = "optC";
    private static final String KEY_OPT_D = "optD";


    ArrayList<HashMap<String, String>> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        loadQuestionsList();
        if(questionList.size() == 0){
            TextView textViewEmpty = (TextView) findViewById(R.id.emptyTextV);
            textViewEmpty.setVisibility(View.VISIBLE);
        }
        else{
            ListAdapter listAdapter = new CustomAdapterNotes(this, questionList);
            ListView listView = (ListView) findViewById(android.R.id.list);
            listView.setAdapter(listAdapter);
        }

    }

    public void loadQuestionsList(){
        DatabaseHandler db = new DatabaseHandler(this);
        List<Question> questions = db.getQuestionNotes();

        for(Question question : questions){
            HashMap<String, String> map = new HashMap<>();
            map.put(Key_ID, Integer.toString(question.getId()));
            map.put(KEY_QUESTION, question.getQuestion());
            map.put(KEY_Answer, question.getAnswer());
            map.put(KEY_EXPLANATION, question.getExplanation());
            map.put(KEY_OPT_A, question.getOptA());
            map.put(KEY_OPT_B, question.getOptB());
            map.put(KEY_OPT_C, question.getOptC());
            map.put(KEY_OPT_D, question.getOptD());

            questionList.add(map);
        }

    }

}
