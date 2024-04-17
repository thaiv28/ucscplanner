package com.thaiv.plansc.ucscplanner.services;

import java.util.Arrays;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.ExpTree;
import com.thaiv.plansc.ucscplanner.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpTreeService {

    final private PreqPostfixService preqPostfixService;
    final private UserService userService;

    public boolean evaluateTree(ExpTree tree, User user, Course parent){
        if(isLeaf(tree)){
            return userService.isSatisfied(user, tree.getVal(), parent);     
        } 
        else if(tree.getVal().equals("OR")){
            return evaluateTree(tree.getLeft(), user, parent)
            || evaluateTree(tree.getRight(), user, parent);
        }
        else if(tree.getVal().equals("AND")){
            return evaluateTree(tree.getLeft(), user, parent)
            && evaluateTree(tree.getRight(), user, parent);
        }

        System.out.println("Invalid tree value: " + tree.getVal());
        System.exit(2);
        return false;
    }

    public ExpTree postfixToTree(String postfix){
        String[] postfixArray = postfix.split(" ");
        System.out.println(Arrays.toString(postfixArray));
        Stack<ExpTree> treeStack = new Stack<>();

        for(String s : postfixArray){
            if(preqPostfixService.isOperand(s)){
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
