package org.jesperancinha.testing.hierarchy;

public class Code {

	protected String comment;
	private String name;

	protected ParentCode parent = null;

	public ParentCode getParent() {
		return parent;
	}

	public void setParent(ParentCode parent) {
		this.parent = parent;
	}

	public Code(String name, String comment) {
		this.setName(name);
		this.comment = comment;

	}

	public Code(ParentCode parent, String name) {
		this.setName(name);
		this.parent = parent;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

}
