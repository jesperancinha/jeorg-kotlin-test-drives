package org.jesperancinha.testing.hierarchy

internal interface CodeHierarchy {
    fun isChildOf(aCode: String, bCode: String): Boolean
    fun isParentOf(aCode: String, bCode: String): Boolean
    fun inHierarchyOf(aCode: String, bCode: String): Boolean
}