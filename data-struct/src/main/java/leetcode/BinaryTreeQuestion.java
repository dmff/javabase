package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.leetcode.po.TreeNode;

/**
 * 二叉树方面的题型：
 * @author dmf
 *
 */
public class BinaryTreeQuestion {

	List<Integer> lists = new ArrayList<Integer>();
	/**
	 * 1.求二叉树的最小深度:
	 * 解题思路：递归求解；如果为空树，返回0,
	 * 			 如果左子树为空，右子树最小深度+1，如果右子树为空，左子树最小深度+1，
	 * 			 如果左右子树均不为空，则取他们之间最小的+1
	 * 有bug，当有5个节点时， 1(2(4 5) 3)返回的深度为3
	 * @return
	 */
	public int getMinDepth(TreeNode root){
		
		if(root == null)
			return 0;
//		if (root.getLeft() == null && root.getRight() == null) 
//			return 1;
		
		if (root.getLeft() ==null ) 
			return getMinDepth(root.getRight())+1;
		if (root.getRight() ==null) 
			return getMinDepth(root.getLeft())+1;
		
		//左右子树都不为空的情况
		return Math.min(getMinDepth(root.getLeft()), getMinDepth(root.getRight()))+1;	
	}
	
	/**
	 * 2.先序遍历二叉树，把值返回并保存到集合中
	 * 也可以使用非递归的方式实现，使用栈实现，先进后出
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root){
		if (root != null) {
			lists.add(root.getValue());
			preorderTraversal(root.getLeft());
			preorderTraversal(root.getRight());
		}
		return lists;
	}
	
	public List<Integer> preorderTraversalWithStack(TreeNode root){
		if (root ==null) {
			Stack<TreeNode> stack = new Stack<TreeNode>();
			stack.push(root);
			while(!stack.isEmpty()){
				TreeNode node = stack.pop();
				lists.add(node.getValue());
				if (node.getRight()!=null) {
					stack.push(node.getRight());
				}
				if (node.getLeft()!=null) {
					stack.push(node.getLeft());
				}
			}
		}
		return lists;
	}
	
	/**
	 * 3.后续遍历二叉树，把值返回并保存到集合中
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversal(TreeNode root){
		if (root != null) {
			postorderTraversal(root.getLeft());
			postorderTraversal(root.getRight());
			lists.add(root.getValue());
		}
		return lists;
	}

	/**
	 * 4.求每个root-leaf叶子节点合并成的值相加
	 * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10+当前根节点的值)
	 * @return
	 */
	public int sumNumbers(TreeNode root){
		int sum = 0;
		sum = preorderSumNumbers(root,sum);
		return sum;
	}

	private int preorderSumNumbers(TreeNode root, int sum) {
		if (root == null) 
			return 0;
		sum = sum*10 +root.getValue();
		
		if (root.getLeft() == null || root.getRight()==null) {
			return sum;
		}
		
		return preorderSumNumbers(root.getLeft(), sum)+preorderSumNumbers(root.getRight(), sum);
	}
	
	/**
	 * 5.求二叉树最大路径和，开始和结束节点可以在任意node
	 * 
	 * 解题思路：在这里树没有被看成有向图，而是被当成无向图来寻找路径。
	 * 因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。
	 * 在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。
	 * 这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。
	 * 这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。
	 * 而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。
	 * 在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。
	 * 而空间上仍然是栈大小O(logn)。
	 * 
	 * @param root
	 * @return
	 */
	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		//lists用来保存最大路径和，一开始为最小整数
		lists.add(Integer.MIN_VALUE);
		helper(root,lists);
		return lists.get(0);
	}

	/**
	 * 目的是找出左子数最大的路径和+右子树最大路径和+根节点
	 * @param root
	 * @param lists2
	 * @return
	 */
	private int helper(TreeNode root, List<Integer> lists2) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.getLeft(), lists2);
		int right = helper(root.getRight(), lists2);
		
		int cur = root.getValue()+(left>0?left:0)+(right>0?right:0);
		if (cur>lists2.get(0)) {
			lists2.set(0, cur);
		}
		//只能返回左右子树中较大值加上root.val
		return root.getValue()+Math.max(left, Math.max(right, 0));
		
	}

	/**
	 * 6.把左节点->右节点，当右节点没有时，指向null结束,其中指向用next节点表示
	 * 解题思路：使用层序遍历，记录每一层左右节点
	 * @param root
	 */
	public void connect(TreeNode root){
		if (root == null) {
			return;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int len=queue.size();
			//循环控制层数，
			for(int i=0;i<len;i++){
				TreeNode temp = queue.poll();
				if (i==len-1) {
					temp.setNext(null);
				}else {
					temp.setNext(queue.peek());
				}
				
				if (temp.getLeft()!=null) {
					queue.add(temp.getLeft());
				}
				
				if (temp.getRight()!=null) {
					queue.add(temp.getRight());
				}
				
			}
			
		}
	}
	
	/**
	 * 7.根据路径总和找出相应从根节点到叶子节点的路径
	 * @param root
	 * @param sum
	 * @return
	 */
	List<List<Integer>> arrayLists;
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		arrayLists = new ArrayList<List<Integer>>();
		if (root != null) {
			getPath(lists,root,sum);
		}
		return  arrayLists;
	}

	private void getPath(List<Integer> lists2, TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		lists2.add(root.getValue());
		if (root.getValue() == sum &&root.getLeft() == null &&root.getRight()==null) {
			arrayLists.add(new ArrayList<>(lists2));
		}
		
		getPath(lists2, root.getLeft(), sum-root.getValue());
		getPath(lists2, root.getRight(), sum-root.getValue());
		
		//移除最后一个元素
		lists2.remove(lists2.size()-1);	
	}
	
	/**
	 * 8.第七题差不多，这里只是需要判断
	 * @return
	 */
	public boolean hasSumpath(TreeNode root, int sum){
		if (root == null) {
			return false;
		}
		if (root.getValue()==sum && root.getLeft()==null && root.getRight()==null) {
			return true;
		}
		return hasSumpath(root.getLeft(), sum-root.getValue()) || hasSumpath(root.getRight(), sum-root.getValue());
	}
	
	/**
	 * 9.判断一棵树是否平衡：左右两个子树的高度差的绝对值不超过1，并且左右子树也为平衡二叉树
	 * 时间复杂度为o(n*logn)
	 * @param root
	 * @return
	 */
	/*public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		//判断高度差和左右子树是否平衡
		if (Math.abs(getDept(root.getLeft())-getDept(root.getRight()))<=1 
				&& isBalanced(root.getLeft()) && isBalanced(root.getRight())) {
			return true;
		}else {
			return false;
		} 	
	}
	*//**
	 * 获取数的高度
	 * @param node
	 * @return
	 *//*
	private int getDept(TreeNode node) {
		int height = 0;
		if (node !=null) {
			int left = getDept(node.getLeft());
			int right = getDept(node.getRight());
			height = (left>right)?left:right;
		}
		return height+1;
	}*/
	
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (getDeptAdvance(root)==-1) {
			return false;
		}else {
			return true;	
		}
		
	}
	
	private int getDeptAdvance(TreeNode node) {
		if (node ==null) {
			return 0;
		}
		int left = getDeptAdvance(node.getLeft());
		int right = getDeptAdvance(node.getRight());
		//-1表示不平衡，在求高度的时候就判断，时间复杂度变为O(n)
		if (left ==-1 || right==-1 || Math.abs(left-right)>1) {
			return -1; 
		}
		return Math.max(left, right)+1;
	}
	
}
