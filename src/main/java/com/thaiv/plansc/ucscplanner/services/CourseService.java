package com.thaiv.plansc.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;

import lombok.NoArgsConstructor;

@Service @NoArgsConstructor
public class CourseService {
    
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public ArrayList<Course> strToCourseList(String courseString){
        ArrayList<Course> list = new ArrayList<>();

        if(courseString == null){
            return null;
        }

        String[] strList = courseString.split(",");

        for(int i = 0; i < strList.length; i++){
            strList[i] = strList[i].trim();
        }

        for(String str : strList){
            list.add(getById(str));
        }

        if(list.size() == 0){
            return null;
        }

        return list;
    }

    public ArrayList<String> genEdArrayList(String str){
        String[] arr = str.split(", ");
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            list.add(arr[i]);
        }
        
        return list;

    }

    public Course getById(String id_spaces){

        String id = id_spaces.replace(" ", "");

        if(courseRepository.findById(id).isPresent()) {
            return courseRepository.findById(id).get();
        } else {
            System.out.println("Error CourseService.getById: Class " + id + " invalid");
            return null;
        }
            
    }

    public String getInfo(Course course) {
        String str = "";

        str = str.concat("\nCredits: " + course.getCredits());

        if(course.getPreqs() != null){
            str = str.concat("\nPrequisities: ");
            for(Course c : strToCourseList(course.getPreqs())){
                str = str.concat(c.getCode() + ", ");
            }
            str = str.substring(0, str.length() - 2);
        }       

        if(course.getGenEd() != null){
            str = str.concat("\nGeneral Education Code: " + course.getGenEd());
        }

        if(course.getProf() != null){
            str = str.concat("\nProfessor: " + course.getProf());
        }

        if(course.getQuarters() != null){
            str = str.concat("\nQuarters Offered: " + course.getQuarters());
        }

        if(!course.getRepeat().equals("False")){
            str = str.concat("\nRepeatable for Credit");
        }


        return str;
    }
    
    public int getMpeValue(String s){
        return Integer.valueOf(s.replaceAll("[a-zA-Z]+", ""));
    }
}