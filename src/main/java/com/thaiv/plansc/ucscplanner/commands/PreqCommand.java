package com.thaiv.plansc.ucscplanner.commands;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.PreqResult;
import com.thaiv.plansc.ucscplanner.models.User;
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.PreqService;
import com.thaiv.plansc.ucscplanner.services.UserService;

import lombok.RequiredArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@RequiredArgsConstructor
@Component
@Command(name = "preq", mixinStandardHelpOptions = true,
description = "Ch")
public class PreqCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    private final CourseService courseService;
    private final PreqService preqService;
    private final UserService userService;

    public Integer call() throws Exception {
        User user = userService.createUser(coursesFile);

        PreqResult result = preqService.check(user);
        if(result.getUnsatisfiedCourses().isEmpty()){
            System.out.println("All prerequisites are satisfied.");
        } else {
            System.out.println("Prerequisites not satisifed for: ");
            String unsatisfiedStr = StringUtils.join(result.getUnsatisfiedCourses(), ", ");
            System.out.print(unsatisfiedStr + "\n");
        }
        
        return 0;
    }
}


