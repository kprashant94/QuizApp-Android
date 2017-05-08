package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class TopicsListActivity extends Activity {

    ArrayList<HashMap<String, String>> topicsList = new ArrayList<>();

    private static final String TAG_ID = "id";
    private static final String TAG_EID = "eId";
    private static final String TAG_TID = "tId";
    private static final String TAG_NAME = "name";
    private static final String MODE_PRACTICE = "Practice";
    private static final String MODE_QUIZ = "Quiz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_list);

        Intent in = getIntent();
        final int eId = Integer.parseInt(in.getStringExtra(TAG_EID));
        String examName = in.getStringExtra(TAG_NAME);

        TextView textViewExamName = (TextView) findViewById(R.id.examNameTextV);

        if(eId == -1){
            textViewExamName.setText("Topics");
        }
        else{
            textViewExamName.setText(examName);
        }

        loadTopicsList(eId);

        ListAdapter listAdapter = new CustomAdapter(this, topicsList);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        TextView textViewId = (TextView) view.findViewById(R.id.id);
                        TextView textViewName = (TextView) view.findViewById(R.id.nameTextV);
                        final String tId = textViewId.getText().toString();
                        final String name = textViewName.getText().toString();

                        AlertDialog.Builder builder = new AlertDialog.Builder(TopicsListActivity.this);
                        final String[] modes = {MODE_PRACTICE, MODE_QUIZ};
                        builder.setTitle("Choose Mode");
                        builder.setItems(modes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int mode) {

                                if(modes[mode].equals(MODE_PRACTICE)){
                                    Intent intent = new Intent(getApplicationContext(), QuestAnsActivity.class);
                                    intent.putExtra(TAG_EID, Integer.toString(eId));
                                    intent.putExtra(TAG_TID, tId);
                                    intent.putExtra(TAG_NAME, name);
                                    startActivity(intent);
                                }
                                if(modes[mode].equals(MODE_QUIZ)){
                                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                                    intent.putExtra(TAG_EID, Integer.toString(eId));
                                    intent.putExtra(TAG_TID, tId);
                                    intent.putExtra(TAG_NAME, name);
                                    startActivity(intent);
                                }
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
        );

    }

    public void loadTopicsList(int eId){
        DatabaseHandler db = new DatabaseHandler(this);
        List<Topic> topics;
        if(eId == -1){
            topics = db.getTopics();
        }
        else{
            topics = db.getTopics(eId);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put(TAG_ID, Integer.toString(-1));
        map.put(TAG_NAME, "All");
        topicsList.add(map);

        for(Topic topic : topics){
            map = new HashMap<>();
            map.put(TAG_ID, Integer.toString(topic.getId()));
            map.put(TAG_NAME, topic.getName());

            topicsList.add(map);
        }

    }
}
