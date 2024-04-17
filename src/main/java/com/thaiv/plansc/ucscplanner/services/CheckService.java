package com.thaiv.plansc.ucscplanner.services;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.ucscplanner.models.Result;
import com.thaiv.plansc.ucscplanner.models.User;


@Service
public interface CheckService {

    public Result check(User user);

}