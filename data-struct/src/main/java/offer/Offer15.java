package offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.offer.vo.ListNode;
import com.offer.vo.TreeNode;

public class Offer15 {

	/**
	 * 1.在一个二维数组中，每一行都是按照从左往右递增，每一列是按照从上往下递增,判断一个整数是否在二维数组中
	 * 解题思路：1.循环遍历查找，这样的时间复杂度是O(n^2)
	 * 
	 * @param target
	 * @param array
	 */
	public boolean find(int target, int[][] array) {
		boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == target) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 *  采用从右上角开始查找，使用二分查找法，时间复杂度是O(logn)
	 * @param target
	 * @param array
	 * @return
	 */
	public boolean find1(int target, int[][] array) {

		boolean flag = false;

		int row = array.length; // 行数
		int col = array[0].length; // 列数

		// 起始坐标
		int x = 0;
		int y = col - 1;

		while (x <= row - 1 && y >= 0) {
			if (array[x][y] == target) {
				flag = true;
				break;
			} else if (array[x][y] > target) {
				y--;
			} else {
				x++;
			}
		}

		return flag;
	}

	/**
	 * 2.将一个字符串中的空格替换成"%20",We Are Happy->We%20Are%20Happy 特殊字符：回车:'\r'
	 * ,换行:'\n',Tab:'\t',换页:'\f',退格:'\b' 单引号:'\'', 换码符:'\\',双引号:'\'''
	 * 
	 * @param str
	 * @return
	 */
	public String replaceSpace(StringBuffer str) {
		if (str != null) {
			String str1 = str.toString();
			return str1.replaceAll("\\s", "%20");
		}
		return null;
	}

	/**
	 * 3.输入一个链表，从尾到头打印每个节点的值 解题思路：1.可以借助栈 2.或者使用递归
	 */
	public List<Integer> printListFromTailToHead(ListNode listNode) {
		List<Integer> lists = null;
		if (listNode == null) {
			return lists;
		}

		lists = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();

		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next;
		}

