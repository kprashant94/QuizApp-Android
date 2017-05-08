package com.example.prashantkumar.questionhub;

public class Exam {
    int _id;
    String _name;

    //Constructors
    public Exam(){

    }
    public Exam(int id, String name){
        this._id = id;
        this._name = name;
    }

    //Setters
    public void setId(int id){
        this._id = id;
    }
    public void setName(String name){
        this._name = name;
    }

    //Getters
    public int getId(){
        return this._id;
    }
    public String getName(){
        return this._name;
    }

}
