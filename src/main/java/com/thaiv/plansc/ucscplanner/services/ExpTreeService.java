package com.thaiv.plansc.ucscplanner.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;
import com.thaiv.plansc.ucscplanner.models.ExpTree;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpTreeService {

    final private PreqPostfixService preqPostfixService;
    final private CourseService courseService;
    final private CourseRepository courseRepository;

    public boolean evaluateTree(ExpTree tree, ArrayList<Course> courseList){
        if(isLeaf(tree)){
            if(tree.getVal().equals("permission")){
                return true;
            }
            Optional<Course> course = courseRepository.findById(tree.getVal());
            if(course.isPresent()){
                return courseService.isCourseTaken(courseList, course.get());
            } else {
                return false;
            }

            
        } 
        else if(tree.getVal().equals("OR")){
            return evaluateTree(tree.getLeft(), courseList)
            || evaluateTree(tree.getRight(), courseList);
        }
        else if(tree.getVal().equals("AND")){
            return evaluateTree(tree.getLeft(), courseList)
            && evaluateTree(tree.getRight(), courseList);
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
