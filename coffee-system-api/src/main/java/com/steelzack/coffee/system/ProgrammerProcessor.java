package com.steelzack.coffee.system;

import java.util.List;

import com.steelzack.coffee.system.objects.CoffeeStats;
import com.steelzack.coffee.system.objects.Programmer;

public interface ProgrammerProcessor {

	CoffeeStats processProgrammers(List<Programmer> programmers);
}