		while (!stack.isEmpty()) {
			lists.add(stack.pop());
		}
		return lists;
	}

	// 递归实现从尾到头打印
	List<Integer> list = new ArrayList<>();
	public List<Integer> printListFromTailToHead2(ListNode listNode) {
		if (listNode != null) {
			printListFromTailToHead2(listNode.next);
			list.add(listNode.val);
		}
		return list;
	}

	/**
	 * 4.根据先序和中序遍历重建二叉树
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回 解題思路:
	 * 1.获取根节点，先序的第一个元素 2.获取左右子树，通过中序遍历 3.左右子树的第一个元素为根节点的左右儿子
	 * 4.重复步骤3,左子树：先序数组获取出去根节点的所有左子树的元素，中序数组也是
	 * 
	 * @param pre
	 * @param in
	 * @return
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
		return root;
	}

	private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
		if (startPre > endPre || startIn > endIn) {
			return null;
		}

		TreeNode root = new TreeNode(pre[startPre]);
		// 对中序采用递归
		for (int i = startIn; i <= endIn; i++) {
			// 在中序中存在和先序首节点相等的元素，那么左右子树的第一个节点为根节点左右孩子
			if (in[i] == pre[startPre]) {
				// 先序2 4 7，中序 4 7 2会构造成根节点
				root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
				// 先序 3 5 6 8，中序 5 3 6 8
				root.right = reConstructBinaryTree(pre, startPre + 1 + i - startIn, endPre, in, i + 1, endIn);
			}
		}
		return root;
	}

	/**
	 * 5.使用两个栈来实现一个队列，完成push和pop操作 解题思路：由于栈是先进后出，使用stack1来充当容器，stack2充当缓冲
	 * 
	 * @author dmf
	 *
	 */
	public static class MyQueue {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();

		public void push(int node) {
			stack1.push(node);
		}

		public int pop() {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}

			// 移除第一个
			if (stack2.isEmpty()) {
				throw new RuntimeException("queue is null");
			}
			int pop = stack2.pop();

			// 复原stack1里面的元素
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			return pop;
		}
	}

	/**
	 * 6.把一个数组最开始的若干元素搬到数组的末尾，称为数组的旋转 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
	 * 
	 * 解题思路：1.遍历整个数组 2.采用二分查找法
	 * 
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray(int[] array) { // 为非递减的数组
		if (array == null) {
			throw new NullPointerException("数组为null");
		}

		if (array.length == 0) {
			return 0;
		}

		int result = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[i + 1]) {
				result = array[i + 1];
				break;
			}
		}
		return result;
	}

	/**
	 * 6.1采用二分查找法 分为三种情况: 1.arr[mid]>arr[high],这说明最小值在mid+1和high之间
	 * 2.arr[mid]<arr[high],这说明最小值在low和mid-1之间
	 * 3.arr[mid]=arr[high]左右两边都有可能出现，最好一个一个判断 注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid
	 * 一定会指向下标靠前的数字 array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ; 如果high =
	 * mid - 1，就会产生错误， 因此high = mid
	 * 
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray1(int[] array) {
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (array[mid] > array[high]) {
				low = mid + 1;
			} else if (array[mid] == array[high]) {
				high = high - 1;
			} else {
				high = mid;
			}
		}
		return array[low];
	}

	/**
	 * 7.斐波那契数列，要求输入一个整数，请输出斐波那契数列的第n项 1.采用递归求解 2.采用替换求解
	 * 
	 */
	public int Fibonacci(int n) {
		// 考虑n<=0的情况
		if (n <= 0) {
			return 0;
		}

		if (n == 1 || n == 2) {
			return 1;
		}
		return Fibonacci(n - 1) + Fibonacci(n - 2);
	}

	// 7.1采用替换求解
	public int Fibonacci1(int n) {

		if (n <= 0) {
			return 0;
		}

		if (n == 1 || n == 2) {
			return 1;
		}
		// 这里可以复用a，b
		int a = 1, b = 1;// result=0;
		while (n-- > 2) {
			// result = a+b;
			a += b; // a=result
			b = a - b; // b=a
		}
		return a;
	}

	/**
	 * 8.青蛙跳台阶的问题：递归和求斐波那契数列数列基本一样 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法？
	 * 解题思路：菲比那切数列数列的变形，青蛙跳第一级有1种跳法，跳第二级有2种跳法 ***假设有6级台阶，可以从第5级跳过来，也可以从第4级跳过来***
	 * 
	 * @param target
	 * @return
	 */
	public int jumpFloor(int target) {
		if (target <= 0) {
			return 0;
		}
		if (target == 1 || target == 2) {
			return target;
		}

		int f1 = 1;
		int f2 = 2;
		int total = 0;
		while (target-- > 2) {
			total = f1 + f2;
			f1 = f2;
			f2 = total;
		}
		return total;
	}

	/**
	 * 9.变态跳台阶的问题：青蛙可以跳1-n级,求有多少种跳法 f(n)=f(0)+f(1)+f(2)+...+f(n-1)可以重0至n-1阶开始往上跳
	 * f(n)=2*f(n-1)
	 * 
	 * @param target
	 * @return
	 */
	public int jumpFloorII(int target) {
		if (target <= 0) {
			return 0;
		}
		if (target == 1) {
			return 1;
		}
		return 2 * jumpFloorII(target - 1);
	}

	/**
	 * 10.用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 * 解题思路：菲比那切数列的问题
	 * 
	 * @param target
	 * @return
	 */
	public int RectCover(int target) {
		if (target <= 0) {
			return 0;
		}
		if (target == 1 || target == 2) {
			return target;
		}

		int f1 = 1;
		int f2 = 2;
		int total = 0;
		while (target-- > 2) {
			total = f1 + f2;
			f1 = f2;
			f2 = total;
		}
		return total;
	}

	/**
	 * 11.输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
	 * 
	 * @param n
	 * @return
	 */
	public int Numberof1(int n) {
		int count = 0;
		int flag = 1;
		while (flag != 0) { // 但是flag会等于0吗
			if ((n & flag) != 0) { // 说明最后一位是1,则count++
				count++;
			}
			flag = flag << 1;
		}
		return count;
	}

	// 11.最优解法
	public int Numberof2(int n) {
		int count = 0;
		while (n != 0) {
			++count;
			n = (n - 1) & n;
		}
		return count;
	}

	/**
	 * 12.求浮点数的次方:使用快速幂 a^11=a^(2^0+2^1+2^3) 时间复杂度为O(logn)
	 * 	  使用连乘的时间复杂度是0(n),使用快速幂的时间复杂度是o(logn)
	 * @param base
	 * @param exponent
	 * @return
	 */
	public double Power(double base, int exponent) {
		// return Math.pow(base, exponent);
		double ans = 1.0;
		int n =Math.abs(exponent);
		while (n != 0) {
			if ((n & 1) != 0) // 求一次的时候
				ans *= base;
			base *= base;
			n >>= 1; // 去掉二进制的最后一位
		}
		return exponent >0 ?ans : 1/ans;
	}

	/**
	 * 13.给定一个整数数组，进行分组，前半部分为奇数，后半部分为偶数，且奇数和偶数他们之间相对位置不变 解题思路：
	 * 1.采用冒泡的思想，如果前面是偶数就交换 2.在创建一个数组
	 * 
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		int[] arr1 = new int[array.length];
		System.arraycopy(array, 0, arr1, 0, array.length);

		int j = 0;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] % 2 == 1) {
				array[j++] = arr1[i];
			}
		}

		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] % 2 == 0) {
				array[j++] = arr1[i];
			}
		}
	}

	// 采用交换的思想,和冒泡排序类似
	public void reOrderArray1(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 14.输入一个链表，输出链表的倒数第k个节点
	 * 解题思路：
	 * 			1.借助容器，然后取值
	 * 			2.构造一把长为k的尺子，一个指向k，一个指向末尾，然后往前走，走到头就是倒数第k
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode findKthToTail(ListNode head, int k) {
		if (head==null || k<=0) {
			return null;
		}
		Stack<ListNode> stack = new Stack<>();
		while(head!=null){
			stack.push(head);
			head = head.next;
		}
		
		//从栈里面取出
		if (k > stack.size()) {
			return null;
		}
		
		ListNode node = null;
		for(int i=0;i<k;i++){
			node = stack.pop();
		}
		
		return node;
	}
	
	/**
	 * 构造长为k的尺子
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode findKthToTail1(ListNode head, int k) {
		if (head==null || k<=0) {
			return null;
		}
		
		ListNode pre=head;
		ListNode last=head;
		
		for(int i=1;i<k;i++){  //把pre节点放置到第k个节点的位置
			if (pre.next!=null) {
				pre = pre.next;
			}else{
				return null;  //说明超出界限
			}
		}
		
		//同时往后走，第一个节点到达末尾的时候，第二个节点刚好到达倒数第k个节点
		while(pre.next!=null){
			pre = pre.next;
			last = last.next;
		}	
		return last;
	}
	
	/**
	 * 15.反转链表
	 * 解题思路：
	 * 		1.借助栈实现链表反转
	 * 		2.采用和后面的节点交换，达到反转
	 * @param node
	 * @return
	 */
	public ListNode reverseList(ListNode node){
		//没有节点或者只有一个节点时不需要处理
		if (node== null || node.next==null) {
			return node;
		}
		
		//头结点
		ListNode pre = null;
		while(node!=null){
			ListNode temp = node.next;  //记住下一个节点
			node.next = pre;  //把上一个节点的值复制给下一个节点的next节点
			pre= node;  //记住上一个节点
			node = temp;
		}
		return pre;
	}
}
