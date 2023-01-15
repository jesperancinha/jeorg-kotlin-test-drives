package org.jesperancinha.testing.hierarchy

class CodeResolver : CodeHierarchy {
    private val allMaps: MutableMap<String, ParentCode> = HashMap()
    override fun isChildOf(aCode: String, bCode: String): Boolean {
        val codeA: Code? = allMaps[aCode]
        val codeB: Code? = allMaps[bCode]
        var isChild = false
        if (codeA!!.parent!!.name == codeB!!.name) {
            isChild = true
        }
        return isChild
    }

    override fun isParentOf(aCode: String, bCode: String): Boolean {
        val codeA: Code? = allMaps[aCode]
        val codeB: Code? = allMaps[bCode]
        var isParent = false
        if (codeB!!.parent!!.name == codeA!!.name) {
            isParent = true
        }
        return isParent
    }

    override fun inHierarchyOf(aCode: String, bCode: String): Boolean {
        return isChildOf(aCode, bCode) || isParentOf(aCode, bCode)
    }

    fun proccessMatrix(matrix: Array<Array<String>>) {
        for (i in matrix.indices) {
            val parentString = matrix[i][1]
            val childString = matrix[i][0]
            val coment = matrix[i][2]
            var parent = allMaps[parentString]
            if (parent == null) {
                parent = ParentCode(parentString, coment)
            }
            var code = allMaps[parentString]
            if (code == null) {
                code = ParentCode(parent, childString)
            }
            parent.addChild(childString, code)
            allMaps[parentString] = parent
            allMaps[childString] = code
        }
        println(isParentOf("A", "B"))
        println(inHierarchyOf("A", "D"))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = arrayOf(
                arrayOf("A", "A", "It's A"),
                arrayOf("B", "A", "It's B"),
                arrayOf("C", "A", "It's C"),
                arrayOf("D", "C", "It's D")
            )
            val resolver = CodeResolver()
            resolver.proccessMatrix(matrix)
        }
    }
}