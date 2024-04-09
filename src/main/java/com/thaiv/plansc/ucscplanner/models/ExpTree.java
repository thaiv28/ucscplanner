package com.thaiv.plansc.ucscplanner.models;

import lombok.Getter;

public class ExpTree {

    @Getter
    private String val;
    @Getter
    private ExpTree left;
    @Getter
    private ExpTree right;

    public ExpTree(String val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public ExpTree(String val, ExpTree left, ExpTree right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString(){
        return toString(1);
    }

    public String toString(int indentation){
        String s = "";

        if(this.left != null){
            s += this.left.toString(indentation + 1);

        }
        s += "\n";
        for(int i = 0; i < indentation - 1; i++){
            
            s += "\t";
        }
        s += " - ";
        s += val;

        if(this.right != null){
            s += "\n";
            s += this.right.toString(indentation + 1);
        }
        return s;
    }
    
}
