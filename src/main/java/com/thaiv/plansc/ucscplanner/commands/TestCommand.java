package com.thaiv.plansc.ucscplanner.commands;

import java.io.File;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "test", mixinStandardHelpOptions = true,
description = "Runs tests on checksc commands.")
public class TestCommand implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;


    public Integer call() throws Exception {
        return 0;
    }
}
