package org.jesperancinha.string.paradigm.performance2

import org.jesperancinha.string.paradigm.api.Dependency

class ParadigmDependency2Impl(data: StringWrapper?) : Dependency() {
    private val dependencies: StringWrapper

    init {
        checkNotNull(data)
        dependencies = data
    }

    override fun dependencyCount(): Int {
        return dependencies.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.size
    }

    override fun getSubDependencyElement(index: Int): String {
        val splittedValues = dependencies.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (index < splittedValues.size) {
            splittedValues[index]
        } else {
            ""
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