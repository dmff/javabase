package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import com.leetcode.po.Point;
import com.leetcode.po.TreeNode;

public class Array {

	/**
	 * 1.把字符串翻译成表达式，并且计算其值:使用栈来保存变量 使用异常来控制操作
	 * 
	 * @param tokens
	 * @return
	 */
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String str : tokens) {
			try {
				stack.add(Integer.parseInt(str));
				// 使用异常来判断添加的是操作符，然后拿出最近的两个数进行操作
			} catch (Exception e) {
				int a = stack.pop();
				int b = stack.pop();
				stack.add(get(a, b, str));
			}
		}
		return stack.pop();
	}

	private Integer get(int a, int b, String str) {
		switch (str) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		default:
			return 0;
		}
	}

	/**
	 * 2.找到在同一条直线上的最多点需要两重循环，第一重循环遍历起始点a，第二重循环遍历剩余点b。      
	 * a和b如果不重合，就可以确定一条直线。对于每个点a，构建 斜率->点数 的map。     
	 * (1)b与a重合，以a起始的所有直线点数+1(用dup统一相加)     (2)b与a不重合，a与b确定的直线点数+1
	 * 
	 * @param points
	 * @return
	 */
	public int maxPoints(Point[] points) {
		if (points==null) {
			return 0;
		}
		if (points.length < 2)
			return points.length;
		int max = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Float, Integer> map = new HashMap<>();
			int chonghe = 0, chuizhi = 0;
			Point point1 = points[i];
			for (int j = 0; j < points.length; j++) {
				// 为同一个起点，跳过继续循环
				if (i == j) {
					continue;
				}
				Point point2 = points[j];
				if (point1.getX() == point2.getX()) {
					if (point1.getY() == point2.getY()) {
						chonghe++;
					} else {
						chuizhi++;
					}
				} else { // 两个点存在斜率
					float slope = (point1.getY() - point2.getY()) / (point1.getX() - point2.getX());
					// 如果斜率不存在，则为1，否则当前斜率的点数+1
					map.put(slope, map.get(slope) == null ? 1 : map.get(slope) + 1);
				}
			}
			// 比较当前垂直的点数和斜率的点数哪一方最多,换句话说就是找到以当前点为起点最多点的直线
			int temp_max = chuizhi;
			for (Float slope : map.keySet()) {
				temp_max = temp_max > map.get(slope) ? temp_max : map.get(slope);
			}
			// 给最大点数赋值
			max = max > temp_max + chonghe + 1 ? max : temp_max + chonghe + 1;
		}
		return max;
	}

	/**
	 * 3.在二位平面内，如果O全被X包围，则O要被X替换 解题思路：
	 * 思路1：从四周'O'入手深度搜索，收到'O'标记为'*'，然后剩下的'O'都是被包围的，设置为X
	 * 思路2：遍历每一个节点，当遍历到'O'时，进行深度搜索，看是否能够找到边界，如果可以则表示 没有被包围，反之则被包围，设置为'X'
	 * 
	 * @param board
	 */
	public void solve(char[][] board) {
		// 为空或者只有行||列不会发生改变
		if (board == null || board.length <= 0 || board[0].length <= 0) {
			return;
		}
		// 对四条边进行深度优先遍历
		int rowNum = board.length;
		int colNum = board[0].length;
		// 对首行和尾行进行深度优先遍历
		for (int i = 0; i < colNum; i++) {
			dfs(board, 0, i);
			dfs(board, rowNum - 1, i);
		}
		// 对首列和尾列进行深度优先遍历
		for (int i = 0; i < rowNum; i++) {
			dfs(board, i, 0);
			dfs(board, i, colNum - 1);
		}
		// 重新给数组里面的元素赋值
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (board[i][j] == '*') {
					board[i][j] = 'O';
				} else {
					board[i][j] = 'X';
				}
			}
		}
	}

	/**
	 * 深度优先遍历
	 * 
	 * @param board
	 * @param row
	 * @param col
	 */
	private void dfs(char[][] board, int row, int col) {
		if (board[row][col] == 'O') {
			board[row][col] = '*';
			if (row > 1) {
				// 向上纵向延伸
				dfs(board, row - 1, col);
			}
			if (row < board.length - 1) {
				// 向下纵向延伸
				dfs(board, row + 1, col);
			}
			if (col > 1) {
				// 向左纵向延伸
				dfs(board, row, col - 1);
			}
			if (col < board[0].length) {
				// 向右纵向延伸
				dfs(board, row, col + 1);
			}
		}
	}

	/**
	 * 4.在数组中找到最多的连续的数，返回连续数的长度 解题思路：使用set，然后移除每一个元素
	 * 
	 * @param num
	 * @return
	 */
	public int longestConsecutive(int[] num) {
		Set<Integer> set = new HashSet<Integer>();
		for (int integer : num) {
			set.add(integer);
		}

		int max = 1;
		for (int n : num) {
			if (set.remove(n)) {
				int val = n;
				int sum = 1;
				int val_small = val - 1;
				int val_big = val + 1;
				// 往下搜索
				while (set.remove(val_small)) {
					sum++;
					val_small--;
				}
				// 往上搜索
				while (set.remove(val_big)) {
					sum++;
					val_big++;
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	/**
	 * 5.设计一个O(n)的算法，在两个股票交易中找出最大的利润 解体思想：采用动态规划的思想
	 */
	public int maxProfitone2one(int[] prices) {
		int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
		int release1 = 0, release2 = 0;
		for (int price : prices) {
			release2 = Math.max(release2, hold2 + price);
			hold2 = Math.max(hold2, release1 - price);
			release1 = Math.max(release1, hold1 + price);
			hold1 = Math.max(hold1, -price);
		}

		return release2;
	}

	/**
	 * 6.设计一个O(n)的算法，在股票交易中找出最大的利润 解体思想： 可以分多次卖，但是再次买时需要卖光  
	 * 本题由于允许多次交易（每次必须先卖出再买进），所以不好用爆搜
	 *         //分析可知，要想利益最大化，就应该每次在波谷买进，波峰卖出，这样利益最大，操作次数最少
	 *         //应该是使用动态规划来做可能比较简洁，个人觉得。
	 * 
	 * 波峰减波谷等于波峰到波谷的元素依次相减的和，写出公式就知道是抵消的。 a,b,c,d,e e-a = e-d+d-c+c-b+b-a
	 */
	public int maxProfitone2money(int[] prices) {
		// 采用贪心算法
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int sum = 0;
		for (int i = 1; i < prices.length; i++) {
			int temp = (prices[i] - prices[i - 1]); // 记录所有的涨和跌
			if (temp > 0) {
				sum += temp; // 累加所有涨幅
			}
		}
		return sum;
	}

	/**
	 * 7.求一个交易的最大的利润:最小买入，最大卖出(且要在买入之后)
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitwithonestock(int[] prices) {
		int max = 0, min = prices[0];
		// 依次求出差值
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > min) {
				max = Math.max(max, prices[i] - min);
			} else {
				min = prices[i];
			}
		}
		return max;
	}

	/**
	 * 给定排好序的数组，转化成BST 解题思路：找到中点，然后依次给左右节点赋值
	 * 
	 * @param num
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] num) {
		if (num == null || num.length == 0)
			return null;

		/*
		 * if (num.length==2) { if (num[0]>num[1]) { TreeNode node = new
		 * TreeNode(num[0]); node.setLeft(new TreeNode(num[1])); return node;
		 * }else { TreeNode node = new TreeNode(num[1]); node.setLeft(new
		 * TreeNode(num[0])); return node; }
		 * 
		 * }
		 */

		// 获取数组中中点
		int mid = num.length % 2 == 0 ? num.length >> 1 : (num.length + 1) >> 1;
		TreeNode root = new TreeNode(num[mid - 1]);

		if (num.length == 1) {
			return root;
		}

		root.left = sortedArrayToBST(Arrays.copyOfRange(num, 0, mid - 1));
		root.right = sortedArrayToBST(Arrays.copyOfRange(num, mid, num.length));
		return root;
	}

	/**
	 * 合并已经排好序的两个数组,可以假定A足够大，A初始化m，B初始化n 注意：此时替换成1会继续进行比较,这样需要开辟额外的空间
	 * 
	 * @param A
	 * @param m
	 * @param B
	 * @param n
	 */
	public void merge(int A[], int m, int B[], int n) {
		int[] temp = new int[m + n];
		int index = 0;
		int a_index = 0;
		int b_index = 0;
		while (a_index < m && b_index < n) {
			if (A[a_index] <= B[b_index]) {
				temp[index++] = A[a_index++];
			} else {
				temp[index++] = B[b_index++];
			}
		}

		while (!(a_index == m && b_index == n)) {
			temp[index++] = a_index < b_index ? A[a_index++] : B[b_index++];
		}
		System.arraycopy(temp, 0, A, 0, temp.length);
	}

	public void merge1(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1, j = n - 1, index = m + n - 1;
		while (i >= 0 && j >= 0)
			nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		while (j >= 0)
			nums1[index--] = nums2[j--];
	}
	

}
