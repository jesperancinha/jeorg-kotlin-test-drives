package com.jesperancinha.string.paradigm.original.performance;

import java.util.List;

import com.jesperancinha.string.paradigm.api.Dependency;

public class OriginalDependeyImpl extends Dependency {
	private String dependencies;

	public OriginalDependeyImpl(String data) {
		this.dependencies = data;
	}

	public int getDependencyCount() {
		return dependencies.split(";").length;
	}

	public String getSubDependencyElement(int index) {
		final String[] splittedValues = dependencies.split(";");
		if (index < splittedValues.length) {
			return splittedValues[index];
		} else {
			return null;
		}
	}

	public boolean startsWith(List<String> subDependencyElement) {
		int codeCount = getDependencyCount();
		if (codeCount < subDependencyElement.size()) {
			return false;
		}
		for (int i = 0; i < subDependencyElement.size(); i++) {
			if (!getSubDependencyElement(i).equals(subDependencyElement.get(i))) {
				return false;
			}
		}
		return true;
	}
}
