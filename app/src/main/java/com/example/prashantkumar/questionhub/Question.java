package com.example.prashantkumar.questionhub;


public class Question {
    int _id;
    String _question, _answer, _explanation, _optA, _optB, _optC, _optD;

    // constructors
    public Question(){

    }
    public Question(int id, String question, String answer, String explanation, String optA, String optB, String optC, String optD){
        this._id = id;
        this._question = question;
        this._answer = answer;
        this._explanation = explanation;
        this._optA = optA;
        this._optB = optB;
        this._optC = optC;
        this._optD = optD;
    }

    //Setters
    public  void setId(int id){
        this._id = id;
    }
    public  void setQuestion(String question){
        this._question = question;
    }
    public  void setAnswer(String answer){
        this._answer = answer;
    }
    public void setExplanation(String explanation){
        this._explanation = explanation;
    }
    public  void setOptA(String optA){
        this._optA = optA;
    }
    public  void setOptB(String optB){
        this._optB = optB;
    }
    public  void setOptC(String optC){
        this._optC = optC;
    }
    public  void setOptD(String optD){
        this._optD = optD;
    }

    //Getters
    public  int getId(){
        return this._id;
    }
    public  String getQuestion(){
        return this._question;
    }
    public  String getAnswer(){
        return this._answer;
    }
    public  String getExplanation(){
        return this._explanation;
    }
    public  String getOptA(){
        return this._optA;
    }
    public  String getOptB(){
        return this._optB;
    }
    public  String getOptC(){
        return this._optC;
    }
    public  String getOptD(){
        return this._optD;
    }
}
