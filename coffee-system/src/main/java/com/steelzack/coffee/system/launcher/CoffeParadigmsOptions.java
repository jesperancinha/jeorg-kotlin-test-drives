package com.steelzack.coffee.system.launcher;

import lombok.Getter;
import org.kohsuke.args4j.Option;

/**
 * Created by joao on 29-4-16.
 */
@Getter
public class CoffeParadigmsOptions {

    @Option(name = "-it", usage = "Define the number of iterations you want to make", aliases = "--iterations", required = false)
    private Integer nIterations = 1;

    @Option(name = "-nu", usage = "Define the number of persons using the coffee machines", aliases = "--users", required = false)
    private Integer nUsers = 1;

    @Option(name = "-ud", usage = "Defines where the user definition file is", aliases = "--userdefinitions", required = false)
    private String userDefinitionFile;

    @Option(name = "-md", usage = "Defines where the machine definition file is", aliases = "--machinedefinitions", required = false)
    private String machineDefinitionFile;
}
