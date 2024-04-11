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
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.PreqService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "preq", mixinStandardHelpOptions = true,
description = "Ch")
public class PreqCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

    @Autowired
    private CourseService courseService;
    @Autowired
    private PreqService preqService;

    public Integer call() throws Exception {
        ArrayList<Course> courses = courseService.parseCSV(coursesFile);

        PreqResult result = preqService.check(courses);
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


