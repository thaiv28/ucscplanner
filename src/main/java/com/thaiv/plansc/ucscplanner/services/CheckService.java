package com.thaiv.plansc.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.Result;


@Service
public interface CheckService {

    public Result check(ArrayList<Course> courses);

}