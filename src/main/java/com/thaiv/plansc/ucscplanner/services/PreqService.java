package com.thaiv.plansc.ucscplanner.services;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.PreqResult;

@Service
public class PreqService implements CheckService{

    CourseService courseService;

    @Autowired
    public PreqService(CourseService courseService){
        this.courseService = courseService;
    }

    @Override
    public PreqResult check(ArrayList<Course> courses) {

        LinkedHashMap<Course, ArrayList<Course>> map = new LinkedHashMap<>();
        ArrayList<Course> totalPreqs = new ArrayList<>();

        return new PreqResult(map, totalPreqs);

    }

    public boolean isOperand(String s){
        if(s.matches("[A-Za-z]+\\d+[A-Za-z]*")){
            return true;
        } else if(s.equals("permission")){
            return true;
        }
        return false;
    }

}
