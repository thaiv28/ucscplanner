package com.thaiv.plansc.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;
import com.thaiv.plansc.ucscplanner.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final CreateUserService createUserService;
    private final PreqPostfixService preqPostfixService;

    public User createUser(File file){
        return createUserService.createUser(file);
    }

    public boolean isSatisfied(User user, String val, Course parent){
        if(val.contains("MPE")){
            return mpeSatisfied(user, courseService.getMpeValue(val));
        } 
        if(val.contains("permission")){
            return hasPermission(user, parent);
        }

        Optional<Course> courseWrapper = courseService.getById(val);

        // if course is not in database but is valid course, returns false
        // TODO: find way of managing false courses
        if(!courseWrapper.isPresent()){
            if(preqPostfixService.isOperand(val)){
                return false;
            }
        }

        return hasTakenCourse(user, courseWrapper.get());

    }

    public boolean hasTakenCourse(User user, Course course){
        if(user.getCourses().contains(course)) return true;
        return false;
    }

    public boolean mpeSatisfied(User user, int targetScore){
        if(user.getMpeScore() >= targetScore) return true;
        return false;
    }

    public boolean hasPermission(User user, Course course){
        if(user.getCoursesWithPerms().contains(course)) return true;
        return false;
    }

    
}
