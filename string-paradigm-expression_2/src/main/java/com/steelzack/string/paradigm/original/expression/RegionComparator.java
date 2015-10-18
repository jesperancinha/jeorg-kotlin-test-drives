package com.steelzack.string.paradigm.original.expression;

import java.util.Comparator;

public class RegionComparator implements Comparator<RegionImpl> {
	@Override
	public int compare(RegionImpl o1, RegionImpl o2) {
		return o1.getDescription().compareTo(o2.getDescription());
	}
}
