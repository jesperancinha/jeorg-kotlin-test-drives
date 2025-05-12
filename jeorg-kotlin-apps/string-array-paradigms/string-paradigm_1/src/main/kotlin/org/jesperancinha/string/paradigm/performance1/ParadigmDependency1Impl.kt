package org.jesperancinha.string.paradigm.performance1

import org.jesperancinha.string.paradigm.api.Dependency

class ParadigmDependency1Impl(private val dependencies: StringWrapper) : Dependency() {
    override fun dependencyCount(): Int = dependencies.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size

    override fun getSubDependencyElement(index: Int): String? {
        val splittedValues = dependencies.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (index < splittedValues.size - 1) {
            splittedValues[index]
        } else {
            null
        }
    }

    override fun startsWith(subDependencyElement: List<String>): Boolean {
        val dependencyCount = dependencyCount()
        if (dependencyCount < subDependencyElement.size) {
            return false
        }
        for (i in subDependencyElement.indices) {
            if (getSubDependencyElement(i) != subDependencyElement[i]) {
                return false
            }
        }
        return true
    }
}