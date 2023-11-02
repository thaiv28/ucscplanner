package com.thaiv.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.ucscplanner.models.Course;
import com.thaiv.ucscplanner.models.Result;


@Service
public interface CheckService {

    public Result check(ArrayList<Course> courses);

}