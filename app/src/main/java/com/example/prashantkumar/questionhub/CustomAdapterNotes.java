package com.example.prashantkumar.questionhub;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class CustomAdapterNotes extends ArrayAdapter<HashMap<String, String>> {

    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_EXPLANATION = "explanation";
    private static final String KEY_OPT_A = "optA";
    private static final String KEY_OPT_B = "optB";
    private static final String KEY_OPT_C = "optC";
    private static final String KEY_OPT_D = "optD";

    private static final String COLOR_GREEN = "#339933";

    CustomAdapterNotes(Context context, ArrayList<HashMap<String, String>> itemList){
        super(context, R.layout.custom_row_notes, itemList);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View customView = layoutInflater.inflate(R.layout.custom_row_notes, parent, false);

        HashMap<String, String> item = getItem(position);
        TextView textViewId = (TextView) customView.findViewById(R.id.id);
        TextView textViewQuestion = (TextView) customView.findViewById(R.id.questionTextV);
        Button buttonOptA = (Button) customView.findViewById(R.id.optAButton);
        Button buttonOptB = (Button) customView.findViewById(R.id.optBButton);
        Button buttonOptC = (Button) customView.findViewById(R.id.optCButton);
        Button buttonOptD = (Button) customView.findViewById(R.id.optDButton);
        TextView textViewAnswer = (TextView) customView.findViewById(R.id.answerTextV);
        TextView textViewExplanation = (TextView) customView.findViewById(R.id.explanationTextV);

        textViewId.setText(item.get(KEY_ID));
        textViewQuestion.setText(item.get(KEY_QUESTION));
        textViewAnswer.setText("Correct : " + item.get(KEY_ANSWER));
        textViewExplanation.setText("Explanation : " + item.get(KEY_EXPLANATION));

        buttonOptA.setText(item.get(KEY_OPT_A));
        buttonOptB.setText(item.get(KEY_OPT_B));
        buttonOptC.setText(item.get(KEY_OPT_C));
        buttonOptD.setText(item.get(KEY_OPT_D));

        if(item.get(KEY_OPT_A).equals(item.get(KEY_ANSWER))){
            buttonOptA.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        if(item.get(KEY_OPT_B).equals(item.get(KEY_ANSWER))){
            buttonOptB.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        if(item.get(KEY_OPT_C).equals(item.get(KEY_ANSWER))){
            buttonOptC.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }
        if(item.get(KEY_OPT_D).equals(item.get(KEY_ANSWER))){
            buttonOptD.setBackgroundColor(Color.parseColor(COLOR_GREEN));
        }

        Button buttonDelete = (Button) customView.findViewById(R.id.deleteButton);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> item = getItem(position);
                int qId = Integer.parseInt(item.get(KEY_ID));
                DatabaseHandler db = new DatabaseHandler(getContext());
                db.deleteNote(qId);
                remove(getItem(position));
            }
        });

        return customView;
    }

}
