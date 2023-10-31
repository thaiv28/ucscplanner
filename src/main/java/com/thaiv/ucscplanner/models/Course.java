package com.thaiv.ucscplanner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Course {

    @Id
    private String code;
    private String name;
    private String subject;
    private String num;
    private int credits;
    private String prof;
    private String genEd;
    private String repeat;
    private String quarters;
    private String preqs;

    public Course(){}

    public Course(String code){
        this.code = code;
    }

    public Course(String code, String name, String subject, String num, 
    int credits, String prof, String genEd, String repeat, String quarters, 
    String preqs){

        this.code = code;
        this.name = name;
        this.subject = subject;
        this.num = num;
        this.credits = credits;
        this.prof = prof;
        this.repeat = repeat;
        this.genEd = genEd;
        this.quarters = quarters;
        this.preqs = preqs;
        
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

    private String getSubject() {
        return this.subject;
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

    public String getGenEd() {
        return this.genEd;
    }

    public String getRepeat() {
        return this.repeat;
    }

    public String getQuarters() {
        return this.quarters;
    }

    public String getPreqs() {
        return this.preqs;
    }

}