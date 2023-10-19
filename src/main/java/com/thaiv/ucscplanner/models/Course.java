package com.thaiv.ucscplanner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Course {

    @Id
    private String code;
    private String name;
    private String num;
    private int credits;
    private String prof;
    private List<String> genEd;
    private boolean repeat;
    private List<String> quarters;
    private List<String> prereqs;

    public Course(){}

    // public Course(String code){
    //     this.code = code;
    // }

    public Course(String code, String name, String num, int credits,
    String prof, String genEd, boolean repeat, String quarters, 
    String prereqs){

        this.code = code;
        this.name = name;
        this.num = num;
        this.credits = credits;
        this.prof = prof;
        this.repeat = repeat;
 
        this.genEd = strToArrayList(genEd);
        this.quarters = strToArrayList(quarters);
        this.prereqs = strToArrayList(prereqs);
        
    }

    public ArrayList<String> strToArrayList(String str){
        return new ArrayList<String>(Arrays.asList(str.split(", ")));
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getNum() {
        return this.num;
    }

    public int getCredits() {
        return this.credits;
    }

    public String getProf() {
        return this.prof;
    }

    public List<String> getGenEd() {
        return this.genEd;
    }

    public boolean isRepeat() {
        return this.repeat;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public List<String> getQuarters() {
        return this.quarters;
    }

    public List<String> getPrereqs() {
        return this.prereqs;
    }

}