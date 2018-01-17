package offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import com.offer.vo.ListNode;
import com.offer.vo.RandomListNode;
import com.offer.vo.TreeNode;

public class Offer30 {

	/**
	 * 16.合并已经排好序的两个链表
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}

		if (list2 == null) {
			return list1;
		}

		ListNode pre = new ListNode(0);
		ListNode root = pre;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				root.next = list1;
				list1 = list1.next;
			} else {
				root.next = list2;
				list2 = list2.next;
			}
			//往后走一步
			root = root.next;
		}
		root.next = list1 == null ? list2 : list1;
		return pre.next;
	}

	// 递归版本实现
	public ListNode Merge1(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}

		if (list2 == null) {
			return list1;
		}

		if (list1.val < list2.val) {
			list1.next = Merge1(list1.next, list2);
			return list1;
		} else {
			list2.next = Merge1(list1, list2.next);
			return list2;
		}
	}

	/**
	 * 17.判断B是不是A的子结构  ***约定空树不是任意一个树的子结构
	 * 	解题思路：先找到和root2相同的根节点，然后依次往下判断节点是否相等
	 * 			   或者使用先序遍历，判断是否包含
	 * @param toot1
	 * @param root2
	 * @return
	 */
	public boolean hasSubtree(TreeNode root1, TreeNode root2) {
		
		if (root1==null || root2 == null) {
			return false;
		}
		//没有的话，继续往下找根节点
		return isSubTree(root1,root2) || hasSubtree(root1.left, root2) || hasSubtree(root1.right, root2);
	}

	private boolean isSubTree(TreeNode root1, TreeNode root2) {
		if (root2==null) {
			return true;
		}
		//root2!=null && root1==null
		if (root1==null) {
			return false;
		}
		if (root1.val == root2.val) {
			//依次往下面找到节点，当root2的左右节点为null，root1不为null时
			return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
		}
		return false;
	}

	/**
	 * 18.二叉树的镜像 采用层序遍历，然后交换左右节点 可以使用栈非递归的实现，也可以采用递归实现
	 * 
	 * @param root
	 */
	public void mirror(TreeNode root) {
		// 不用进行交换
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.push(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			// 交换左右节点
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			if (node.left != null) {
				queue.push(node.left);
			}
			if (node.right != null) {
				queue.push(node.right);
			}
		}
	}

	// 递归实现
	public void mirror1(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return;
		}

		// 交换左右节点
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;

