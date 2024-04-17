package com.thaiv.plansc.ucscplanner.commands;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.services.CourseService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "info", mixinStandardHelpOptions = true,
description = "Gives information on a given course.")
public class InfoCommand implements Callable<Integer> {

    @Parameters(paramLabel = "course", description = "Course code "
    + "to get information on.", index = "0")
        String code;

    @Autowired
    private CourseService courseService;


    public Integer call() throws Exception {
        Course course = courseService.getById(code.toUpperCase().replace(" ", "")).get();

        System.out.println("\n" + course);
        System.out.println(courseService.getInfo(course));
        System.out.println();

        return 0;
    }

}
