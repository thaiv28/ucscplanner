package com.thaiv.plansc.ucscplanner.commands;

import org.springframework.stereotype.Component;

import java.io.File;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "checksc", mixinStandardHelpOptions = true, 
version = "checksc 1.0", description = "Checks given course list to ensure that " + 
"those courses will meet graduation requirements.",
subcommands = {
    CreditCommand.class,
    TestCommand.class,
    GenEdCommand.class,
    PreqCommand.class,
    InfoCommand.class
})
public class CheckCommand{

    @Parameters(paramLabel = "FILE", description = "csv containing " +  
        "list of courses", index = "0")
        File coursesFile;

}