		mirror1(node.left);
		mirror1(node.right);
	}

	/**
	 * 19.顺序打印矩阵 解题思路：先要获取打印的圈数，一圈打印2行或者2列
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> printMatrix(int[][] array) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (array == null || array.length == 0)
			return result;
		int n = array.length, m = array[0].length;
		if(m==0) return result;
		int layers = (Math.min(n, m) - 1) / 2 + 1;// 这个是层数
		for (int i = 0; i < layers; i++) {
			for (int k = i; k < m - i; k++)
				result.add(array[i][k]);// 左至右
			for (int j = i + 1; j < n - i; j++)
				// row-i-1!=i与collor-i-1!=i的限制就是单行或单列只打印一次
				result.add(array[j][m - i - 1]);// 右上至右下
			for (int k = m - i - 2; (k >= i) && (n - i - 1 != i); k--)
				result.add(array[n - i - 1][k]);// 右至左
			for (int j = n - i - 2; (j > i) && (m - i - 1 != i); j--)
				result.add(array[j][i]);// 左下至左上
		}
		return result;
	}

	/**
	 * 20.定义栈的数据结构 借用list实现，也可以借用stack实现，使用两个栈,使用辅助栈来保存最小值
	 */
	public static class MyStack {

		List<Integer> list = new ArrayList<>();

		public void push(int node) {
			list.add(0, node);
		}

		public void pop() {
			list.get(0);
			list.remove(0);
		}

		public int top() {
			return list.get(0);
		}

		public int min() {
			int temp = top();
			for (int i = 0; i < list.size(); i++) {
				if (temp > list.get(i)) {
					temp = list.get(i);
				}
			}
			return temp;
		}
	}

	/**
	 * 21.第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等
	 * 
	 * 入栈1,2,3,4,5 出栈4,5,3,2,1 
	 * 首先1入辅助栈，此时栈顶1≠4，
	 * 继续入栈2 此时栈顶2≠4，
	 * 继续入栈3 此时栈顶3≠4，继续入栈4
	 * 此时栈顶4＝4，出栈4，弹出序列向后一位，
	 * 此时为5，,辅助栈里面是1,2,3 此时栈顶3≠5，继续入栈5
	 * 此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
	 * 
	 * @param pushA  栈的压入顺序
	 * @param popA   栈的弹出
	 * @return
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA == null || popA == null || pushA.length == 0 || popA.length == 0) {
			return false;
		}

		Stack<Integer> stack = new Stack<>();
		// 用于标识弹出序列的位置
		int popIndex = 0;
		for (int i = 0; i < pushA.length; i++) {
			// 入栈
			stack.push(pushA[i]);
			while (!stack.empty() && stack.peek() == popA[popIndex]) {
				stack.pop();
				popIndex++; // 弹出序列向后一位
			}
		}
		return stack.empty();
	}
	
	/**
	 * 22.从上往下打印出二叉树的每个节点，同层节点从左至右打印
	 * 	  采用层序遍历的思想，可以使用递归，使用队列
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<TreeNode> queue = new ArrayList<>();
		
		if (root==null) {
			return list;
		}
		queue.add(root);
		while(queue.size()!=0){
			TreeNode node = queue.remove(0);
			if (node.left!=null) {
				queue.add(node.left);
			}
			if (node.right!=null) {
				queue.add(node.right);
			}
			list.add(node.val);
		}
		return list;
	}
	
	/**
	 * 23.输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果,假设输入的数组的任意两个数字都互不相同
	 * 解题思路：采用递归，和不使用递归
	 * 对于一个序列S，最后一个元素是x （也就是根），如果去掉最后一个元素的序列为T，那么T满足：T可以分成两段，
	 * 前一段（左子树）小于x，后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。
	 * 符合条件，前面的数都比最后一个数小
	 * @param sequence
	 * @return boolean
	 */
	public boolean verifySquenceOfBST(int [] sequence) {
		if (sequence==null || sequence.length==0) {
			return false;
		}
		return isBFS(sequence,0,sequence.length-1);
	}

	private boolean isBFS(int[] sequence, int start, int end) {
		if (start>=end) {  //相当于只有一个节点
			return true;
		}
		//找到一开始比根节点大的位置
		int index;
		for(index=0;index<end;index++){
			if (sequence[index]>sequence[end]) {
				break;
			}
		}
		
		//判断index后面的元素是不是都比根节点大
		for(int i=index+1;i<=end;i++){
			if (sequence[i]<sequence[end]) {
				return false;  //存在比根节点小的元素
			}
		}
		
		//两部分都满足BFS的定义
		return isBFS(sequence, start, index-1) && isBFS(sequence, index, end-1);
	}
	
	/**
	 * 24.输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
	 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径
	 * 解题思路：采用先序遍历的思想,把每个节点相加
	 * @param root
	 * @param target
	 * @return
	 */
	List<List<Integer>> list = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();
	public List<List<Integer>> findPath(TreeNode root,int target){
		if (root==null) {
			return list;
		}
		list2.add(root.val);
		if (target==root.val && root.left==null && root.right==null) {
			//因为add添加的是引用，如果不new一个的话，后面的操作会更改这个list
			list.add(new ArrayList<>(list2));
//			list.add(list2);  //这样会得到两个空list
		}
		findPath(root.left, target-root.val);
		findPath(root.right, target-root.val);
		//回退一位
		list2.remove(list2.size()-1);
		return list;
	}

	/**
	 * 25.复杂链表的复制，注意：输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空
	 * 解题思路：分三步，1.复制每一个节点插入到当前节点的next节点
	 * 					2.复制每一个节点的随机节点
	 * 					3.拆分链表
	 * @param pHead
	 * @return
	 */
	public RandomListNode clone(RandomListNode pHead){
		if (pHead==null) {
			return pHead;
		}
		//第一步，复制每一个节点
		RandomListNode cur = pHead;
		while(cur!=null){
			RandomListNode copy = new RandomListNode(cur.label);
			copy.next = cur.next;
			cur.next = copy;
			
			//继续往下走
			cur = copy.next;
		}
		
		// 第二步，复制随机节点
		cur = pHead;
		while(cur!=null){
			RandomListNode node = cur.next;
			if (cur.random!=null) {
				node.random = cur.random;
			}
			cur = node.next;
		}
		//第三部，拆分成原链表和复制链表
		RandomListNode copyHead = pHead.next;
		cur = pHead;
		RandomListNode temp =null;
		while(cur.next!=null){
			temp = cur.next;  //记录复制的节点
			cur.next = temp.next;  //把复制节点置为null
			cur =temp;
		}
		return copyHead;
	}
	
	/**
	 * 26.输入一颗二叉树，将二叉树转换成排序的双向链表。要求不能创建新的节点，只能调整指向
	 * 解题思路：采用递归，left、right相当于前驱和后继节点
	 * @param pRootOfTree
	 * @return
	 */
	//记录左子树的最后一个节点
	protected TreeNode leftLast = null;
	public TreeNode convertLinkList(TreeNode pRootOfTree) {
		
		if (pRootOfTree==null ) {
			return pRootOfTree;
		}
		if (pRootOfTree.left==null && pRootOfTree.right==null) {
			// 最后的一个节点可能为最右侧的叶节点,会随着递归而修改最后的左叶子节点
			leftLast = pRootOfTree;
			return pRootOfTree;
		}
		//将左子树改造成双向链表,并返回头结点
		TreeNode left = convertLinkList(pRootOfTree.left);
		
		if (left!=null) {
			leftLast.right = pRootOfTree;
			pRootOfTree.left = leftLast;
		}
		
		//3.当根节点只含左子树时，则该根节点为最后一个节点
		leftLast = pRootOfTree;
		//4.将右子树构造成双链表，并返回链表头节点
		TreeNode right = convertLinkList(pRootOfTree.right);
		//5.如果右子树链表不为空的话，将该链表追加到root节点之后
		if (right!=null) {
			right.left = pRootOfTree;
			pRootOfTree.right = right;
		}	
		return left != null ? left : pRootOfTree;
	}
	
	/**
	 * 使用栈和数的中序遍历
	 * @param pRootOfTree
	 * @return
	 */
	public TreeNode convertLinkListWithStack(TreeNode pRootOfTree) {
		Stack<TreeNode> stack = new Stack<>();
		if (pRootOfTree==null||(pRootOfTree.left==null && pRootOfTree.right==null)) {
			return pRootOfTree;
		}
		
		TreeNode node = pRootOfTree;
		TreeNode pre = null;  //用于保存上一个节点
		boolean isfirst = true;
		while(node!=null || !stack.isEmpty()){
			while(node!=null){
				stack.push(node);
				node = node.left;
			}
			
			node = stack.pop();
			if (isfirst) {
				pRootOfTree =node;// 将中序遍历序列中的第一个节点记为root
				pre = pRootOfTree; 
				isfirst = false;
			}else {
				pre.right= node;
				node.left = pre;
				pre = node;
			}
			node = node.right;	
		}
		return pRootOfTree;
	}
	
	/**
	 * 27.返回字符串的所有排列顺序，长度不超过9(可能有字符重复),字符只包括大小写字母。
	 * 如：abc,acb,bac,bca,cab和cba。
	 * 	   解题思路：把字符串分为两部分，首字符和剩下的部分，然后依次和后面的交换
	 * @param str
	 * @return
	 */
	public ArrayList<String> permutation(String str) {
		//全排列的问题
		ArrayList<String> list = new ArrayList<>();
		if (str==null || str.length()==0) {
			return list;
		}
		char[] chars = str.toCharArray();
		permutation_help(chars,list,0);
		//牛客网默认要按照字典顺序排序
		Collections.sort(list);
		return list;
	}
	
	private void permutation_help(char[] chars, ArrayList<String> list, int beginIndex) {
		//已经是最后一个字符
		if (beginIndex == chars.length-1) {
			 //当有字符重复的情况下，有可能出现全排列过程中，出现相同的排列结果，要把这种情况排除掉
			if (!list.contains(String.valueOf(chars))) {
				//排除相同的结果
				list.add(String.valueOf(chars));				
			}
		}
		for(int i=beginIndex;i<chars.length;i++){
			swap_chars(chars, beginIndex, i);
			permutation_help(chars, list, beginIndex+1);
			swap_chars(chars, beginIndex, i);
		}
		
	}
	
	private void swap_chars(char[] chars,int right,int left){
		char temp;
		temp =chars[left];
		chars[left] = chars[right];
		chars[right] = temp;
	}

	/**
	 * 28.数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字;如果不存在则输出0
	 * 解题思路：先排序，然后找到数组中任意第k大的数字，如果基于partion的快排的时间复杂度是O(nlogn)
	 * 	
	 * @param array
	 * @return 
	 */
	public int moreThanHalfNum(int [] array) {
		if (array==null || array.length==0) {
			return 0;
		}
		//遍历每一个元素，相同次数+1，不相同次数-1，为0时，更换当前保存元素的值
		int result = array[0];
		int times = 1;
		//最后result是超过数组长度一半的元素
		for(int i=0;i<array.length;i++){
			if (times==0) {
				result = array[i];
				times =1;
			}else if (array[i]==result) {
				times++;
			}else {
				times--;
			}
		}
		
		times = 0;
		//判断result是否符合条件
		for(int i=0;i<array.length;i++){
			if (array[i]==result) {
				times++;
			}
		}
		return times>array.length/2?result:0;
	}
	
	/**
	 * 28.查找数组中超过数组长度一半的数，使用快速排序的思想，
	 * @param array
	 * @return
	 */
	public int moreThanHalfNum1(int [] array) {
	
		/*在随机快速排序算法中，我们现在数组中随机选择一个数字，然后调整数组中数字的顺序，
		使得比选中的数字小的数字都排在它的左边，比选中的数字大的数字都排在它的右边。
		如果这个选中的数字的下标刚好是n/2，那么这个数字就是数组的中位数。如果它的下标大于n/2，
		那么中位数应该位于它的左边，我们可以接着在它的左边部分的数组中查找。如果它的下标小于n/2，
		那么中位数应该位于它的右边，我们可以接着在它的右边部分的数组中查找*/
		if (array==null || array.length==0) {
			return 0;
		}
		int mid =array.length >> 1;
		int start = 0;
		int end = array.length-1;
		int index = partion(array,start,end);
		//只需要排到index = array.length/2即可
		while(index !=mid){
			if (index >mid) {
				end = index-1;
				index = partion(array, start, end);
			}else {
				start = index+1;
				index = partion(array, start, end);
			}
		}
		//找到partion，也就数数组中间的位置
		int result = array[mid];
		
		int times = 0;
		//判断result是否符合条件
		for(int i=0;i<array.length;i++){
			if (array[i]==result) {
				times++;
			}
		}
		return times>array.length/2?result:0;
	}
	
	//快排核心
	private int partion(int[] array, int start, int end) {
		

		//这里的pivotkey也可以是[low,high]区间一个随机数，也可以是三数取中，九数取中，
	    //int pivotkey=input[RandInRange(start,end)];
		int partion = array[start];
		while(start<end){
			while(start<end && partion<array[end])
				end--;
			swap(array, start, end);
			while(start<end && partion >=array[start])
				start++;
			swap(array, start, end);
			
		}
		return start;
	}
	
	private void swap(int[] array,int a,int b){
		int temp = 0;
		temp =array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 29.输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
	 * 解题思想：1.先全排序，然后获取O(nlogn)
	 * 			2.使用最小堆 O(nlogk)
	 * 			使用最大堆,空间大小为k，
	 * @param input
	 * @param k 最小的个数
	 * @return
	 */
	public ArrayList<Integer> getLeastNumbers(int [] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
		if (input == null || k<=0 || input.length<k) {
			return list;
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}			
		});
		
		for(int i=0;i<input.length;i++){
			if (maxHeap.size() !=k) {
				maxHeap.add(input[i]);
			}else if(maxHeap.peek() > input[i]){  //数组中元素比堆中最大元素小
				@SuppressWarnings("unused")
				Integer temp = maxHeap.poll();
				temp = null;
				maxHeap.add(input[i]);
			}
		}
		
		for (Integer integer : maxHeap) {
			list.add(integer);
		}
		return list;
	}
	
	/**
	 * 基于快排的思想，查找最小的k个数，前提数组可以修改，时间复杂度是O(n)
	 * @param input
	 * @param k
	 * @return
	 */
	public ArrayList<Integer> getLeastNumbers2(int [] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
		if (input == null || k<=0 || input.length<k) {
			return list;
		}
		int start = 0;
		int end = input.length-1;
		int index = partion(input, start, end);
		while(index != k-1){
			if (index >k-1) {
				end = index-1;
				partion(input, start, end);
			}else {
				start = index+1;
				partion(input, start, end);
			}
		}
		for(int i=0;i<k;i++){
			list.add(input[i]);
		}
		return list;
	}
	
	/**
	 * 30.在数组中获取连续子向量的最大和；如：{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8
	 * 解题思想：对于一个数A，如果他的左边是非负的，那么可以相加
	 * 			 确保肯定是以正数开始的,正数结束
	 * @param array
	 * @return 最大向量
	 */
	public int FindGreatestSumOfSubArray(int[] array) {
	    if (array==null || array.length==0) {
	        return 0;	
		}
	    int total = array[0];
	    int max =array[0];
	    for(int i=1;i<array.length;i++){
	    	if (total>0) {
				total +=array[i]; 
			}else {
				total = array[i];
			}
	    	
	    	if (total>max) {
				max = total;
			}
	    }  
	    return max;
	}
	
	
	/**
	 * 30.采用动态规划的思想，求连续子向量的最大和
	 * @param array
	 * @return
	 */
	public int FindGreatestSumOfSubArray1(int[] array) {
		int res = array[0];  //记录当前所有子数组的和的最大值
		int max = array[0];  //包含array[i]的连续数组最大值
		for(int i=1;i<array.length;i++){
			//只要前一个数是正数对和没有影响就可以加起来
			max = Math.max(max+array[i], array[i]);
			res = Math.max(max, res);
		}
		return res;
	}
	
}
