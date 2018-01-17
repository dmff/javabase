package biubiu.array;

import java.util.Stack;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x;
	}
	
	/*根据先序和中序重建出该二叉树，先序可以获得根节点，
     *然后确定根节点的左孩子和右孩子
     *前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
     */
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		//根节点在中序数组中的位置
		return reConstructBinaryTree(pre, 0, pre.length-1, in, 0, in.length-1);
	}
	
	private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

		if (startPre < endPre) {
			return null;
		}

		TreeNode root = new TreeNode(pre[0]);
		for (int i = startIn; i <= endIn; i++) {
			if(in[i] == pre[0]){
				root.left = reConstructBinaryTree(pre, startPre+1, startPre+i-startIn, in, startIn, i-1);
				root.right = reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
			}
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Stack<Integer> stack = new Stack<Integer>();
		//stack.push(item);
		
	}
}
