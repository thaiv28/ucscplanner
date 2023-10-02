package com.thaiv.ucscplanner.models;

import java.util.List;

public class UserCourses {
    
    private List<Course> list;

    public UserCourses(List<Course> list){
        this.list = list;
    }

    public String toString(){
        return list.toString();
    }
    
}

