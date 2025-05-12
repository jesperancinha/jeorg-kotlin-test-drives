package org.jesperancinha.string.paradigm1.expression

import org.jesperancinha.string.paradigm.api.Region

class RegionImpl(type: String, size: String) : Region(type, size) {
    override var description: String
        get() = "$regionName, $assignedNumber"
        set(description) {
            super.description = description
        }
    override fun toString(): String {
        return description
    }
}