package com.thaiv.plansc.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.User;
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.CreditService;
import com.thaiv.plansc.ucscplanner.services.UserService;

import lombok.RequiredArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@RequiredArgsConstructor
@Component
@Command(name = "creds", mixinStandardHelpOptions = true,
description = "Checks given course list to ensure that " + 
"the credit total is valid.")
public class CreditCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    private CourseService courseService;
    private CreditService creditService;
    private final UserService userService;


    public Integer call() throws Exception {
        User user = userService.createUser(coursesFile);


        if(creditService.check(user).isSatsify()){
            System.out.println("The course list fulfills the graduation requirement " +
            "for credits. Credit count: " + creditService.getCredits(user));
        } else {
            System.out.println("The course list does not fulfill the graduation " +
            "requirement for number of credits. Credit count: " +
            creditService.getCredits(user));
        }
        
        return 0;
    }
}
