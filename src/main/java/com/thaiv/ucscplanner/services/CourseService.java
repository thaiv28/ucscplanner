package com.thaiv.ucscplanner.services;

import static org.mockito.ArgumentMatchers.nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.assertj.core.util.Arrays;
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
                
                courses.add(getById(courseString));
             
            }
        }
        catch(Exception e) {
            System.out.println("Error: incorrect file format");
            System.exit(2);
        }
        return courses;
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
            System.exit(2);
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
    

}