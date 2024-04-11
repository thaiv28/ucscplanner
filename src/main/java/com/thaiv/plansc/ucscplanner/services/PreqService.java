package com.thaiv.plansc.ucscplanner.services;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.ExpTree;
import com.thaiv.plansc.ucscplanner.models.PreqResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PreqService implements CheckService{

    final CourseService courseService;
    final PreqPostfixService preqPostfixService;
    final ExpTreeService expTreeService;

    @Override
    public PreqResult check(ArrayList<Course> courses) {
        ArrayList<Course> unsatisfiedCourses = new ArrayList<>();
        boolean allSatisfied = true;

        for(Course course : courses){
            String preqStr = course.getPreqstr();
            if(preqStr == null){
                continue;
            }
            String postfix = preqPostfixService.convertToPostfix(course.getPreqstr());
            ExpTree tree = expTreeService.postfixToTree(postfix);
            boolean satisfied = expTreeService.evaluateTree(tree, courses);

            if(!satisfied){
                unsatisfiedCourses.add(course);
                allSatisfied = false;
            }
        }

        return new PreqResult(allSatisfied, unsatisfiedCourses);

    }

    
}
