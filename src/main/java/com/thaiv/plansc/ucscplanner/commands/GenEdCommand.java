package com.thaiv.plansc.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.GenEdResult;
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.GenEdService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "gen", mixinStandardHelpOptions = true,
description = "Verifies that given course list satisfies general education " +
        "requirements.")
public class GenEdCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    @Autowired
    private CourseService courseService;
    @Autowired
    private GenEdService genEdService;

    public Integer call() throws Exception {
        ArrayList<Course> courses = courseService.parseCSV(coursesFile);
    
        GenEdResult results = genEdService.check(courses);
        
        if(results.isSatisfy()){
            System.out.println("The course list fulfills the general education " +
            "graduation requirements.");
        } else {
            System.out.println("The course list does not fulfill the general education " +
            "graduation requirements. Missing requirements: ");
            for(String str : results.getGenEd()){
                System.out.print(str + ", ");
            }

        }

        return 0;
    }
}
