package com.thaiv.plansc.ucscplanner.models;

import java.util.ArrayList;

import com.thaiv.plansc.coursedb.models.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EvalTreeResult {

    @Getter
    private ArrayList<Course> unsatisfyList;
    private boolean satisfied;

    public boolean isSatisfy(){
        return satisfied;
    }
}
