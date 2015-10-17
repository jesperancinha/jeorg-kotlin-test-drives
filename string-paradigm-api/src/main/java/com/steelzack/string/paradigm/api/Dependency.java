package com.steelzack.string.paradigm.api;

import java.util.List;

public abstract class Dependency {
	abstract public int getDependencyCount();

	abstract public String getSubDependencyElement(int index);

	abstract public boolean startsWith(List<String> subDependencyElement);
}
