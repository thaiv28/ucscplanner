package com.thaiv.ucscplanner.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.models.GenEdResult;
import com.thaiv.ucscplanner.repositories.CourseRepository;

@Service
public class GenEdService implements CheckService{

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public GenEdResult check(ArrayList<Course> courses){
        HashMap<String, Boolean> map = initMap();
        for(Course course : courses){
            String genEd = course.getGenEd();

            if(genEd == null){
                continue;
            }

            if(genEd.equals("C")){
                    map.put(genEd, true);
                    continue;
                }

            map.put(genEd.substring(0, 2), true);
        
        }

        for(String genEd : map.keySet()){
            if(map.get(genEd) == false){
                return new GenEdResult(false, genEd);
            }
        }
        return new GenEdResult(true, null);
    }

    public HashMap<String, Boolean> initMap() {
        HashMap<String, Boolean> courses = new HashMap<>();
        courses.put("CC", false);
        courses.put("ER", false);
        courses.put("IM", false);
        courses.put("MF", false);
        courses.put("SI", false);
        courses.put("SR", false);
        courses.put("TA", false);
        courses.put("PE", false);
        courses.put("PR", false);
        courses.put("C", false);

        return courses;

    }
}
