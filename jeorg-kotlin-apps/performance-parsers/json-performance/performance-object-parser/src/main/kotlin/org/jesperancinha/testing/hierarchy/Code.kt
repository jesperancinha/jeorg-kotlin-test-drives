package org.jesperancinha.testing.hierarchy

open class Code {
    protected var comment: String? = null
    var name: String? = null
        protected set
    @JvmField
	var parent: ParentCode? = null

    constructor(name: String?, comment: String?) {
        this.name = name
        this.comment = comment
    }

    constructor(parent: ParentCode?, name: String?) {
        this.name = name
        this.parent = parent
    }
}