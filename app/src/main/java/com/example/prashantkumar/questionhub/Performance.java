package com.example.prashantkumar.questionhub;

public class Performance {
    private int _id, _bestScore, _eId, _tId;
    private String _timeTaken;

    //Constructors
    public Performance(){

    }
    public Performance(int bestScore, String timeTaken, int eId, int tId){
        this._bestScore = bestScore;
        this._timeTaken = timeTaken;
        this._eId = eId;
        this._tId = tId;
    }

    //Setters
    public void setId(int id){
        this._id = id;
    }
    public void setBestScore(int bestScore){
        this._bestScore = bestScore;
    }
    public void setTimeTaken(String timeTaken){
        this._timeTaken = timeTaken;
    }
    public void setEId(int eId){
        this._eId = eId;
    }
    public void setTId(int tId){
        this._tId = tId;
    }

    //Getters
    public int getId(){
        return this._id;
    }
    public int getBestScore(){
        return this._bestScore;
    }
    public String getTimeTaken(){
        return this._timeTaken;
    }
    public int getEId(){
        return this._eId;
    }
    public int getTId(){
        return this._tId;
    }
}
