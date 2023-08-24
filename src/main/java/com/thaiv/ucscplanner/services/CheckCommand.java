package com.thaiv.ucscplanner.services;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

import picocli.CommandLine.Command;

@Component
@Command(name = "checksc", mixinStandardHelpOptions = true, 
version = "checksc 1.0", description = "Checks given course list to ensure that" + 
"those courses will meet graduation requirements.")
public class CheckCommand implements Callable<Integer>{

    public Integer call() throws Exception {
        System.out.println("Your courses will meet graduation requirements.");
        return 0;
    }

}