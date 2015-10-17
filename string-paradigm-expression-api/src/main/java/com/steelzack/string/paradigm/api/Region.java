package com.steelzack.string.paradigm.api;

public abstract class Region {
	protected String regionName;
	protected String assignedNumber;

	public abstract String getRegionName();

	public abstract String getAssignedNumber();

	public abstract String getDescription();
}
