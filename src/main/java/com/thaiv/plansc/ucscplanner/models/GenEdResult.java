package com.thaiv.plansc.ucscplanner.models;

import java.util.ArrayList;

public class GenEdResult implements Result{

    private boolean satsify;
    private ArrayList<String> genEd;

    public GenEdResult(ArrayList<String> genEd){
        if(genEd.isEmpty()){
            this.genEd = null;
        } else {
            this.genEd = genEd;
        }

        satsify= genEd.isEmpty();
    }

    public GenEdResult(boolean bool, ArrayList<String> genEd) {
        this.satsify= bool;
        this.genEd = genEd;
    }

    public boolean isSatisfy() {
        return this.satsify;
    }

    public ArrayList<String> getGenEd() {
        return this.genEd;
    }
}
