package com.thaiv.ucscplanner.models;

import java.util.ArrayList;

public class GenEdResult implements Result{

    private boolean bool;
    private ArrayList<String> genEd;

    public GenEdResult(ArrayList<String> genEd){
        if(genEd.isEmpty()){
            this.genEd = null;
        } else {
            this.genEd = genEd;
        }

        bool = genEd.isEmpty();
    }

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
