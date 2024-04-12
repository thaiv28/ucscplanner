package com.thaiv.plansc.ucscplanner.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.Result;
import com.thaiv.plansc.ucscplanner.models.User;


@Service
public interface CheckService {

    public Result check(User user);

}