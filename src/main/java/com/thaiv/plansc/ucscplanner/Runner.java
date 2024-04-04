package com.thaiv.plansc.ucscplanner;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.thaiv.plansc.ucscplanner.commands.CheckCommand;

import org.springframework.boot.CommandLineRunner;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class Runner implements CommandLineRunner, ExitCodeGenerator {
    
    private final IFactory factory;
    private final CheckCommand checkCommand;
    private int exitCode;

    public Runner(IFactory factory, CheckCommand checkCommand) {
        this.factory = factory;
        this.checkCommand = checkCommand;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(checkCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

}