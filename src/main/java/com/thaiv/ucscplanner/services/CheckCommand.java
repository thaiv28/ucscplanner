package com.thaiv.ucscplanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.models.Course;

import java.io.File;
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
    
        for(Course c :courseService.getAllCourses()){
            System.out.println(c.getCode());
            // System.out.print(c.getProf());
            // System.out.print(c.getPrereqs());
            // System.out.println(c.getGenEd());
        }
        return 0;
    }

}