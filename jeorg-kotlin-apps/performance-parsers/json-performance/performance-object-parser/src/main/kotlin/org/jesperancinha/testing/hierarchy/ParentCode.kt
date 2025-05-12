package org.jesperancinha.testing.hierarchy

class ParentCode : Code {
    var childCodes: MutableMap<String?, Code?>? = null

    constructor(name: String?, comment: String?) : super(name, comment) {
        this.name = name
        parent = this
        childCodes = HashMap()
    }

    constructor(parent: ParentCode?, name: String?) : super(parent, name) {
        childCodes = HashMap()
    }

    fun addChild(name: String?, childCode: Code?) {
        childCodes!![name] = childCode
    }
}