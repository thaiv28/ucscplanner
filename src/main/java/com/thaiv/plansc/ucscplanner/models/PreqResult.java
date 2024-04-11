package com.thaiv.plansc.ucscplanner.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.thaiv.plansc.coursedb.models.Course;

import lombok.Getter;

public class PreqResult implements Result{

    @Getter
    private ArrayList<Course> unsatisfiedCourses;
    private boolean satisfy;
    

    public PreqResult(boolean satisfy, ArrayList<Course> unsatisfiedCourses){

        this.unsatisfiedCourses = unsatisfiedCourses;
        this.satisfy = satisfy;

    }

    public boolean isSatisfy(){
        return this.satisfy;
    }

}
