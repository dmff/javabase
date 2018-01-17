package leetcode.leetcode_test;

import java.util.List;

import org.junit.Test;

import com.leetcode.BinaryTreeQuestion;
import com.leetcode.po.TreeNode;

public class TreeNodeTest {

	@Test
	public void testMinDeep(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
//		TreeNode root3 = new TreeNode(4);
//		TreeNode root4 = new TreeNode(5);
		
		//root3.setLeft(root4);
//		root1.setLeft(root3);
//		root1.setRight(root4);
		root1.setLeft(root2);
		root.setRight(root1);
	}
	
	@Test
	public void testTrver(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
//		TreeNode root3 = new TreeNode(4);
//		TreeNode root4 = new TreeNode(5);
		
		//root3.setLeft(root4);
//		root1.setLeft(root3);
//		root1.setRight(root4);
		root1.setLeft(root2);
		root.setRight(root1);
		
		//System.out.println(new BinaryTreeQuestion().getMinDepth(root));
		//List<Integer> list = new BinaryTreeQuestion().postorderTraversal(root);
		List<Integer> list1 = new BinaryTreeQuestion().preorderTraversal(root);
		@SuppressWarnings("unused")
		List<Integer> list2 = new BinaryTreeQuestion().preorderTraversalWithStack(root);
		System.out.println(list1);
	}
	
	@Test
	public void testSum(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
		TreeNode root3 = new TreeNode(4);
		TreeNode root4 = new TreeNode(5);
		
		root1.setLeft(root3);
		root1.setRight(root4);
		root.setLeft(root2);
		root.setRight(root1);
		
		int sumNumbers = new BinaryTreeQuestion().sumNumbers(root);
		System.out.println(sumNumbers);
	}
	
	@Test
	public void testmaxPathSum(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
//		TreeNode root3 = new TreeNode(4);
//		TreeNode root4 = new TreeNode(5);
		
//		root1.setLeft(root3);
//		root1.setRight(root4);
		root.setLeft(root2);
		root.setRight(root1);
		
		//int sumNumbers = new BinaryTreeQuestion().sumNumbers(root);
		 int maxPathSum = new BinaryTreeQuestion().maxPathSum(root);
		System.out.println(maxPathSum);
	}
	
	@Test
	public void testConnect(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
//		TreeNode root3 = new TreeNode(4);
//		TreeNode root4 = new TreeNode(5);
		
//		root1.setLeft(root3);
//		root1.setRight(root4);
		root.setLeft(root2);
		root.setRight(root1);
		
		new BinaryTreeQuestion().connect(root);
		
		System.out.println(root);
		//preorder(root);
		
	}
	
/*	private void preorder(TreeNode root){
		if (root!=null) {
			System.out.println(root);
			preorder(root.getLeft());
			preorder(root.getRight());
		}
	}*/
	
	@Test
	public void testpathSum(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
//		TreeNode root3 = new TreeNode(4);
//		TreeNode root4 = new TreeNode(5);
		
		root.setLeft(root1);
		root.setRight(root2);
		boolean flag = new BinaryTreeQuestion().hasSumpath(root, 3);
		System.out.println(flag);
	}
	
	@Test
	public void testisbalance(){
		TreeNode root = new TreeNode(1);
		TreeNode root1 = new TreeNode(2);
		TreeNode root2 = new TreeNode(3);
		root.setLeft(root1);
		root1.setRight(root2);
		boolean balanced = new BinaryTreeQuestion().isBalanced(root);
		System.out.println(balanced);
	}
}
