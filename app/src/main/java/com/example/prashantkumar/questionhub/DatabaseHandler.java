package com.example.prashantkumar.questionhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "QuestionHub";
    // Table names
    private static final String TABLE_EXAMS = "Exams";
    private static final String TABLE_TOPICS = "Topics";
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_EXAMS_TOPICS = "ExamsTopics";
    private static final String TABLE_TOPICS_QUESTIONS = "TopicsQuestions";
    private static final String TABLE_EXAMS_TOPICS_QUESTIONS = "ExamsTopicsQuestions";
    private static final String TABLE_QUESTION_NOTES = "QuestionNotes";
    private static final String TABLE_PERFORMANCE = "Performance";

    // Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EID = "eId";
    private static final String KEY_TID = "tId";
    private static final String KEY_QID = "qId";
    private static final String KEY_NAME = "name";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_EXPLANATION = "explanation";
    private static final String KEY_OPT_A = "optA";
    private static final String KEY_OPT_B = "optB";
    private static final String KEY_OPT_C = "optC";
    private static final String KEY_OPT_D = "optD";
    private static final String KEY_BEST_SCORE = "bestScore";
    private static final String KEY_TIME_TAKEN = "timeTaken";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_EXAMS = "CREATE TABLE " + TABLE_EXAMS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " VARCHAR(255) NOT NULL"+
                ")";
        String CREATE_TABLE_TOPICS = "CREATE TABLE " + TABLE_TOPICS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " VARCHAR(255) NOT NULL"+
                ")";
        String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_QUESTION + " text NOT NULL, "+
                KEY_ANSWER + " VARCHAR(255) NOT NULL, "+
                KEY_EXPLANATION + " text, "+
                KEY_OPT_A + " VARCHAR(255) NOT NULL, "+
                KEY_OPT_B + " VARCHAR(255) NOT NULL, "+
                KEY_OPT_C + " VARCHAR(255) NOT NULL, "+
                KEY_OPT_D + " VARCHAR(255) NOT NULL" +
                ")";
        String CREATE_TABLE_EXAMS_TOPICS = "CREATE TABLE " + TABLE_EXAMS_TOPICS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_EID + " INTEGER NOT NULL, " +
                KEY_TID + " INTEGER NOT NULL, " +
                "UNIQUE(" + KEY_EID + ", " + KEY_TID + ")" +
                ")";
        String CREATE_TABLE_TOPICS_QUESTIONS = "CREATE TABLE " + TABLE_TOPICS_QUESTIONS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_TID + " INTEGER NOT NULL, " +
                KEY_QID + " INTEGER NOT NULL, " +
                "UNIQUE(" + KEY_TID + ", " + KEY_QID + ")" +
                ")";
        String CREATE_TABLE_EXAMS_TOPICS_QUESTIONS = "CREATE TABLE " + TABLE_EXAMS_TOPICS_QUESTIONS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_EID + " INTEGER NOT NULL, " +
                KEY_TID + " INTEGER NOT NULL, " +
                KEY_QID + " INTEGER NOT NULL, " +
                "UNIQUE(" + KEY_EID + ", " + KEY_TID + ", " + KEY_QID + ")" +
                ")";
        String CREATE_TABLE_QUESTION_NOTES = "CREATE TABLE " + TABLE_QUESTION_NOTES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_QID + " INTEGER NOT NULL UNIQUE " +
                ")";
        String CREATE_TABLE_PERFORMANCE = "CREATE TABLE " + TABLE_PERFORMANCE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_BEST_SCORE + " INTEGER NOT NULL, " +
                KEY_TIME_TAKEN + " VARCHAR(20) NOT NULL, " +
                KEY_EID + " INTEGER NOT NULL, " +
                KEY_TID + " INTEGER NOT NULL " +
                ")";

        db.execSQL(CREATE_TABLE_EXAMS);
        db.execSQL(CREATE_TABLE_TOPICS);
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_EXAMS_TOPICS);
        db.execSQL(CREATE_TABLE_TOPICS_QUESTIONS);
        db.execSQL(CREATE_TABLE_EXAMS_TOPICS_QUESTIONS);
        db.execSQL(CREATE_TABLE_QUESTION_NOTES);
        db.execSQL(CREATE_TABLE_PERFORMANCE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAMS_TOPICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPICS_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFORMANCE);

        //create table again
        onCreate(db);
    }

    //Adding new exam
    public void addExam(Exam exam){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, exam.getId());
        values.put(KEY_NAME, exam.getName());

        db.insert(TABLE_EXAMS, null, values);

        db.close();
    }

    //Adding new topic
    public void addTopic(Topic topic){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, topic.getId());
        values.put(KEY_NAME, topic.getName());

        db.insert(TABLE_TOPICS, null, values);

        db.close();
    }


    //Adding new question
    public void addQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, question.getId());
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_ANSWER, question.getAnswer());
        values.put(KEY_EXPLANATION, question.getExplanation());
        values.put(KEY_OPT_A, question.getOptA());
        values.put(KEY_OPT_B, question.getOptB());
        values.put(KEY_OPT_C, question.getOptC());
        values.put(KEY_OPT_D, question.getOptD());

        db.insert(TABLE_QUESTIONS, null, values);

        db.close();
    }

    //Adding new (Exam, Topic)
    public void addExamTopic(int id, int eId, int tId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_EID, eId);
        values.put(KEY_TID, tId);

        db.insert(TABLE_EXAMS_TOPICS, null, values);

        db.close();
    }

    //Adding new (Topic, Question)
    public void addTopicQuestion(int id, int tId, int qId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_TID, tId);
        values.put(KEY_QID, qId);

        db.insert(TABLE_TOPICS_QUESTIONS, null, values);

        db.close();
    }

    //Adding new (Exam, Topic, Question)
    public void addExamTopicQuestion(int id, int eId, int tId, int qId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_EID, eId);
        values.put(KEY_TID, tId);
        values.put(KEY_QID, qId);

        db.insert(TABLE_EXAMS_TOPICS_QUESTIONS, null, values);

        db.close();
    }

    //Adding new question note
    public void addQuestionNote(int qId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QID, qId);

        db.insert(TABLE_QUESTION_NOTES, null, values);

        db.close();
    }

    //Adding new Performance
    public void addPerformance(Performance performance){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BEST_SCORE, performance.getBestScore());
        values.put(KEY_TIME_TAKEN, performance.getTimeTaken());
        values.put(KEY_EID, performance.getEId());
        values.put(KEY_TID, performance.getTId());

        db.insert(TABLE_PERFORMANCE, null, values);

        db.close();
    }

    //Getting Exams
    public List<Exam> getExams(){
        List<Exam> exams = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EXAMS + "  ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Exam exam = new Exam();
                exam.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                exam.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));

                exams.add(exam);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return exams;
    }

    //Getting Topics
    public List<Topic> getTopics(){
        List<Topic> topics = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TOPICS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Topic topic = new Topic();
                topic.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                topic.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));

                topics.add(topic);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return topics;
    }


    //Getting Topics with eId
    public List<Topic> getTopics(int eId){
        List<Topic> topics = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TOPICS + " AS t INNER JOIN " +
                TABLE_EXAMS_TOPICS + " AS et ON t." +
                KEY_ID + " = et." + KEY_TID +
                " WHERE et." + KEY_EID + " = " + eId;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Topic topic = new Topic();
                topic.setId(cursor.getInt(cursor.getColumnIndex(KEY_TID)));
                topic.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));

                topics.add(topic);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return topics;
    }


    //Getting Questions
    public List<Question> getQuestions(){
        List<Question> questions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " ORDER BY RANDOM()";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(KEY_EXPLANATION)));
                question.setOptA(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
                question.setOptB(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
                question.setOptC(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
                question.setOptD(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));

                questions.add(question);

            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return questions;
    }

    //Getting Questions with tId
    public List<Question> getQuestionsTopic(int tId){
        List<Question> questions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " AS q INNER JOIN " +
                TABLE_TOPICS_QUESTIONS + " AS tq ON q." +
                KEY_ID + " = tq." + KEY_QID +
                " WHERE tq." + KEY_TID + " = " + tId + " ORDER BY RANDOM()";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(KEY_QID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(KEY_EXPLANATION)));
                question.setOptA(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
                question.setOptB(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
                question.setOptC(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
                question.setOptD(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));

                questions.add(question);

            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return questions;
    }

    //Getting Questions with eId
    public List<Question> getQuestionsExam(int eId){
        List<Question> questions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " AS q INNER JOIN " +
                TABLE_EXAMS_TOPICS_QUESTIONS + " AS etq ON q." +
                KEY_ID + " = etq." + KEY_QID +
                " WHERE etq." + KEY_EID + " = " + eId + " ORDER BY RANDOM()";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(KEY_QID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(KEY_EXPLANATION)));
                question.setOptA(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
                question.setOptB(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
                question.setOptC(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
                question.setOptD(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));

                questions.add(question);

            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return questions;
    }

    //Getting Questions with tId
    public List<Question> getQuestionsExamTopic(int eId, int tId){
        List<Question> questions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " AS q INNER JOIN " +
                TABLE_EXAMS_TOPICS_QUESTIONS + " AS etq ON q." +
                KEY_ID + " = etq." + KEY_QID +
                " WHERE etq." + KEY_EID + " = " + eId + " AND etq." + KEY_TID + " = " + tId + " ORDER BY RANDOM()";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(KEY_QID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(KEY_EXPLANATION)));
                question.setOptA(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
                question.setOptB(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
                question.setOptC(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
                question.setOptD(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));

                questions.add(question);

            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return questions;
    }

    //Getting Notes
    public List<Question> getQuestionNotes(){
        List<Question> questions = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " AS q INNER JOIN " +
                TABLE_QUESTION_NOTES + " AS qn ON q."+
                KEY_ID + " = qn." + KEY_QID +
                " ORDER BY " + " qn." + KEY_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(KEY_QID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(KEY_EXPLANATION)));
                question.setOptA(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
                question.setOptB(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
                question.setOptC(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
                question.setOptD(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));

                questions.add(question);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return questions;
    }

    public Performance getPerformance(int eId, int tId){
        String selectQuery = "SELECT * FROM " + TABLE_PERFORMANCE +
                                " where " + KEY_EID + " = " + eId + " and " + KEY_TID + " = " + tId;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        Performance performance = new Performance();

        if(cursor.moveToFirst()){
            do{
                performance.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                performance.setBestScore(cursor.getInt(cursor.getColumnIndex(KEY_BEST_SCORE)));
                performance.setTimeTaken(cursor.getString(cursor.getColumnIndex(KEY_TIME_TAKEN)));
                performance.setEId(cursor.getInt(cursor.getColumnIndex(KEY_EID)));
                performance.setTId(cursor.getInt(cursor.getColumnIndex(KEY_TID)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return performance;

    }

    public void deleteExamsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXAMS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }

    public void deleteTopicsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOPICS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }


    public void deleteQuestionsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }


    public void deleteExamsTopicsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXAMS_TOPICS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }


    public void deleteTopicsQuestionsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOPICS_QUESTIONS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }


    public void deleteExamsTopicsQuestionsExcept(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXAMS_TOPICS_QUESTIONS, KEY_ID + " NOT IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);

        db.close();
    }

    public void deleteNote(int qId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTION_NOTES, KEY_QID + " = ?", new String[]{String.valueOf(qId)});

        db.close();
    }

    public void updateExam(Exam exam){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, exam.getName());

        db.update(TABLE_EXAMS, values, KEY_ID + " = ? ", new String[]{String.valueOf(exam.getId())});
        db.close();

    }

    public void updateTopic(Topic topic){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, topic.getName());

        db.update(TABLE_TOPICS, values, KEY_ID + " = ? ", new String[]{String.valueOf(topic.getId())});
        db.close();

    }


    public void updateQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_ANSWER, question.getAnswer());
        values.put(KEY_EXPLANATION, question.getExplanation());
        values.put(KEY_OPT_A, question.getOptA());
        values.put(KEY_OPT_B, question.getOptB());
        values.put(KEY_OPT_C, question.getOptC());
        values.put(KEY_OPT_D, question.getOptD());

        db.update(TABLE_QUESTIONS, values, KEY_ID + " = ? ", new String[]{String.valueOf(question.getId())});
        db.close();

    }

    public void updatePerformance(Performance performance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_BEST_SCORE, performance.getBestScore());
        values.put(KEY_TIME_TAKEN, performance.getTimeTaken());
        values.put(KEY_EID, performance.getEId());
        values.put(KEY_TID, performance.getTId());

        db.update(TABLE_PERFORMANCE, values, KEY_ID + " = ? ", new String[]{String.valueOf(performance.getId())});
        db.close();

    }

    public boolean hasExam(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_EXAMS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;

    }

    public boolean hasTopic(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_TOPICS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }


    public boolean hasQuestion(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_QUESTIONS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }

    public boolean hasExamsTopics(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_EXAMS_TOPICS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }


    public boolean hasTopicsQuestions(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_TOPICS_QUESTIONS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }


    public boolean hasExamsTopicsQuestions(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select *from " + TABLE_EXAMS_TOPICS_QUESTIONS + " where id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }

    public boolean hasPerformance(int eId, int tId){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PERFORMANCE +
                " where " + KEY_EID + " = " + eId + " and " + KEY_TID + " = " + tId;
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        return flag;
    }

}