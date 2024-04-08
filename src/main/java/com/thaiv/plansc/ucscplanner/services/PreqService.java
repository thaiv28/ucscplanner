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

        for(Course course : courses){
            
            // list of prerequisites for specific course
            ArrayList<Course> preqs = courseService.strToCourseList(course.getPreqs());

            // creates list of absent prereqs
            ArrayList<Course> absent = new ArrayList<>();

            if(preqs == null){
                continue;
            }

            for(Course preq : preqs){
                if(!courses.contains(preq)){
                    absent.add(preq);
                }
            }
            
            if(absent.size() != 0){
                map.put(course, absent);
            }
            
        }

        return new PreqResult(map, totalPreqs);

    }

}
