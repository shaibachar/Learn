package com.test.TreeMap;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestTreeMap {
	private static class TreeNode {
		String value;
		TreeNode left;
		TreeNode right;

		TreeNode(String data) {
			this.value = data;
		}

		TreeNode(String data, TreeNode left, TreeNode right) {
			this.value = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TreeNode other = (TreeNode) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TreeNode [value=" + value + ", left=" + left + ", right=" + right + "]";
		}
		
	}

	public static void printLeafNodes(TreeNode node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			System.out.print(MessageFormat.format("{0},", node.value));
		}
		printLeafNodes(node.left);
		printLeafNodes(node.right);
	}

	@Test
	public void mainTest() {

		TreeNode n10 = new TreeNode("A");
		TreeNode n34 = new TreeNode("B");
		TreeNode n43 = new TreeNode("C");
		TreeNode n78 = new TreeNode("S");
		TreeNode n25 = new TreeNode("F", n10, n34);
		TreeNode n65 = new TreeNode("A", n43, n78);
		TreeNode root = new TreeNode("E", n25, n65); // print all leaf nodes of binary tree using recursion
		System.out.println("Printing all leaf nodes of a binary tree in Java (recursively)");
		printLeafNodes(root); // insert a new line
		System.out.println();

	}
}
