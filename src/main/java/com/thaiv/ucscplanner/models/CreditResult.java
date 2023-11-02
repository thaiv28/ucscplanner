package com.thaiv.ucscplanner.models;

public class CreditResult implements Result{

    private boolean satisfy;

    public CreditResult(boolean bool){
        this.satisfy = bool;
    }

    public boolean isSatsify(){
        return this.satisfy;
    }
    
}
