package com.thaiv.plansc.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;
import com.thaiv.plansc.ucscplanner.models.ExpTree;
import com.thaiv.plansc.ucscplanner.services.CourseService;
import com.thaiv.plansc.ucscplanner.services.PreqPostfixService;
import com.thaiv.plansc.ucscplanner.services.PreqService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public class PreqServiceTest {

    @Mock
    private CourseRepository courseRepository;
    private PreqService preqService;
    private PreqPostfixService preqPostfixService;
    private ExpTreeService expTreeService;
    private String preq;
    private ArrayList<Course> courses;

    @BeforeAll
    void initService(){
        CourseService courseService = new CourseService(courseRepository);
        preqPostfixService = new PreqPostfixService();
        expTreeService = new ExpTreeService(preqPostfixService, courseService, courseRepository);
        preqService = new PreqService(courseService, preqPostfixService, expTreeService);

        preq = "Prerequisite(s): AM 10 and MATH 11A; or AM 10 and MATH " +
        "19A; or AM 10 and MATH 20A; or MATH 21 and MATH 11A; or MATH 21 and " +
        "MATH 19A; or MATH 21 and MATH 20A.";
    }

    @Test
    void replaceOperatorsTest(){
        String replaced = preqPostfixService.cleanString(preq);
        System.out.println(replaced);
        System.out.println("Postfix: " + preqPostfixService.convertToPostfix(preq));
    }

}
