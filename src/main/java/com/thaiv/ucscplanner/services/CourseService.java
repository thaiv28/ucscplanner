package com.thaiv.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.repositories.CourseRepository;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
        
    }

    public List<Course> getAllCourses() {
    
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    // return arraylist of courses by parsing through courseFile and 
    // returning every course from repository
    public ArrayList<Course> parseCoursesFile(File courseFile) {
        
        return null;
    }

}