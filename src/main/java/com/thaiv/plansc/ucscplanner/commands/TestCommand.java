package com.thaiv.plansc.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.CreditService;
import com.thaiv.plansc.ucscplanner.services.GenEdService;

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
    @Autowired 
    private CreditService creditService;
    @Autowired
    private GenEdService genEdService;


    public Integer call() throws Exception {
        ArrayList<Course> courses = courseService.parseCSV(coursesFile);

        
        return 0;
    }
}
