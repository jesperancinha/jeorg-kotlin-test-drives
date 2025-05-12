package org.jesperancinha.string.paradigm.original.expression

import org.jesperancinha.string.paradigm.api.Region


class RegionImpl(regionName: String, assignedNumber: String) : Region(regionName, assignedNumber) {
    override var description: String
        get() = "$regionName, $assignedNumber"
        set(_) {}
}