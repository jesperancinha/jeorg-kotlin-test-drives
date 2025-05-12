package org.jesperancinha.string.paradigm.nowrapper.performance

import org.jesperancinha.string.paradigm.api.Dependency

class NoWrapperDependencyImpl(private val dependencies: String) : Dependency() {
    override fun dependencyCount(): Int {
        return dependencies.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size
    }

    override fun getSubDependencyElement(index: Int): String? {
        val splittedValues = dependencies.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (index < splittedValues.size - 1) {
            splittedValues[index]
        } else {
            null
        }
    }

    override fun startsWith(subDependencyElement: List<String>): Boolean {
        val codeCount = dependencyCount()
        if (codeCount < subDependencyElement.size) {
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