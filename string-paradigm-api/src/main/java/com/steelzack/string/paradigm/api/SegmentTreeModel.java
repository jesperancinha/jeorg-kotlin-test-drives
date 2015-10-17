package com.steelzack.string.paradigm.api;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public interface SegmentTreeModel {
	DefaultMutableTreeNode findChild(DefaultMutableTreeNode parent, String code);

	DefaultMutableTreeNode addCombinationNode(DefaultMutableTreeNode parent, Dependency dependency, int codeIndex);

	void clear();
	
	List<String> getDependencies(TreePath path);
	
	DefaultMutableTreeNode addCombination(Dependency dependency);
}
