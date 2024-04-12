package com.thaiv.plansc.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.CreditResult;
import com.thaiv.plansc.ucscplanner.models.User;

@Service
public class CreditService implements CheckService{

    @Override
    public CreditResult check(User user){
        int credits = 0;

        for(Course course : user.getCourses()){
            credits += course.getCredits();
        }
        if (credits >= 180 && credits <= 225){
            return new CreditResult(true);
        } else{
            return new CreditResult(false);
        }
    }

    public int getCredits(User user){
        ArrayList<Course> courses = user.getCourses();
        int credits = 0;

        for(Course course : courses){
            credits += course.getCredits();
        }
        return credits;
    }
    
}
