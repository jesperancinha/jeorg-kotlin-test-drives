package com.steelzack.coffee.system;

import com.steelzack.coffee.system.objecs.CoffeeStats;
import com.steelzack.coffee.system.objecs.Programmer;

import java.util.List;

public interface ProgrammerProcessor {

    CoffeeStats processProgrammers(List<Programmer> programmers);

}
