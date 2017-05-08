package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExamsListActivity extends Activity {

    ArrayList<HashMap<String, String>> examsList = new ArrayList<>();

    private static final String TAG_ID = "id";
    private static final String TAG_EID = "eId";
    private static final String TAG_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_list);

        loadExamsList();
        ListAdapter listAdapter = new CustomAdapter(this, examsList);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        TextView textViewId = (TextView) view.findViewById(R.id.id);
                        TextView textViewName = (TextView) view.findViewById(R.id.nameTextV);
                        String eId = textViewId.getText().toString();
                        String name = textViewName.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), TopicsListActivity.class);
                        intent.putExtra(TAG_EID, eId);
                        intent.putExtra(TAG_NAME, name);
                        startActivity(intent);
                    }
                }
        );


    }

    public void loadExamsList(){
        DatabaseHandler db = new DatabaseHandler(this);
        List<Exam> exams = db.getExams();

        for(Exam exam : exams){
            HashMap<String, String> map = new HashMap<>();
            map.put(TAG_ID, Integer.toString(exam.getId()));
            map.put(TAG_NAME, exam.getName());
            examsList.add(map);
        }
    }

}
