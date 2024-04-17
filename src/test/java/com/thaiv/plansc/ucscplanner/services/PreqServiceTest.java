package com.thaiv.plansc.ucscplanner.services;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;
import com.thaiv.plansc.ucscplanner.models.User;

@Service
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PreqServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private PreqPostfixService preqPostfixService;
    @InjectMocks
    private PreqService preqService;
    @InjectMocks
    private String preq;
    private Course AM30;
    private User user;

    @BeforeAll
    void initService(){
        preq = "Prerequisite(s): AM 10 and MATH 11A; or AM 10 and MATH " +
        "19A; or AM 10 and MATH 20A; or MATH 21 and MATH 11A; or MATH 21 and " +
        "MATH 19A; or MATH 21 and MATH 20A.";

        AM30 = new Course();
        AM30.setCode("AM30");
        AM30.setPreqstr(preq);

        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(AM30));
    }

    @Test
    void isOperandTest(){
        assert(preqPostfixService.isOperand("AM30"));
        assert(preqPostfixService.isOperand("ECON11B"));
        assert(preqPostfixService.isOperand("permission"));
        assert(preqPostfixService.isOperand("equivalent"));
        assert(preqPostfixService.isOperand("AP_14A"));
        assert(preqPostfixService.isOperand("MPE_410"));
        assert(!preqPostfixService.isOperand("AND"));
        assert(!preqPostfixService.isOperand("OR"));
        assert(!preqPostfixService.isOperand("Mathematics 20"));
    }

    @Test
    void replaceOperatorsTest(){
        String replaced = preqPostfixService.cleanString(preq);
        System.out.println(replaced);
        System.out.println("Postfix: " + preqPostfixService.convertToPostfix(preq));
    }

}
