package com.steelzack.coffee.system.manager;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
interface GeneralProcessor {
    void startSimulationProcess( //
            final int nIterations, //
            final String sourceXmlcoffeesFile, //
            final String sourceXmlmachinesFile //
    );
}
