package com.thaiv.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.ucscplanner.models.Course;

@Service
public class CreditService implements CheckService{

    @Override
    public Boolean check(ArrayList<Course> courses){
        int credits = 0;

        for(Course course : courses){
            credits += course.getCredits();
        }
        if (credits >= 180 && credits <= 225){
            return true;
        } else{
            return false;
        }
    }

    public int getCredits(ArrayList<Course> courses){
        int credits = 0;

        for(Course course : courses){
            credits += course.getCredits();
        }
        return credits;
    }
    
}
