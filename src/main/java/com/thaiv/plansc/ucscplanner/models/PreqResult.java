package com.thaiv.plansc.ucscplanner.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.thaiv.plansc.coursedb.models.Course;

public class PreqResult implements Result{

    private LinkedHashMap<Course, ArrayList<Course>> courseMap;
    private ArrayList<Course> preqs;
    private boolean satisfy;
    

    public PreqResult(LinkedHashMap<Course, ArrayList<Course>> courseMap,
     ArrayList<Course> preqs){

        this.courseMap = courseMap;
        this.preqs = preqs;
        if(preqs.isEmpty()){
            this.satisfy = true;
        } else {
            this.satisfy = false;
        }

    }

    public LinkedHashMap<Course, ArrayList<Course>> getMap(){
        return this.courseMap;
    }

    public ArrayList<Course> getPreqs(){
        return this.preqs;
    }

    public boolean isSatisfy(){
        return this.satisfy;
    }

}
