package org.jesperancinha.string.paradigms.nowrapper.expression

import org.jesperancinha.string.paradigm.api.Region

class RegionImpl(type: String, size: String) : Region() {
    init {
        regionName = type!!
        assignedNumber = size!!
    }

    override var regionName: String
        set(regionName) {
            super.regionName = regionName
        }
    override var assignedNumber: String
        set(assignedNumber) {
            super.assignedNumber = assignedNumber
        }
    override var description: String
        get() = "$regionName, $assignedNumber"
        set(description) {
            super.description = description
        }

    override fun toString(): String {
        return description
    }
}