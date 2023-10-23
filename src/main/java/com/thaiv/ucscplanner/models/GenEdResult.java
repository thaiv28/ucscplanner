package com.thaiv.ucscplanner.models;

import jakarta.persistence.Entity;

public class GenEdResult {

    private boolean bool;
    private String genEd;

    public GenEdResult(boolean bool, String genEd) {
        this.bool = bool;
        this.genEd = genEd;
    }

    public boolean getBool() {
        return this.bool;
    }

    public String getGenEd() {
        return this.genEd;
    }
}
