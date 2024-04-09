package com.thaiv.plansc.ucscplanner.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.ucscplanner.models.ExpTree;

@Service
public class ExpTreeService {

    private PreqService preqService;

    @Autowired
    public ExpTreeService(PreqService preqService){
        this.preqService = preqService;
    }

    public ExpTree postfixToTree(String postfix){
        String[] postfixArray = postfix.split(" ");
        System.out.println(Arrays.toString(postfixArray));
        Stack<ExpTree> treeStack = new Stack<>();

        for(String s : postfixArray){
            if(preqService.isOperand(s)){
                treeStack.push(new ExpTree(s));
            } else{
                ExpTree left = treeStack.pop();
                ExpTree right = treeStack.pop();

                treeStack.push(new ExpTree(s, left, right));
            }
        }

        return treeStack.pop();
    }

    public boolean isLeaf(ExpTree tree){
        if(tree.getLeft() == null){
            return true;
        }
        return false;
    }
}
