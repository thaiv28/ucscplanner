package com.thaiv.ucscplanner.models;

import java.util.ArrayList;
import java.util.HashMap;

public class PreqResult implements Result{

    private HashMap<Course, ArrayList<Course>> courses;
    private boolean bool;
    

    public PreqResult(HashMap<Course, ArrayList<Course>> courses){
        this.courses = courses;
        if(courses.isEmpty()){
            bool = true;
        } else {
            bool = false;
        }
    }

    public HashMap<Course, ArrayList<Course>> getPreq(){
        return this.courses;
    }

    public boolean getBool(){
        return this.bool;
    }

}
