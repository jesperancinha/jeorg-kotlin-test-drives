package com.steelzack.testing.hierarchy;

interface CodeHierarchy {
	boolean isChildOf(String aCode, String bCode);

	boolean isParentOf(String aCode, String bCode);

	boolean inHierarchyOf(String aCode, String bCode);
}
