package com.steelzack.testing.hierarchy;

import java.util.HashMap;
import java.util.Map;

public class ParentCode extends Code {

	private Map<String, Code> childCodes = null;

	public ParentCode(String name, String comment) {
		super(name, comment);
		this.setName(name);
		this.parent = this;
		this.setChildCodes(new HashMap<String, Code>());
	}

	public ParentCode(ParentCode parent, String name) {
		super(parent, name);
		this.setChildCodes(new HashMap<String, Code>());
	}

	public void addChild(String name, Code childCode) {
		this.getChildCodes().put(name, childCode);
	}

	public Map<String, Code> getChildCodes() {
		return childCodes;
	}

	public void setChildCodes(Map<String, Code> childCodes) {
		this.childCodes = childCodes;
	}
}
