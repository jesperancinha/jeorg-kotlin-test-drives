package com.steelzack.string.paradigm.performance2;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.steelzack.string.paradigm.api.Dependency;

public class ParadigmDependency2Impl extends Dependency {
	private StringWrapper dependencies;

	public ParadigmDependency2Impl(StringWrapper data) {
		checkNotNull(data);
		this.dependencies = data;
	}

	public int getDependencyCount() {
		return dependencies.toString().split(".").length;
	}

	public String getSubDependencyElement(int index) {
		final String[] splittedValues = dependencies.toString().split(".");
		if (index < splittedValues.length - 1) {
			return splittedValues[index];
		} else {
			return null;
		}
	}

	public boolean startsWith(List<String> subDependencyElement) {
		int dependencyCount = getDependencyCount();
		if (dependencyCount < subDependencyElement.size()) {
			return false;
		}
		for (int i = 0; i < subDependencyElement.size(); i++) {
			if (!getSubDependencyElement(i).equals(subDependencyElement.get(i).toString())) {
				return false;
			}
		}
		return true;
	}
}
