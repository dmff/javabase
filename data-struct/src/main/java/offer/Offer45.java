package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import com.offer.vo.ListNode;
import com.offer.vo.TreeNode;

public class Offer45 {

	/**
	 * 31.求出任意非负正数区间内1出现的次数 解题思路： 出现1无非是在个位、十位、百位、千位。。。等出现，
	 * 那对一个数判断是否有1便是判断他的各个位有没有1出现。
	 * 
	 * 或者拼接成字符串，然后获取
	 * 
	 * @param n
	 * @return
	 */
	public int numberOf1Between1AndN_Solution(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(i);
		}
		String str = sb.toString();
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	/**
	 * 31.分别统计每一位上出现1的次数
	 * 
	 * @param n
	 * @return
	 */
	public int numberOf1Between1AndN_Solution1(int n) {
		int count = 0;
		// 获取每一个位置上出现1的次数
		for (int m = 1; m <= n; m *= 10) {
			int a = n / m;
			int b = n % m;

			if (a % 10 == 0) {
				count += a / 10 * m;
			} else if (a % 10 == 1) {
				count += (a / 10 * m) + (b + 1);
			} else {
				count += (a / 10 + 1) * m;
			}
		}
		return count;
	}

	/**
	 * 32.输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
	 * 
	 * @param numbers
	 * @return
	 */
	public String PrintMinNumber(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return "";
		}
		int length = numbers.length;
		String[] str = new String[length];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			str[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(str, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// 把两个字符串拼接是重点
				String c1 = s1 + s2;
				String c2 = s2 + s1;
				return c1.compareTo(c2); // <0 =0 >0
			}
		});

		for (int i = 0; i < length; i++) {
			sb.append(str[i]);
		}
		return sb.toString();
	}

	/**
	 * 33.求按从小到大的顺序的第N个丑数;把只包含因子2、3和5的数称作丑数,1是最小的丑数 例如:6和8是丑数，14不是丑数
	 * 解题思路：创建丑数数组，然后一直从小到大构建丑数
	 * 
	 * @param index
	 *            索引位置，从1开始
	 * @return 大于1表示为正确的丑数，等于0表示索引不正确，这样的丑数不存在
	 */
	public int GetUglyNumber_Solution(int index) {
		if (index <= 0) {
			return 0;
		}
		// 因为1-6都是丑数
		if (index < 7) {
			return index;
		}

		int[] arr = new int[index];
		arr[0] = 1;
		int t2 = 0, t3 = 0, t5 = 0;
		for (int i = 1; i < index; i++) {
			arr[i] = Math.min(arr[t2] * 2, Math.min(arr[t3] * 3, arr[t5] * 5));
			if (arr[i] == arr[t2] * 2)
				t2++;
			if (arr[i] == arr[t3] * 3)
				t3++;
			if (arr[i] == arr[t5] * 5)
				t5++;
		}
		return arr[index - 1];
	}

	/**
	 * 34.在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
	 * 
	 * @param str
	 *            输入的字符
	 * @return index 第一个只出现一次的字符 -1表示这样的""字符
	 */
	public int firstNotRepeatingChar(String str) {
		if (str == null) {
			throw new RuntimeException("字符串为null异常");
		}
		// 使用map来保存
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				Integer time = map.get(str.charAt(i));
				map.put(str.charAt(i), ++time);
			}
		}

		int index = -1;
		for (int i = 0; i < str.length(); i++) {
			if (map.get(str.charAt(i)) == 1) {
				index = i;
				break;
			}
		}
		return index;

	}

	/**
	 * 35.在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
	 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
	 * 解题思路：采用归并排序的思想,或者直接暴力解法
	 * 
	 * @param array
	 *            数组
	 * @return int 逆序对的总数P对1000000007取模的结果
	 */
	public int inversePairs(int[] array) {

		// 长度为0或者1直接没有逆序对
		if (array == null || array.length <= 1) {
			return 0;
		}

		int count =0;
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				if (array[i]>array[j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 使用归并的思想
	 * @param array
	 * @return
	 */
	public int inversePairs_Advance(int[] array) {
		// 长度为0或者1直接没有逆序对
		if (array == null || array.length <= 1) {
			return 0;
		}
		int[] temp = new int[array.length];
		return merge(array, 0, array.length-1,temp );
	}

	// 进行拆分,统计，排序
	private int merge(int[] array, int begin, int end,int[] temp) {
		if (begin >= end) {
			return 0;
		}
		int mid = begin + (end - begin) / 2;
		int left = merge(array, begin, mid,temp);  //统计左边的逆序对
		int right = merge(array, mid + 1, end,temp); //统计右边的逆序对
		int count = mergeCount(array,begin,mid,end,temp); //合并统计逆序对
		return ((left+right)%1000000007+count)%1000000007;
	}
	
	private int mergeCount(int[] array, int begin, int mid, int end,int[] temp) {
		long count = 0;
		int i = mid;
		int j = end;
		int index = end;
		while(i>=begin && j>=mid+1){
			if (array[mid]>array[end]) {
				temp[index--] = array[i--];
				count +=j-mid;
			}else {
				temp[index--] = array[j--];
			}
		}
		while(i>=begin){
			temp[index--] = array[i--];
		}
		while(j>=mid+1){
			temp[index--] = array[j--];
		}
		
		//赋值给array
		for(int k=end;k>=begin;k--){
			array[k] = temp[k];
		}
		
		return (int) (count%1000000007);
	}

	/**
	 * 36.找出两个链表的第一个公共节点 解题思路：由于是单链表，所有重合后为Y字形
	 * 先找到尾节点，然后开始第一个next不相同的上一个节点就是common节点，使用两个辅助栈实现
	 * 思路2：先获取链表的长度，然后把两个链表等长，齐头并进寻找公共节点
	 * 
	 * @param pHead1
	 * @param pHead2
	 * @return
	 */
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		ListNode common = null;

		if (pHead1 != null && pHead2 != null) {
			Stack<ListNode> stack1 = new Stack<>();
			Stack<ListNode> stack2 = new Stack<>();

			while (pHead1 != null) { // 链表1入栈
				stack1.push(pHead1);
				pHead1 = pHead1.next;
			}

			while (pHead2 != null) { // 链表2入栈
				stack2.push(pHead2);
				pHead2 = pHead2.next;
			}

			// 开始好公共节点
			while (!stack1.isEmpty() && !stack2.isEmpty()) {
				ListNode node1 = stack1.pop();
				ListNode node2 = stack2.pop();

				if (node1 != node2) { // 找到分叉点，可以直接退出
					break;
				}
				common = node1;
			}

		}
		return common;
	}

	/**
	 * 36.1.用两个指针扫描”两个链表“，最终两个指针到达 null 或者到达公共结点
	 * 
	 * @param pHead1
	 * @param pHead2
	 * @return
	 */
	public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {

		/*
		 * 思路：如果有公共节点，1）若两个链表长度相等，那么遍历一遍后，在某个时刻，p1 == p2
		 *                      2)若两个链表长度不相等，那么短的那个链表的指针pn（也就是p1或p2）
		 *                          必先为null，那么这时再另pn = 链表头节点。经过一段时间后，
		 *                          则一定会出现p1 == p2。        
		 * 如果没有公共节点：这种情况可以看成是公共节点为null，顾不用再考虑。
		 */
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;
		while (p1 != p2) {
			if (p1 != null)
				p1 = p1.next;// 防止空指针异常
			if (p2 != null)
				p2 = p2.next;
			// 当两个链表长度不想等，跑圈
			if (p1 != p2) {
				if (p1 == null)
					p1 = pHead1;
				if (p2 == null)
					p2 = pHead2;
			}
		}
		return p1;
	}

	/**
	 * 37.统计一个数字在排序数组中出现的次数 思路：采用二分查找法，找到第一次出现k的位置和最后一次出现k的位置
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public int GetNumberOfK(int[] array, int k) {
		if (array == null || array.length == 0) {
			return 0;
		}

		int number = 0;
		int firstK = getFirstK(array, array.length, k, 0, array.length - 1);
		int lastK = getLastK(array, array.length, k, 0, array.length - 1);
		if (firstK > -1 && lastK > -1) {
			number = lastK - firstK + 1;
		}
		return number;
	}

	/**
	 * 找到第一次出现k的位置
	 * 
	 * @param array
	 * @param k
	 * @param start
	 * @param end
	 * @return 返回-1说明这个数不存在，返回>0表示这个数在数组中的位置
	 */
	private int getFirstK(int[] array, int length, int k, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = start + (end - start) / 2;
		if (array[mid] == k) {
			if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
				return mid;
			} else {
				end = mid - 1;
			}
		} else if (array[mid] > k) {
			end = mid - 1;
		} else {
			start = mid + 1;
		}

		return getFirstK(array, length, k, start, end);
	}

	/**
	 * 找到最后一次出现k的位置
	 * 
	 * @param array
	 * @param k
	 * @param start
	 * @param end
	 * @return 返回-1说明这个数不存在，返回>0表示这个数在数组中的位置
	 */
	private int getLastK(int[] array, int length, int k, int start, int end) {
		/*
		 * if (start>end) { return -1; }
		 */
		// 采用非递归的写法
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == k) {
				if ((mid < length - 1 && array[mid + 1] != k) || mid == length - 1) {
					return mid;
				} else {
					start = mid + 1;
				}
			} else if (array[mid] > k) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
		// return getLastK(array,length, k, start, end);
	}

	/**
	 * 38.求一颗二叉树的深度 解题思路：1.采用递归的方式获取树的深度 2.使用二叉树的层次遍历，通过插入特殊的结点new
	 * TreeNode(Integer.MIN_VALUE)来计算层次
	 * 
	 * @param root
	 * @return
	 */
	public int treeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = treeDepth(root.left);
		int right = treeDepth(root.right);

		return (left > right ? left : right) + 1;
	}

	/**
	 * 38.使用非递归的方式获取二叉树的深度
	 * 
	 * @param root
	 * @return
	 */
	public int treeDepth1(TreeNode root) {
		int depth = 0;
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			queue.offer(new TreeNode(Integer.MIN_VALUE));

			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				if (node.val != Integer.MIN_VALUE) {
					if (node.left != null) {
						queue.offer(node.left);
					}
					if (node.right != null) {
						queue.offer(node.right);
					}
				} else {
					// 当遇到特殊节点时，说明当前层已经遍历完了同时下一层的所有节点已经全部入队了，在每层的最后压入一个特殊节点
					depth++;
					if (!queue.isEmpty()) {// 重点，不然会死循环,里面添加了子节点
						queue.offer(new TreeNode(Integer.MIN_VALUE));
					}
				}
			}
		}
		return depth;
	}

	/**
	 * 39.判断一颗树是否是平衡二叉树 左右两个子树的高度差的绝对值不超过1，并且左右子树也为平衡二叉树
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1 && isBalanced(root.right)
				&& isBalanced(root.left)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一棵树是否是平衡二叉树 优化：在求高度的时候就判断，时间复杂度变为O(n)
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBalanced_Advance(TreeNode root) {
		if (root == null) {
			return true;
		}
		int advance = getDeptAdvance(root);
		if (advance == -1) {
			return false;
		} else {
			return true;
		}
	}

	private int getDeptAdvance(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getDeptAdvance(root.left);
		int right = getDeptAdvance(root.right);
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;

	}

	/**
	 * 40.一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
	 * 解题思路：位运算中异或的性质：两个相同数字异或=0，一个数和0异或还是它本身。
	 * 
	 * @param array
	 * @param num1
	 *            将num1[0],num2[0]设置为返回结果
	 * @param num2
	 */
	public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
		if (array == null || array.length < 2) {
			return;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				Integer time = map.get(array[i]);
				map.put(array[i], ++time);
			} else {
				map.put(array[i], 1);
			}
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				if (num1[0] == 0) {
					num1[0] = entry.getKey();
				} else {
					num2[0] = entry.getKey();
				}
			}
		}
	}

	/**
	 * 40.采用异或的解法：数组中有两个出现一次的数字，其他数字都出现两次，找出这两个数字
	 * 首先：位运算中异或的性质：两个相同数字异或=0，一个数和0异或还是它本身。
	 * 
	 * @param array
	 * @param num1
	 * @param num2
	 */
	public void FindNumsAppearOnce1(int[] array, int num1[], int num2[]) {

		/*
		 * 首先：位运算中异或的性质：两个相同数字异或=0，一个数和0异或还是它本身。
		 * 当只有一个数出现一次时，我们把数组中所有的数，依次异或运算，最后剩下的就是落单的数，因为成对儿出现的都抵消了。
		 * 
		 * 依照这个思路，我们来看两个数（我们假设是AB）出现一次的数组。我们首先还是先异或，剩下的数字肯定是A、B异或的结果，
		 * 这个结果的二进制中的1，表现的是A和B的不同的位。我们就取第一个1所在的位数，假设是第3位，接着把原数组分成两组，
		 * 分组标准是第3位是否为1。如此，相同的数肯定在一个组，因为相同数字所有位都相同，而不同的数，肯定不在一组。
		 * 然后把这两个组按照最开始的思路，依次异或，剩余的两个结果就是这两个只出现一次的数字。
		 */

		if (array == null || array.length < 2) {
			return;
		}

		int bitSum = 0;
		for (int i = 0; i < array.length; i++) {
			bitSum ^= array[i];
		}

		// 找到两个数不同位的位置
		int index;
		for (index = 0; index < 32; index++) {
			if ((bitSum & (1 << index)) != 0) {
				break;
			}
		}

		// 如果两个数相同，那么他们的bit位是相同的,然后分为两组，依次异或
		for (int i = 0; i < array.length; i++) {
			if ((array[i] & (1 << index)) != 0) {
				num2[0] ^= array[i];
			} else {
				num1[0] ^= array[i];
			}
		}
	}

	/**
	 * 40.采用异或的解法：数组中有一个出现一次的数字，其他数字都出现两次，找出这个数字
	 * 
	 * @param array
	 * @return
	 */
	public int find1From2(int[] array) {
		if (array == null || array.length == 0) {
			throw new RuntimeException("数组错误");
		}

		int bitSum = 0;
		for (int i = 0; i < array.length; i++) {
			bitSum ^= array[i];
		}

		return bitSum;
	}

	/**
	 * 40.采用异或的解法：数组中有一个出现一次的数字，其他数字都出现三次，找出这个数字
	 * 
	 * @param array
	 * @return
	 */
	public int find1From3(int[] a) {
		//不理解
		if (a == null || a.length == 0) {
			throw new RuntimeException("数组错误");
		}
		int[] bits = new int[32];

		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 32; j++) {
				bits[j] = bits[j] + ((a[i] >>> j) & 1);
			}
		}
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if (bits[i] % 3 != 0) {
				res = res | (1 << i);
			}
		}
		return res;

	}
	
	/**
	 * 41.输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
	 * 	   例如：100的序列有18,19,20,21,22和9-16
	 * 	  解题思想：也可以从1开始，到sum/2结束判断，起始都不能等于sum/2
	 * @param sum  连续序列的和，至少含有两个数
	 * @return  ArrayList 序列的集合
	 */
	public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		
		//根据数学公式计算:(a1+an)*n/2=s  n=an-a1+1
		//(an+a1)*(an-a1+1)=2*s=k*l(k>l)
		//an=(k+l-1)/2  a1=(k-l+1)/2
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		if (sum<3) {
			return list;
		}
		
		//d就是k，i就是l。l最大值取2s开方就行了
		int lmax = (int) Math.sqrt(2*sum);
		for(int l=lmax;l>=2;l--){
			if (2*sum%l==0) {
				int k = 2*sum/l;
				//保证k和l必定为一奇一偶
				if (k%2==0 && l%2!=0 || k%2!=0 && l%2==0) {
					int a1 = (k-l+1)/2;
					int an = (k+l-1)/2;
					ArrayList<Integer> temp = new ArrayList<>();
					for(int j=a1;j<=an;j++){
						temp.add(j);
					}
					list.add(temp);
				}
			}
		}
		return list;
	}
	
	/**
	 * 42.输入一个递增排序的数组和一个数字S，在数组中查找两个数，他们的和正好是S，
	 * 	   如果有多对数字的和等于S，输出两个数的乘积最小的。
	 * 	   解题思路：维护两个指针，最小和最大，判断他们的和判断两个指针如何移动
	 * @param array
	 * @param sum   两个数的和
	 * @return  返回和为sum的两个数， 如果有多对数字的和等于S，输出两个数的乘积最小的。
	 */
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {

		/* * 假设：找到两组满足条件的数组对（x，y）、（x+a,y-a），其中（x+y=S, 0<a<y-x）
		 * x*y-[(x+a)(y-a)]
		 *  =x*y-x*y-(y-x)a+a2
		 *  =a[a-(y-x)]
		 *  因为0<a<y-x,所以a-(y-x)<0,所以a[a-(y-x)]<0
		 *  因此(x,y)乘积一定比(x+a,y-a)小*/
		
		ArrayList<Integer> list = new ArrayList<>();
		if (array==null || array.length<2) {
			return list;
		}
		
		int start=0,end=array.length-1;
		//第一组的显然是最小的
		while(start<end){
			int tempSum = array[start]+array[end];
			if (tempSum == sum) {
				list.add(array[start]);
				list.add(array[end]);
				break;
				
			}else if(tempSum>sum){
				end--;
			}else {
				start++;
			}
		}
		
		return list;
	}
	
	/**
	 * 43.循环左移，如：字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
	 * @param str
	 * @param n 左移的位数
	 * @return
	 */
	public String leftRotateString(String str,int n) {
		
		if (str==null || str.equals("")) {
			return str;
		}
		
		//先获取,截取前的部分
		String  sub= str.substring(0,n);
		String str1 = str.substring(n);
		//合并两部分	
		String concat = str1.concat(sub);
		return concat;
	}
	
	/**
	 * 43.字符串循环左移，
	 * 解法一：在原字符串上修改“abcdef”循环左移9位（3位）前3位逆序，后3位逆序，整体再逆序。“cbafed”-> “defabc”
	 * 			
	 * @param str
	 * @param n
	 * @return
	 */
	public String leftRotateString1(String str,int n) {
		
		if (n<0) {
			throw new RuntimeException("参数n错误");
		}
		
		if (str==null || str.equals("")) {
			return str;
		}
		char[] charArray = str.toCharArray();
		reverse(charArray,0,n-1);  //翻转前半部分
		reverse(charArray,n,charArray.length-1);  //翻转后半部分
		reverse(charArray, 0, charArray.length-1);  //翻转整个数组
		return new String(charArray);
	}

	private void reverse(char[] charArray, int low, int high) {
		char temp;
		while(low<high){
			temp = charArray[low];
			charArray[low] = charArray[high];
			charArray[high] = temp;
			low++;
			high--;
		}
	}
	
	/**
	 * 44.反转英文句子
	 * 	  解题思路：经过两次反转，第一次整个句子反转，第二次单个单词在反转
	 * 		      或者使用spilt，分解成每一个单词，然后数组反转，添加" ",最后一个不需要
	 * @param str
	 * @return
	 */
	public String reverseSentence(String str) {
		if (str==null || str.trim().equals("")) {
			return str;
		}
		char[] chars = str.toCharArray();
		//反转整个句子
		reverse(chars, 0, chars.length-1);
		//反转每一个单词
		int blank = -1;
		for(int i=0;i<chars.length;i++){
			if (chars[i]==' ') {
				int nextBlank=i;
				reverse(chars, blank+1, nextBlank-1);
				blank = nextBlank;
			}
		}
		//最后一个单词独自反转
		reverse(chars, blank+1, chars.length-1);
		return new String(chars);
	}
	
	/**
	 * 45.扑克牌游戏，组成顺子返回true，有4个(1-13)和4个0(大小王-可以当成任意数)
	 * @param numbers
	 * @return
	 */
	public boolean isContinuous(int [] numbers) {	
		  /*max 记录 最大值
			min 记录  最小值
			min ,max 都不记0
			满足条件 1 max - min <5
			        2 除0外没有重复的数字(牌)
			        3 数组长度 为5*/
		if (numbers==null || numbers.length!=5) {
			return false;
		}
		
		int max=-1;
		int min=14;
		int[] times = new int[14];  //记录出现的次数
		for(int i=0;i<numbers.length;i++){
			//判断数字是否合法
			if (numbers[i]<0||numbers[i]>13) {
				return false;
			}
			
			times[numbers[i]]++;
			if (numbers[i]==0) {
				continue;
			}
			//d[0]是拿来防止大小王数量影响判断是否有其余对子的
			if (times[numbers[i]]>1) {
				return false;
			}
			
			//设置最大值和最小值
			if (numbers[i]>max) {
				max = numbers[i];
			}
			//0不进入最小值
			if (numbers[i]<min) {
				min = numbers[i];
			}
		}
		
		//最后循环出来肯定没有除0重复的数
		if (max-min<5) {
			return true;
		}
		return false;
	}
	
	/**
	 * 46.围圈游戏
	 * 首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
	 * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....
	 * 这样下去....直到剩下最后一个小朋友
	 * @param cycyle  圈的大小
	 * @param m  出圈数
	 * @return
	 */
	public int lastRemaining(int cycyle,int m){
		if (cycyle<0||m<1) {
			return -1;
		}
		LinkedList<Integer> linkedList = new LinkedList<>();
		for(int i=0;i<cycyle;i++){
			linkedList.add(i);
		}
		//留出最后一个数
		int bt=0;//要出去的索引位置
		while(linkedList.size()>1){
			//记住当前索引的位置
			bt = (bt+m-1) % linkedList.size();
			linkedList.remove(bt);
		}
		return linkedList.size()==1?linkedList.get(0):-1;
	}
}
