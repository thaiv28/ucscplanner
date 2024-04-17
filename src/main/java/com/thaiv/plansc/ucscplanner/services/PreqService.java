package com.thaiv.plansc.ucscplanner.services;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.ucscplanner.models.ExpTree;
import com.thaiv.plansc.ucscplanner.models.PreqResult;
import com.thaiv.plansc.ucscplanner.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PreqService implements CheckService{

    final CourseService courseService;
    final PreqPostfixService preqPostfixService;
    final ExpTreeService expTreeService;

    @Override
    public PreqResult check(User user) {
        ArrayList<Course> unsatisfiedCourses = new ArrayList<>();
        boolean allSatisfied = true;

        for(Course course : user.getCourses()){
            String preqStr = course.getPreqstr();
            if(preqStr == null){
                continue;
            }
            String postfix = preqPostfixService.convertToPostfix(course.getPreqstr());
            ExpTree tree = expTreeService.postfixToTree(postfix);
            System.out.println("preqservice.java: converted postfix to tree");
            boolean satisfied = expTreeService.evaluateTree(tree, user, course);
            System.out.println("preqservice.java: evaluated tree");

            if(!satisfied){
                unsatisfiedCourses.add(course);
                allSatisfied = false;
            }
        }

        return new PreqResult(allSatisfied, unsatisfiedCourses);

    }

    
}
