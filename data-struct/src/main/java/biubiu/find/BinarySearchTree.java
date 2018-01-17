package biubiu.find;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree {

	private TreeNode root = null;// 定义树的根节点
	private List<TreeNode> nodelist = new ArrayList<TreeNode>();// 遍历链表节点

	// 构造二叉查找数的内部类,这就相当于C语言中的结构体
	private class TreeNode {
		private int key;
		private TreeNode lchild; // 定义二叉查找树的左孩子节点
		private TreeNode rchild;// 定义二叉查找树的右孩子节点
		private TreeNode parent;// 定义二叉查找树的父亲节点

		public TreeNode(int key, TreeNode lchild, TreeNode rchild, TreeNode parent) {
			this.key = key;
			this.lchild = lchild;
			this.rchild = rchild;
			this.parent = parent;
		}

		public int getKey() {
			return key;
		}
	}

	// 判断此二叉查找树是否为空
	public boolean isEmpty() {
		return root == null;
	}

	// 实现二叉查找数的插入操作
	public void insert(int key) {
		TreeNode parent = null;
		TreeNode newNode = new TreeNode(key, null, null, null);
		TreeNode temp = root;

		if (root == null) {
			root = newNode;
			return;
		}
		// 找到需要插入的父节点
		while (temp != null) {
			parent = temp;
			if (key < temp.key) {
				temp = temp.lchild;
			} else if (key > temp.key) {
				temp = temp.rchild;
			} else {
				return; // 一般情况下，我们不会插入树中已经存在的节点
			}
		}

		if (key < parent.key) {
			parent.lchild = newNode;
			newNode.parent = parent;
		} else {
			parent.rchild = newNode;
			newNode.parent = parent;
		}
	}

	// 实现二叉查找树的查找操作
	public TreeNode search(int key) {
		TreeNode temp = root;
		while (temp != null && temp.key != key) {
			if (temp.key < key) {
				temp = temp.rchild;
			} else {
				temp = temp.lchild;
			}
		}
		return temp;
	}

	// 获取此树中最小元素的节点
	public TreeNode minNode() {
		return minNode(root);
	}

	private TreeNode minNode(TreeNode node) {
		if (node == null) {
			System.out.println("此树为null");
			return null;
		}
		TreeNode temp = node;
		while (temp.lchild != null) {
			temp = temp.lchild;
		}
		return temp;
	}

	// 获取此树中最大元素的节点
	public TreeNode maxNode() {
		return maxNode(root);
	}

	private TreeNode maxNode(TreeNode node) {
		if (node == null) {
			System.out.println("此树为null");
			return null;
		}
		TreeNode temp = node;
		while (temp.rchild != null) {
			temp = temp.rchild;
		}
		return temp;
	}

	// 中序遍历
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(TreeNode node) {
		if (node != null) {
			inOrder(node.lchild);
			nodelist.add(node);
			inOrder(node.rchild);
		}
	}

	// 获取二叉查找树中序遍历的节点，存在nodelist中
	public List<TreeNode> inOrderTree() {
		if (nodelist != null)
			nodelist.clear();
		inOrder(root);
		return nodelist;
	}

	// 找出中序条件下某节点的后续节点
	public TreeNode successor(TreeNode node) {
		if (node == null) {
			return node;
		}
		if (node == maxNode()) {
			return null;
		}

		if (node.rchild != null) {
			return minNode(node.rchild);
		} else { // 右子树为null，说明后续节点在祖先节点上
			TreeNode parent = node.parent;
			// 找到父节点的左孩子指向当前节点
			while (parent != null && parent.rchild == node) {
				node = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	// 找出中序条件下某节点的前续节点
	public TreeNode predecessor(TreeNode node) {
		if (node == null) {
			return node;
		}
		if (node == minNode()) {
			return null;
		}

		if (node.lchild != null) {
			return maxNode(node.lchild);
		} else { // 左子树为null，说明后续节点在祖先节点上
			TreeNode parent = node.parent;
			// 找到父节点的右孩子指向当前节点
			while (parent != null && parent.lchild == node) {
				node = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	public void remove(int key) {
		TreeNode node = search(key);
		if (node == null) {
			System.out.println("删除的节点" + key + "不存在");
		}
		remove(node);
	}

	private void remove(TreeNode node) {
		if (node == null) {
			return;
		}
		// 左右孩子都存在,找到有孩子的后继节点
		if (node.lchild != null && node.rchild != null) {
			TreeNode temp = successor(node);
			node.key = temp.key;
			remove(temp);

		} else { // 左孩子为null，右孩子为null，或者都为null
			TreeNode parent = node.parent;
			if (parent.lchild == node) {
				parent.lchild = null;
			} else {
				parent.rchild = null;
			}
		}
	}

	public TreeNode getRoot() {// 获取根节点
		return root;
	}

	public String printTree() { // 打印出此二叉查找树
		List<TreeNode> ll = inOrderTree();
		Iterator<TreeNode> it = ll.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			TreeNode p = (TreeNode) it.next();
			sb.append(p.key + " ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		  BinarySearchTree bst = new BinarySearchTree();  
	        System.out.println("此二叉查找树是否为空:" + bst.isEmpty());  
	        int[] keys = new int[] { 15, 6, 18, 3, 7, 13, 20, 2, 9, 4 };  
	        for (int key : keys)  
	            bst.insert(key); 
	        System.out.println("此二叉查找树是否为空:" + bst.isEmpty());  
	        System.out.println("此二叉查找树中最小元素为:"  
	                + bst.minNode().getKey());  
	        System.out.println("此二叉查找树中最大元素为:"  
	                + bst.maxNode().getKey());  
	        System.out.println("此二叉查找树根节点元素为:" + bst.getRoot().getKey());  
	        System.out.println("此二叉查找树为:" + bst.printTree());  
	        System.out.println("查找9是否在二叉查找树中:"  
	                + ((bst.search(9) != null) ? "在" : "不在"));  
	        bst.remove(9);  
	        System.out.println("查找9是否在二叉查找树中:"  
	                + ((bst.search(9) != null) ? "在" : "不在"));  
	        System.out.println("此二叉查找树为:" + bst.printTree());  
	        System.out.println("查找13是否在二叉查找树中:"  
	                + ((bst.search(13) != null) ? "在" : "不在")); 
		}
}
