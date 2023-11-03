package com.thaiv.ucscplanner.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.models.PreqResult;
import com.thaiv.ucscplanner.services.CourseService;
import com.thaiv.ucscplanner.services.PreqService;

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
        
        if(result.isSatisfy()){
            System.out.println("The course list fulfills all " +
            "prerequisites");
        } else {
            System.out.println("The course list does not fulfill the " +
            "following requirements:");

            LinkedHashMap<Course, ArrayList<Course>> map = result.getMap();

            for(Course course : map.keySet()){
                System.out.print(course.getCode() + ": ");

                String str = "";

                for(int i = 0; i < map.get(course).size(); i++){
                    str = str + map.get(course).get(i).getCode();
                    str = str +", ";
                }
                str = str.substring(0, str.length() - 2);

                System.out.print(str+"\n");
            }
        }

        return 0;
    }
}


