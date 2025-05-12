package org.jesperancinha.string.paradigm.original.expression

class RegionComparator : Comparator<RegionImpl> {
    override fun compare(o1: RegionImpl, o2: RegionImpl): Int {
        return o1.description.compareTo(o2.description)
    }
}