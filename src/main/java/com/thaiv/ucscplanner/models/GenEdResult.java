package com.thaiv.ucscplanner.models;

import java.util.ArrayList;

public class GenEdResult {

    private boolean bool;
    private ArrayList<String> genEd;

    public GenEdResult(boolean bool, ArrayList<String> genEd) {
        this.bool = bool;
        this.genEd = genEd;
    }

    public boolean getBool() {
        return this.bool;
    }

    public ArrayList<String> getGenEd() {
        return this.genEd;
    }
}
