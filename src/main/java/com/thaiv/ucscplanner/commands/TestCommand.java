package com.thaiv.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.services.CourseService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "test", mixinStandardHelpOptions = true,
description = "Runs tests on checksc commands.")
public class TestCommand implements Callable<Integer> {

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
