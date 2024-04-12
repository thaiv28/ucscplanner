package com.thaiv.plansc.ucscplanner.models;

import java.util.ArrayList;

import com.thaiv.plansc.coursedb.models.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class User {

    @Getter 
    private ArrayList<Course> courses;
    @Getter @Setter
    private int mpeScore;
    @Getter 
    private ArrayList<Course> coursesWithPerms;
    @Getter 
    private ArrayList<String> apsTaken;

}
