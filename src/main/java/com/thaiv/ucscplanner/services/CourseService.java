package com.thaiv.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        courseRepository.save(new Course("CSE120"));
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    // return arraylist of courses by parsing through courseFile and 
    // returning every course from repository
    public ArrayList<Course> parseCSV(File file) {
        ArrayList<Course> courses = new ArrayList<>();
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()) {
                String courseString = scanner.nextLine().replaceAll("\\s", "");

                if(courseRepository.findById(courseString).isPresent()) {
                    courses.add(courseRepository.findById(courseString).get());
                } else {
                    System.out.println("Error: Class " + courseString + " invalid");
                    System.exit(2);
                }
             
            }
        }
        catch(Exception e) {
            System.out.println("Error: incorrect file format");
            System.exit(2);
        }
        return courses;
    }

}