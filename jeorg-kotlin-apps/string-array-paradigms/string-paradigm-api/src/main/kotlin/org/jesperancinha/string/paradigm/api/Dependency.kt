package org.jesperancinha.string.paradigm.api

abstract class Dependency {
    abstract fun dependencyCount(): Int
    abstract fun getSubDependencyElement(index: Int): String?
    abstract fun startsWith(subDependencyElement: List<String>): Boolean
}