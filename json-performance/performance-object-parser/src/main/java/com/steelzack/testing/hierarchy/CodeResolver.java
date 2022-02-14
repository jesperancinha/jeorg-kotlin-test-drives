package com.steelzack.testing.hierarchy;

import java.util.HashMap;
import java.util.Map;

public class CodeResolver implements CodeHierarchy {

	private Map<String, ParentCode> allMaps = new HashMap<String, ParentCode>();

	@Override
	public boolean isChildOf(String aCode, String bCode) {
		Code codeA = allMaps.get(aCode);
		Code codeB = allMaps.get(bCode);

		boolean isChild = false;
		if (codeA.getParent().getName().equals(codeB.getName())) {
			isChild = true;
		}
		return isChild;
	}

	@Override
	public boolean isParentOf(String aCode, String bCode) {
		Code codeA = allMaps.get(aCode);
		Code codeB = allMaps.get(bCode);

		boolean isParent = false;
		if (codeB.getParent().getName().equals(codeA.getName())) {
			isParent = true;
		}
		return isParent;
	}

	@Override
	public boolean inHierarchyOf(String aCode, String bCode) {
		return isChildOf(aCode, bCode) || isParentOf(aCode, bCode);
	}

	public static void main(String[] args) {
		String[][] matrix = new String[][] { { "A", "A", "It's A" },
				{ "B", "A", "It's B" }, { "C", "A", "It's C" },
				{ "D", "C", "It's D" } };

		CodeResolver resolver = new CodeResolver();
		resolver.proccessMatrix(matrix);

	}

	public void proccessMatrix(String[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			String parentString = matrix[i][1];
			String childString = matrix[i][0];
			String coment = matrix[i][2];

			ParentCode parent = (ParentCode) allMaps.get(parentString);
			if (parent == null) {
				parent = new ParentCode(parentString, coment);
			}

			ParentCode code = (ParentCode) allMaps.get(parentString);

			if (code == null) {
				code = new ParentCode(parent, childString);
			}
			
			parent.addChild(childString, code);
			allMaps.put(parentString, parent);
			allMaps.put(childString, code);
		}
		
		System.out.println(isParentOf("A", "B"));
		System.out.println(inHierarchyOf("A", "D"));
	}

}
