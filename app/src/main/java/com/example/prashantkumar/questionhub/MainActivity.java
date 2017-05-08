package com.example.prashantkumar.questionhub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG_ID = "id";
    private static final String TAG_EID = "eId";
    private static final String TAG_TID = "tId";
    private static final String TAG_QID = "qId";
    private static final String TAG_EXAMS = "exams";
    private static final String TAG_TOPICS = "topics";
    private static final String TAG_QUESTIONS = "questions";
    private static final String TAG_EXAMS_TOPICS = "exams_topics";
    private static final String TAG_TOPICS_QUESTIONS = "topics_questions";
    private static final String TAG_EXAMS_TOPICS_QUESTIONS = "exams_topics_questions";
    private static final String TAG_NAME = "name";
    private static final String TAG_QUESTION = "question";
    private static final String TAG_ANSWER = "answer";
    private static final String TAG_EXPLANATION = "explanation";
    private static final String TAG_OPT_A = "optA";
    private static final String TAG_OPT_B = "optB";
    private static final String TAG_OPT_C = "optC";
    private static final String TAG_OPT_D = "optD";
    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNetworkAvailable()){
            new UpdateData().execute();
        }
        else{
            Toast.makeText(getApplicationContext(), "Connect to the internet to update content." ,Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickButtonExams(View view){
        Intent intent = new Intent(this, ExamsListActivity.class);
        startActivity(intent);
    }

    public void onClickButtonTopics(View view){
        Intent intent = new Intent(this, TopicsListActivity.class);
        intent.putExtra(TAG_EID, "-1");
        startActivity(intent);
    }

    public void onClickButtonNotes(View view){
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class UpdateData extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args) {

            JSONParser jParser = new JSONParser();
            JSONArray exams, topics, questions, examsTopics, topicsQuestions, examsTopicsQuestions;

            //String url_get_data = "https://quizhub.000webhostapp.com/get_data.php";
            String url_get_data = "https://quizhub.000webhostapp.com/get_data.php";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_get_data, "GET", params);

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    exams = json.getJSONArray(TAG_EXAMS);
                    topics = json.getJSONArray(TAG_TOPICS);
                    questions = json.getJSONArray(TAG_QUESTIONS);
                    examsTopics = json.getJSONArray(TAG_EXAMS_TOPICS);
                    topicsQuestions = json.getJSONArray(TAG_TOPICS_QUESTIONS);
                    examsTopicsQuestions = json.getJSONArray(TAG_EXAMS_TOPICS_QUESTIONS);

                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    String[] idsReceived = new String[exams.length()];
                    for (int i = 0; i < exams.length(); i++) {
                        JSONObject c = exams.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        String name = c.getString(TAG_NAME);

                        idsReceived[i] = Integer.toString(id);
                        Exam exam = new Exam(id, name);
                        if(db.hasExam(id)){
                            db.updateExam(exam);
                        }
                        else{
                            db.addExam(exam);
                        }
                    }
                    db.deleteExamsExcept(idsReceived);

                    idsReceived = new String[topics.length()];
                    for (int i = 0; i < topics.length(); i++) {
                        JSONObject c = topics.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        String name = c.getString(TAG_NAME);

                        idsReceived[i] = Integer.toString(id);
                        Topic topic = new Topic(id, name);
                        if(db.hasTopic(id)){
                            db.updateTopic(topic);
                        }
                        else{
                            db.addTopic(topic);
                        }
                    }
                    db.deleteTopicsExcept(idsReceived);

                    int k=0;
                    List<Question> qNotes = db.getQuestionNotes();
                    idsReceived = new String[questions.length() + qNotes.size()];
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject c = questions.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        String ques = c.getString(TAG_QUESTION);
                        String ans = c.getString(TAG_ANSWER);
                        String exp = c.getString(TAG_EXPLANATION);
                        String optA = c.getString(TAG_OPT_A);
                        String optB = c.getString(TAG_OPT_B);
                        String optC = c.getString(TAG_OPT_C);
                        String optD = c.getString(TAG_OPT_D);

                        idsReceived[i] = Integer.toString(id);
                        k++;
                        Question question = new Question(id, ques, ans, exp, optA, optB, optC, optD);
                        if(db.hasQuestion(id)){
                            db.updateQuestion(question);
                        }
                        else{
                            db.addQuestion(question);
                        }
                    }
                    for(Question ques : qNotes){
                        idsReceived[k] = Integer.toString(ques.getId());
                        k++;
                    }
                    db.deleteQuestionsExcept(idsReceived);

                    idsReceived = new String[examsTopics.length()];
                    for (int i = 0; i < examsTopics.length(); i++) {
                        JSONObject c = examsTopics.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        int eId = Integer.parseInt(c.getString(TAG_EID));
                        int tId = Integer.parseInt(c.getString(TAG_TID));

                        idsReceived[i] = Integer.toString(id);
                        if( !(db.hasExamsTopics(id)) ){
                            db.addExamTopic(id, eId, tId);
                        }
                    }
                    db.deleteExamsTopicsExcept(idsReceived);

                    idsReceived = new String[topicsQuestions.length()];
                    for (int i = 0; i < topicsQuestions.length(); i++) {
                        JSONObject c = topicsQuestions.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        int tId = Integer.parseInt(c.getString(TAG_TID));
                        int qId = Integer.parseInt(c.getString(TAG_QID));

                        idsReceived[i] = Integer.toString(id);
                        if( !(db.hasTopicsQuestions(id))){
                            db.addTopicQuestion(id, tId, qId);
                        }
                    }
                    db.deleteTopicsQuestionsExcept(idsReceived);

                    idsReceived = new String[examsTopicsQuestions.length()];
                    for (int i = 0; i < examsTopicsQuestions.length(); i++) {
                        JSONObject c = examsTopicsQuestions.getJSONObject(i);

                        int id = Integer.parseInt(c.getString(TAG_ID));
                        int eId = Integer.parseInt(c.getString(TAG_EID));
                        int tId = Integer.parseInt(c.getString(TAG_TID));
                        int qId = Integer.parseInt(c.getString(TAG_QID));

                        idsReceived[i] = Integer.toString(id);
                        if( !(db.hasExamsTopicsQuestions(id))){
                            db.addExamTopicQuestion(id, eId, tId, qId);
                        }
                    }
                    db.deleteExamsTopicsQuestionsExcept(idsReceived);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
