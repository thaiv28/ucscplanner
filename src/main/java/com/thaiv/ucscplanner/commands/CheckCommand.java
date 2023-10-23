package com.thaiv.ucscplanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.models.Course;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "checksc", mixinStandardHelpOptions = true, 
version = "checksc 1.0", description = "Checks given course list to ensure that " + 
"those courses will meet graduation requirements.")
public class CheckCommand implements Callable<Integer>{

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    @Autowired
    private CourseService courseService;

    public Integer call() throws Exception {
        ArrayList<Course> courses = courseService.parseCSV(coursesFile);
        for(Course course:courses){
            System.out.println(course.getCode());
        }
        return 0;
    }

}