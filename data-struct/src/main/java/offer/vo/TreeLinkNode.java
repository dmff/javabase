package offer.vo;

public class TreeLinkNode {

	public int val;
	public TreeLinkNode left;
	public TreeLinkNode right;

	public TreeLinkNode parent;
	
	
	public TreeLinkNode(int val) {
		super();
		this.val = val;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}

	
}
