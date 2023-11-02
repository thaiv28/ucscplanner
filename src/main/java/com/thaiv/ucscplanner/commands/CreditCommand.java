package com.thaiv.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.services.CourseService;
import com.thaiv.ucscplanner.services.CreditService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "creds", mixinStandardHelpOptions = true,
description = "Checks given course list to ensure that " + 
"the credit total is valid.")
public class CreditCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CreditService creditService;

    public Integer call() throws Exception {
        ArrayList<Course> courses = courseService.parseCSV(coursesFile);

        if(creditService.check(courses).isSatsify()){
            System.out.println("The course list fulfills the graduation requirement " +
            "for credits. Credit count: " + creditService.getCredits(courses));
        } else {
            System.out.println("The course list does not fulfill the graduation " +
            "requirement for number of credits. Credit count: " +
            creditService.getCredits(courses));
        }
        
        return 0;
    }
}
