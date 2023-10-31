package com.thaiv.ucscplanner.models;

public class CreditResult implements Result{

    private boolean bool;

    public CreditResult(boolean bool){
        this.bool = bool;
    }

    public boolean getBool(){
        return this.bool;
    }
    
}
