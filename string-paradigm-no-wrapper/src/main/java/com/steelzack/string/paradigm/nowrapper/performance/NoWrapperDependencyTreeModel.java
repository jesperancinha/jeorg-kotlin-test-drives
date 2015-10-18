package com.steelzack.string.paradigm.nowrapper.performance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.steelzack.string.paradigm.api.Dependency;
import com.steelzack.string.paradigm.api.SegmentTreeModel;

public class NoWrapperDependencyTreeModel extends DefaultTreeModel implements SegmentTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7448300246415449363L;

	public NoWrapperDependencyTreeModel() {
		super(null);
		clear();
	}

	@Override
	public DefaultMutableTreeNode getRoot() {
		return (DefaultMutableTreeNode) super.getRoot();
	}

	@Override
	public DefaultMutableTreeNode getChild(Object parent, int index) {
		return (DefaultMutableTreeNode) super.getChild(parent, index);
	}

	@Override
	public void clear() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Combinations");
		root.setAllowsChildren(true);
		setRoot(root);
	}

	@Override
	public List<String> getDependencies(TreePath path) {
		DefaultMutableTreeNode last = (DefaultMutableTreeNode) path.getLastPathComponent();
		Object[] objects = last.getUserObjectPath();
		List<String> subDependencyElement = new ArrayList<String>();
		for (int i = 1; i < objects.length; i++) {
			subDependencyElement.add((String) objects[i]);
		}
		return subDependencyElement;
	}

	@Override
	public DefaultMutableTreeNode addCombination(Dependency dependency) {
		return addCombinationNode(getRoot(), dependency, 0);
	}

	@Override
	public DefaultMutableTreeNode addCombinationNode(DefaultMutableTreeNode parent, Dependency dependency,
			int codeIndex) {

		String subDependency = dependency.getSubDependencyElement(codeIndex);
		DefaultMutableTreeNode child = findChild(parent, subDependency);
		if (child == null) {
			child = new DefaultMutableTreeNode(subDependency);
			child.setAllowsChildren(true);
			insertNodeInto(child, parent, getChildCount(parent));
		}
		if (codeIndex < dependency.getDependencyCount() - 1) {
			addCombinationNode(child, dependency, codeIndex + 1);
		}
		return child;
	}

	@Override
	public DefaultMutableTreeNode findChild(DefaultMutableTreeNode parent, String subDependency) {
		for (int i = 0; i < getChildCount(parent); i++) {
			DefaultMutableTreeNode child = getChild(parent, i);
			if (child.getUserObject().equals(subDependency)) {
				return child;
			}
		}
		return null;
	}
}
