package com.thaiv.ucscplanner;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.thaiv.ucscplanner.services.CheckCommand;

import org.springframework.boot.CommandLineRunner;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class Runner implements CommandLineRunner, ExitCodeGenerator {
    
    private final IFactory factory;
    private final CheckCommand checkReq;
    private int exitCode;

    public Runner(IFactory factory, CheckCommand checkReq) {
        this.factory = factory;
        this.checkReq = checkReq;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(checkReq, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

}