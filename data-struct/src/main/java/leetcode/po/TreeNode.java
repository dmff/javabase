package leetcode.po;

public class TreeNode {

	public TreeNode left;
	public TreeNode right;
	private TreeNode next;
	private int value;

	public TreeNode(int value) {
		super();
		this.value = value;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public TreeNode getNext() {
		return next;
	}

	public void setNext(TreeNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "TreeNode [left=" + left + ", right=" + right + ", value=" + value + "]";
	}

	/*@Override
	public String toString() {
		return "TreeNode [left=" + left + ", right=" + right + ", next=" + next + ", value=" + value + "]";
	}	*/
	
	
	
	
}
