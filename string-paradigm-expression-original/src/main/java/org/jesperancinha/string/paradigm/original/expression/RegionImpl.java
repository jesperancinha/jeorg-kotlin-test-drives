package org.jesperancinha.string.paradigm.original.expression;

import com.jesperancinha.string.paradigm.api.Region;

public class RegionImpl extends Region {
	public RegionImpl(String type, String size) {
		this.regionName = type;
		this.assignedNumber = size;
	}

	public String getRegionName() {
		return regionName;
	}

	public String getAssignedNumber() {
		return assignedNumber;
	}

	public String getDescription() {
		return regionName + ", " + assignedNumber;
	}
}
