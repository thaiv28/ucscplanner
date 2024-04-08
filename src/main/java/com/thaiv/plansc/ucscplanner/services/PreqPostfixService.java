package com.thaiv.plansc.ucscplanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;

@Service
public class PreqPostfixService {
    
    private CourseService courseService;

    @Autowired
    public PreqPostfixService(CourseService courseService){
        this.courseService = courseService;
    }

    public String convertToPostfix(Course course){
        String preqStr = course.getPreqstr();
        String replacedStr = replaceOperators(preqStr);

        return replacedStr;
    }

    public String replaceOperators(String preqStr){
        String replaced = preqStr.replaceAll(", and", "COMMA-AND")
                    .replaceAll(", or", "COMMA-OR")
                    .replaceAll("; or", " SEMI-OR")
                    .replaceAll("; and", " SEMI-AND")
                    .replaceAll(";", " SEMI")
                    .replaceAll(",", " COMMA")
                    .replaceAll("or", "OR")
                    .replaceAll("and", "AND");

        return replaced;
    }
}
