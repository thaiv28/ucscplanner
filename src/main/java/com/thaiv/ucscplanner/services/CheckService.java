package com.thaiv.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.ucscplanner.models.Course;


@Service
public interface CheckService {

    public Object check(ArrayList<Course> courses);

}