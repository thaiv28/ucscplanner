package com.thaiv.plansc.ucscplanner.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.GenEdResult;

@Service
public class GenEdService implements CheckService{

    @Override
    public GenEdResult check(ArrayList<Course> courses){
        HashMap<String, Integer> map = initUserMap();
        HashMap<String, Integer> reqMap = initReqMap();
        for(Course course : courses){
            String genEd = course.getGenEd();

            if(genEd == null){
                continue;
            }

            if(genEd.equals("C")){
                    map.put(genEd, course.getCredits() + map.get(genEd));
                    continue;
                }
            
            // add credits of course to map
            map.put(genEd.substring(0, 2),
            course.getCredits() + map.get(genEd.substring(0,2)));
        
        }

        ArrayList<String> missingGens = new ArrayList<>();

        for(String genEd : map.keySet()){
            if(map.get(genEd) < reqMap.get(genEd)){
                missingGens.add(genEd);
            }
        }

        return new GenEdResult(missingGens);  
    }

    public HashMap<String, Integer> initUserMap() {
        HashMap<String, Integer> courses = new HashMap<>();
        courses.put("CC", 0);
        courses.put("ER", 0);
        courses.put("IM", 0);
        courses.put("MF", 0);
        courses.put("SI", 0);
        courses.put("SR", 0);
        courses.put("TA", 0);
        courses.put("PE", 0);
        courses.put("PR", 0);
        courses.put("C", 0);

        return courses;

    }

    public HashMap<String, Integer> initReqMap() {
        HashMap<String, Integer> courses = new HashMap<>();
        courses.put("CC", 5);
        courses.put("ER", 5);
        courses.put("IM", 5);
        courses.put("MF", 5);
        courses.put("SI", 5);
        courses.put("SR", 5);
        courses.put("TA", 5);
        courses.put("PE", 5);
        courses.put("PR", 2);
        courses.put("C",5);

        return courses;
    }
}
