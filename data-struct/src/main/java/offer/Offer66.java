package offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.offer.vo.ListNode;
import com.offer.vo.TreeLinkNode;
import com.offer.vo.TreeNode;

public class Offer66 {

	/**
	 * 47.要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C） 求1 2 3 4
	 * ...n的和 解题思路：利用短路与 也可以使用异常退出循环
	 * 
	 * @param n
	 * @return 1~n的和
	 */
	public int sum(int n) {
		int ans = 0;
		@SuppressWarnings("unused")
		boolean flag = n > 0 && (ans += sum(n - 1)) > 0;
		return ans + n;
	}

	/**
	 * 48.要求在函数体内不得使用+、-、*、/四则运算符号
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int add(int num1, int num2) {
		/*
		 * 两个数异或：相当于每一位相加，而不考虑进位； 两个数相与，并左移一位：相当于求得进位； 将上述两步的结果相加,继续异或
		 */
		while (num2 != 0) { // 当进位数为0时说明结果相加完毕
			int sum = num1 ^ num2;
			int carry = (num1 & num2) << 1; // 进位数
			num1 = sum;
			num2 = carry;
		}
		return num1;
	}

	/**
	 * 交换两个数，不使用临时变量
	 * 
	 * @param num1
	 * @param num2
	 */
	public void exchangeNumber(int num1, int num2) {
		/*
		 * num1 = num1+num2; num2 = num1-num2; //num1 num1 = num1-num2; //num2
		 */
		// 使用异或，异或本身为0，异或0为本身
		num1 = num1 ^ num2;
		num2 = num1 ^ num2;
		num1 = num1 ^ num2;
	}

	/**
	 * 49.将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0 转化的为10进制
	 * 
	 * @param str
	 * @return
	 */
	public int strToint(String str) {
		// 要点：也可以加一个标志位，判断是否出现异常
		// 1. 异常输入和0输入的区分，设置一个全局变量
		// 2. 正负号的处理
		// 3. 溢出处理
		
		//初始判断
		if (str == null || str.trim().equals("")) {
			return 0;
		}

		char[] chars = str.toCharArray();
		//处理符号位
		int fuhao = 0;
		if (chars[0] == '-') { // 处理符号
			fuhao = -1;
			chars[0] = '0';
		} else if (chars[0] == '+') {
			fuhao = 1;
			chars[0] = '0';
		}

		int result = 0;
		for (int i = 0; i < chars.length; i++) {
			//判断是否合法
			if (chars[i] < '0' || chars[i] > '9') {
				result = 0;
				break;
			}
			 // char类型相减，会自动提升为int型
			int sum = result * 10 + chars[i] - '0';
			
			//判断是否溢出
			if ((sum - (chars[i] - '0')) / 10 != result) {
				result = 0;
				break; // 出现溢出，直接退出
			}

			result = result * 10 + chars[i] - '0';
		}

		return fuhao == 0 ? result : fuhao * result;
	}

	/**
	 * 50.找出数组中任意一个重复的数字,在一个长度为n的数组里的所有数字都在0到n-1的范围内
	 * 
	 * @param numbers
	 * @param length
	 *            数组的长度
	 * @param duplication
	 *            用来保存重复的数字，使用duplication[0]保存
	 * @return 对输入参数进行校验，合法返回true，否则返回false
	 */
	public boolean duplicate(int numbers[], int length, int[] duplication) {
		// 参数不合法的情况
		if (numbers == null || duplication == null || numbers.length < 2 || numbers.length != length) {
			return false;
		}

		// 判断数组是否合法（每个数都在0~n-1之间）
		for (int i = 0; i < length; i++) {
			if (numbers[i] < 0 || numbers[i] > length - 1) {
				return false;
			}
		}
		// 一开始都是false，没有访问过
		boolean[] flag = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (flag[numbers[i]] == true) { // 存在已经访问过的
				duplication[0] = numbers[i];
				return true;
			}
			flag[numbers[i]] = true;
		}
		return false;
	}

	/**
	 * 51.给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
	 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法
	 * 解题思路：说白了就是B[i]=A[1]*...*A[n-1]/A[i] 可以每次把A[i]置为1
	 * 
	 * @param A
	 *            数组A
	 * @return 返回构建的乘积数组
	 */
	public int[] multiply(int[] A) {
		if (A == null || A.length == 0) {
			return A;
		}
		int[] B = new int[A.length];

		// boolean changed = false;
		// 这样的时间复杂度是o(n^2)，显然效率不行
		for (int i = 0; i < B.length; i++) {
			B[i] = 1;
			for (int j = 0; j < A.length; j++) {
				if (i != j) {
					B[i] *= A[j];
				}
				/*
				 * int temp=1; if (i==j) { temp = A[j]; A[j] = 1; changed =
				 * true; } B[i]*=A[j]; //保证自会运行一次,***还不如直接暴力，当i!=j时，直接相乘*** if
				 * (changed) {A[j] = temp; changed = false; }
				 */
			}
		}
		return B;
	}

	/**
	 * 51.构建乘积矩阵的高效算法 B0: 1 A1 A2 ...An-2 An-1 B1: A0 1 A2 ...An-2 An-1 B2: A0
	 * A1 1 ...An-2 An-1 ... Bn-2: A0 A1 1 ...1 An-1 Bn-1: A0 A1 1 ...An-2 1
	 * 因此我们的思路就很清晰了，先算下三角中的连乘， 即我们先算出B[i]中的一部分，然后倒过来按上三角中的分布规律，把另一部分也乘进去。
	 * 
	 * @param A
	 * @return
	 */
	public int[] multiply_aviliable(int[] A) {
		if (A == null || A.length == 0) {
			return A;
		}

		int[] B = new int[A.length];

		// 计算下三角的连乘
		B[0] = 1;
		for (int i = 1; i < B.length; i++) {
			B[i] = B[i - 1] * A[i - 1];
		}
		// 用来记录连乘
		int temp = 1;
		for (int j = B.length - 2; j >= 0; j--) {
			temp *= A[j + 1];
			B[j] *= temp;
		}
		return B;
	}

	/**
	 * 52.模式匹配
	 * 	 实现一个函数用来匹配包括'.'和'*'的正则表达式,自己实现.和*(表示它前面的字符可以出现n次)
	 * 	"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
	 * @param str
	 * @param pattern
	 * @return
	 */
	public boolean match(char[] str, char[] pattern) {
		
		if (str==null && pattern==null) {
			return true;
		}
		
		if (str==null ||  pattern==null) {
			return false;
		}
		
		if (pattern.length==2 && pattern[0]=='.' && pattern[1]=='*') {
			return true;
		}
		
		Stack<Character> src = new Stack<>();
		Stack<Character> pat = new Stack<>();
		
		//字符串和匹配字符串入队列
		for(int i=0;i<str.length;i++){
			src.push(str[i]);
		}
		
		for(int i=0;i<pattern.length;i++){
			pat.push(pattern[i]);
		}
		
		 // 从尾匹配，解决*重复前一个字符的问题
		while(!src.isEmpty() && !pat.isEmpty()){
			if (Character.isLetter(pat.peek()) && !pat.peek().equals(src.peek())) {
				return false; //末尾不相等，直接返回false
			}
			//相等，直接出栈
			if (Character.isLetter(pat.peek()) && pat.peek().equals(src.peek())) {
				src.pop();
				pat.pop();
			}else if(pat.peek().equals('.')){
				pat.pop();
				pat.push(src.peek());
			}else {  //模式匹配'*'
				pat.pop();
				if (pat.peek().equals(src.peek())) { //相等
					//继续去除重复的元素
					pat.push(src.peek());  //推入src的元素
					pat.push('*');
					src.pop();	
				}else {
					 // 否则从模式栈弹栈，直到找到匹配目标串的字符，或遇到.
					while(!pat.isEmpty() && !pat.peek().equals(src.peek())&& !pat.peek().equals('.'))
						//弹出无效的字符与src里面的字符不相等的字符,比如aaa和ab*ac*a，弹出c
						pat.pop();
					
					if (!pat.isEmpty() && pat.peek().equals('.')) {
						// 如果遇到了‘.’ 直接替换为目标字符，再重新压入,会继续重复while的代码,
						pat.pop();
						pat.push(src.peek());
						pat.push('*');
					}
				}
			}
		}
		
		//两个栈都为null，说明匹配
		if (src.isEmpty() && pat.isEmpty()) {
			return true;
		}
		
		//存在这样的情况，src一开始就为null;或者//"aab","c*a*b" pat剩下c**
		if (src.isEmpty() && !pat.isEmpty() && pat.peek().equals('*')) {
			char c = pat.pop();
			while(!pat.isEmpty()){
				if (c=='*' || pat.peek()=='*' || c==pat.peek()) {
					c=pat.pop();
				}else 
					return false;
			}		
			return true;
		}
		//其他情况均不成功
		return false;
	}
	
	

	/**
	 * 53.判断字符串是否表示数值（包括整数和小数）
	 * 	   例如：字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值
	 * 		      但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是
	 * @param str
	 * @return
	 */
	public boolean isNumeric_reg(char[] str) {
		String string = new String(str);
		return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
	}
	
	/**
	 * 判断字符串是否表示数值（包括整数和小数），不采用正则的做法
	 * @param str
	 * @return
	 */
	public boolean isNumeric(char[] str) {
		if (str==null || str.length==0) {
			return false;
		}
		//标记符号、小数、e是否出现过
		boolean sign =false,decimal=false,hasE=false;
		for(int i=0;i<str.length;i++){
			//对e的判断
			if (str[i]=='e'||str[i]=='E') {
				if (i==str.length-1)  //e后面一定要跟数字 
					return false;
				if (hasE)    //不能存在两个e
					return false;
				hasE =true;
			}
			//对符号的判断
			else if (str[i]=='+'||str[i]=='-') {
				//第二次出现时，必须紧跟在e后面
				if (sign && str[i-1] !='e' && str[i-1]!='E') 
					return false;
				 // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
				if (!sign && i>0 && str[i-1] !='e' && str[i-1]!='E') 
					return false;
				sign =true;
			}
			
			//对小数点的判断
			else if(str[i]=='.'){
				 // e后面不能接小数点，小数点不能出现两次
				if (hasE || decimal) 
					return false;
				decimal =true;
			}
			
			//对字符的判断
			else if(str[i]<'0' || str[i]>'9'){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 54.从找出第一个只出现一次的字符
	 * 从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
	 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"
	 * @param str
	 * @return
	 */
	public static class FirstChar{

		int[] hashtable = new int[128];
		StringBuilder sb = new StringBuilder();
		public char firstAppearingOnce() {
			char[] charArray = sb.toString().toCharArray();
			for (char c : charArray) {
				if (hashtable[c]==1) {
					return c;
				}
			}
			return '#';
		}

		public void insert(char ch) {
			sb.append(ch);
			hashtable[ch]++;
//			if (hashtable[ch]==1) 
//				//这个O（N）是在insert函数中已经解决了，单从数组中取是O（1）
//				queue.offer(ch);  
		}
	}
	
	/**
	 * 55.链表形成环，找到链表的环的入口节点
	 * 		解题思路：
	 *     1.形成环的节点入口一定是重复的节点,所以借用hashset判断是否存在重复节点
	 * 	   2.找出快慢指针的相遇点，重新定义一个head指针，相遇指针和hea指针每次走一步，相遇点位环的入口
	 * 		  证明：总长a(环的前部分)+n,相遇点a+b(环的一部分),快指针已经走了n圈
	 * 			     a+b+nk=2a*b   ---->a=nk-b
	 * 				 a=相遇点环剩余的部分
	 * @param pHead
	 * @return
	 */
	public ListNode EntryNodeOfLoop(ListNode pHead){
		//为null或者只有一个节点不可能形成环
		if (pHead==null || pHead.next==null) {
			return null;
		}
		ListNode slow = pHead;
		ListNode fast = pHead;
		while(fast!=null && fast.next!=null){
			fast =fast.next.next;
			slow =slow.next;
			if (fast == slow) {
				break;
			}
		}
		
		//相遇点为null，没有环
		if (slow==null) {
			return slow;
		}
		ListNode head = pHead;
		while(head!=slow){
			head= head.next;
			slow =slow.next;
		}
		return slow;
	}
	
	/**
	 * 56.在一个排序的链表中，删除链表重复(不是去重)的节点，返回链表头指针
	 * 	   例如： 链表1->2->3->3->4->4->5 处理后为 1->2->5
	 * @param pHead
	 * @return 返回链表头指针
	 */
	public ListNode deleteDuplication(ListNode pHead){
		if (pHead == null || pHead.next ==null) {
			return pHead;
		}
		//由于是排好序的，所以可以直接往后移动
		ListNode curNode = pHead;
		ListNode preNode = null;
		ListNode nextNode = null;
		while(curNode!=null){	
			nextNode = curNode.next;
			boolean isRept = false;
			while (nextNode!=null && nextNode.val == curNode.val) {  //找到所有重复的节点
				 //发生了重复，则略过重复节点，找到不重复的next节点
				isRept = true;
				nextNode =nextNode.next;
			}
			
			if (isRept) {
				if (preNode != null) {  
					//preNode不为空，则将不重复的节点加入
					preNode.next = nextNode;
					
				}else {
					//preNod为空，表示头结点开始就是重复的
					pHead = null;
				}
			}else {  //当前结点和next节点不重复
				//如果头结点属于重复节点被删除后，preNode仍然==null
                //说明当前节点属于不重复的第一个节点，则将pHead = currentNode
				if (preNode==null) {
					pHead = curNode;
				}
				//没发现重复的情况下，将当前节点赋值给preNode
				preNode = curNode;
			}
			//更新currentNode，继续遍历
			curNode = nextNode;
		}
		return pHead;
	}
	
	/**
	 * 57.给定二叉树的一个节点，获取中序遍历当前结点的下一个节点，当前二叉树包含指向父节点的指针
	 * 	   注意：空节点是没有父节点的
	 *  示例图：
	 * 	 		 A
			   /   \	
			  B		C
			/  \   /  \
		   D    E F    G
		       / \
		      H   I
		      
	 * @param pNode
	 * @return
	 */
	public TreeLinkNode getNext(TreeLinkNode pNode){

		/*
		1.二叉树为空，则返回空；
		2.节点右孩子存在，则设置一个指针从该节点的右孩子出发，一直沿着指向左子结点的指针找到的叶子节点即为下一个节点；
		3. 若该节点不存在右子树：这时分两种情况：
				该节点为父节点的左子节点，则下一个节点为其父节点（如图节点 D ）
				该节点为父节点的右子节点，则沿着父节点向上遍历，知道找到一个节点的父节点的左子节点为该节点，
				则该节点的父节点下一个节点（如图节点 I ，沿着父节点一直向上查找找到 B （ B 为其父节点的左子节点），则 B 的父节点 A 为下一个节点）
		*/
		if (pNode==null) {
			return pNode;
		}
		
		if (pNode.right!=null) {
			//右子树存在
			pNode = pNode.right;
			while(pNode.left!=null){  //找左孩子
				pNode = pNode.left;
			}
			return pNode;
		}
		//说白了找到第一个parent.left等于node，返回parent节点
		while(pNode.parent!=null){
			if (pNode.parent.left == pNode) {
				return pNode.parent;
			}
			pNode = pNode.parent;
		}
		return null;
	}
	
	/**
	 * 58.判断二叉树是否是对称的，该二叉树和它的镜像相等，则是对称的
	 * @return
	 */
	public boolean isSymmetry(TreeNode root){
		if (root==null) {
			return true;
		}	
		return compare(root.left,root.right);
	}
	//比较两个节点
	private boolean compare(TreeNode left, TreeNode right) {
		if (left==null) {
			return right==null;
		}
		if (right==null) {
			return false;
		}
		if (left.val !=right.val) {
			return false;
		}
		return compare(left.right, right.left) && compare(left.left, right.right);
	}
	
	/**
	 * 58.判断二叉树是否对称，使用队列
	 * @param root
	 * @return
	 */
	public boolean isSymmetryWithQueue(TreeNode root){
		if (root==null) {
			return true;
		}
		LinkedList<TreeNode> queue1 = new LinkedList<>();
		LinkedList<TreeNode> queue2 = new LinkedList<>();
		queue1.push(root.left);
		queue2.push(root.right);
		while(!queue1.isEmpty() && !queue2.isEmpty()){
			TreeNode left = queue1.poll();
			TreeNode right = queue2.poll();
			//两边为null，继续往下运行
			if (left==null && right==null) {
				continue;
			}
			//只有一边为null
			if (right==null || left==null) {
				return false;
			}
			if (left.val !=right.val) {
				return false;
			}
			queue1.push(left.left);
			queue1.push(left.right);
			queue2.push(right.right);
			queue2.push(right.left);
		}
		return true;
	}
	
	/**
	 * 59.打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
	 * 	    第三行按照从左到右的顺序打印，其他行以此类推
	 * 
	 * 	   解题思路：采用层次遍历，中间使用标志位记录打印的顺序
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> print(TreeNode root){
		ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
		if (root==null) {
			return arrayLists;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		//或者先记住queue的size，然后每次取出所有的值
		queue.offer(new TreeNode(Integer.MIN_VALUE));  //区分层数的标志位
		
		boolean flag = false; //奇偶数标志位
		ArrayList<Integer> list = new ArrayList<>();
		while(!queue.isEmpty()){
			TreeNode node = queue.poll();
			if (node.val !=Integer.MIN_VALUE) {
				if (!flag) {
					//单行从左到又打印
					list.add(node.val);
				}else {
					//多行从又往左打印
					list.add(0,node.val);
				}
				
				if (node.left!=null) {
					queue.add(node.left);
				}
				if (node.right!=null) {
					queue.add(node.right);
				}
				
			}else{
				flag = !flag;
				//继续添加层数标志,注意，只有队列里面还有元素时才能添加标志位，不然会死循环
				if (!queue.isEmpty()) {
					queue.offer(new TreeNode(Integer.MIN_VALUE));  //区分层数的标志位
				}
				//因为会记住list的地址，所以需要重新创建一个list对象
				arrayLists.add(new ArrayList<>(list));
				//并且清空list的数据
				list.clear();
			}
		}
		return arrayLists;
	}
	
	/**
	 * 59.不使用标志为区分层数
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> print1(TreeNode root){
		ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
		if (root==null) {
			return arrayLists;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean flag = false; //奇偶数标志位
		while(!queue.isEmpty()){
			int size =queue.size();
			ArrayList<Integer> list = new ArrayList<>();
			//不能使用queue.size，因为是动态变化的
			for(int i=0;i<size;i++){
				TreeNode node = queue.poll();
				if (node.left!=null) {
					queue.add(node.left);
				}
				if (node.right!=null) {
					queue.add(node.right);
				}
				
				if (!flag) {
					//单行从左到又打印
					list.add(node.val);
				}else {
					//多行从又往左打印
					list.add(0,node.val);
				}
			}
			arrayLists.add(list);
		}
		return arrayLists;
	}
	
	/**
	 * 60.从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
	 * @param root
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> printlevel(TreeNode root){
		ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
		if (root==null) {
			return arrayLists;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()){
			int size =queue.size();
			ArrayList<Integer> list = new ArrayList<>();
			//不能使用queue.size，因为是动态变化的
			for(int i=0;i<size;i++){
				TreeNode node = queue.poll();
				if (node.left!=null) {
					queue.add(node.left);
				}
				if (node.right!=null) {
					queue.add(node.right);
				}
				list.add(node.val);
			}
			arrayLists.add(list);
		}
		return arrayLists;
	}
	
	/**
	 * 61.请实现两个函数，分别用来序列化和反序列化二叉树
	 * 	  解题思路：使用先序遍历序列化和反序列化,也可以使用层序序列和反序列
	 * @author dmf
	 *
	 */
	public static class SerialTree{
		int index = -1;
		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			if (root==null) {
				sb.append("#,");
				return sb.toString();
			}
			sb.append(root.val+",");
			sb.append(serialize(root.left));
			sb.append(serialize(root.right));
			
			return sb.toString();
		}
		public TreeNode deSerialize(String str){
			index++;
			int length =str.length();
			if (str==null || index>=length) {
				return null;
			}
			
			String[] strarr = str.split(",");
			TreeNode node = null;
			if (!strarr[index].equals("#")) {
				node = new TreeNode(Integer.valueOf(strarr[index]));
				node.left = deSerialize(str);
				node.right = deSerialize(str);
			}
			return node;
		}
	}
	
	/**
	 * 62.给定一颗二叉搜索树，找到按从小到大的顺序第k个节点
	 * 	   解题思路：由于二叉树的中序遍历是排序的，可以使用中序遍历，使用控制层数的flag
	 * @param node
	 * @return
	 */
	int index=0;
	public TreeNode kthNode(TreeNode root,int k){
		if (root !=null) {
			TreeNode node = kthNode(root.left, k);
			// 这里一定要注意加上这句话，事实上不加这句话是过不了的，这个是为了保证递归能够逐层往外传
			if (node!=null) {
				return node;
			}
			
			index++;
			if (index==k) {
				return root;
			}
			
			node = kthNode(root.right, k);
			if (node!=null) {
				return node;
			}
		}
		
		return null;
	}
	
	/**
	 * 63.如何快速的获取一个数据流中的中位数
	 * 	   解题思路：使用最小堆+最大堆，
	 * 			     1.满足两个堆得元素个数差不能超过1，2.最大堆的所有元素小于最小堆所有元素
	 * 				 这样就会保证当元素为奇数时，中位数在最大堆的第一个元素，
	 * 					       当元素个数为偶数时，中位数是最大堆和最小堆第一个元素的平均数
	 * @author dmf
	 *
	 */
	public static class GetMedian{
		
		int count =0;  //记录元素个数
		private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		//优先队列默认是最小堆,实现自己的比较器，变成最大堆
		private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		public void insert(Integer num){
			count++;
			//偶数，加入最小堆
			if ((count&1)==0) {  //判断偶数的高效判别法
				//最大堆最大的元素比加入的元素大，需要调整往最大堆插入的元素，需要把最大堆里面最大的元素往里面插入
				/*if (!maxHeap.isEmpty() && maxHeap.peek() >num) { 
					maxHeap.offer(num);
					num = maxHeap.poll();
				}*/
				maxHeap.offer(num);
				num = maxHeap.poll();
				minHeap.offer(num);
			}else {
				//奇数，往最大堆里面插入元素
				//如果最小堆里面最小的元素比插入的元素小，需要把最小堆里面最小的元素插入最大堆中
				
				//注意：如果num小的话，自然会在头部，否则最小堆里面的元素比较小
				minHeap.offer(num);
				num = minHeap.poll();
				maxHeap.offer(num);
			}
		}
		
		public double getMedian(){
			if (count == 0) {
				throw new RuntimeException("no availiable number");
			}
			
			double result;
			if ((count&1)==0) {
				result= (maxHeap.peek()+minHeap.peek())/2.0;
			}else {
				result= maxHeap.peek();
			}
			return result;
		}
	}
	
	/**
	 * 64.给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
	 * 	   例如：如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
	 * 		      那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}
	 * @param array 
	 * @param size 窗口大小
	 * @return  所有滑动窗口里数值的最大值。
	 */
	public ArrayList<Integer> maxInWindows(int[] array,int size){
		ArrayList<Integer> list = new ArrayList<>();
		if (array == null || array.length <size || size<0) {
			return list;
		}
		
		//这样的时间复杂度过高,改进方案，求出第一个窗口的最大值，然后往后移动，
		//判断最大值是否在窗口内，在的话和后面的index+1比较，不在的话需要重新计算最大值
		for(int i=0;i<=array.length-size;i++){
			int max = array[i];
			for(int j=i+1;j<i+size;j++){
				if (array[j]>max) {
					max = array[j];
				}
			}
			list.add(max);
		}
		return list;
	}
	
	/**
	 * 64.使用双端队列改良版
	 *    判断最大值是否在窗口内，在的话和后面的index+1比较，不在的话需要重新计算最大值
	 * @param array
	 * @param size
	 * @return
	 */
	public ArrayList<Integer> maxInWindows_Advance(int[] array,int size){
		/**
		用一个双端队列，队列第一个位置保存当前窗口的最大值，当窗口滑动一次
		1.判断当前最大值是否过期
		2.新增加的值从队尾开始比较，把所有比他小的值丢掉
		*/
		ArrayList<Integer> list = new ArrayList<>();
		if (array == null || array.length <size || size<=0) {
			return list;
		}
		
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		for(int i=0;i<array.length;i++){
			//如果加入的元素比里面的元素大
			/*
			 * 分析，为什么使用while而不是if，假如后面元素比头元素小，是加入到队尾的，
			 * 当出现更大的时候，移除的较小的元素
			 */
			//必须先判断头元素是否过期
			if (deque.isEmpty()) {
				deque.add(i);
			}else if (deque.peekFirst() == i-size) {
				deque.pollFirst();
			}
			
			while (!deque.isEmpty() && array[i] >=array[deque.peekLast()]) {
				deque.pollLast();
			}
			deque.addLast(i);
			
			
			if (i+1>=size) {
				list.add(array[deque.peekFirst()]);
			}
		}
		return list;
	}
	
	/**
	 * 65.用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径
	 * 		每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
	 * 		如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 
	 * 		例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
	 * 		测试用例："ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS
	 * 				ABCEHJIG
	 * 				SFCSLOPQ
	 * 				ADEEMNOE
	 * 				ADIDEJFM
	 * 				VCEIFGGS",5,8,"SGGFIECVAASABCEEJIGOEM"
	 * 
	 * 		解题思路：从矩阵的每一个点出发，看是否能找到这样的一条路径，如果不能，回退，继续往下一点去寻找
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @param str
	 * @return
	 */
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
		//状态数组保存之前访问过的字符
		boolean[] visited = new boolean[matrix.length]; 
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if (search(matrix,rows,cols,i,j,str,0,visited)) 
					return true;	
			}
		}
		return false;
	}

	private boolean search(char[] matrix, int rows, int cols, int r, int c, char[] str, int index, boolean[] visited) {
		//由于有加号，所有r和c不能大于当前的行列数,r*col+c当前索引数
		if (r<0 || r>=rows || c<0 || c>=cols || matrix[r*cols+c]!=str[index] || visited[r*cols+c]) {
			return false;
		}
		
		if (index == str.length-1) {
			return true;
		}
		
		//回溯重点，当没有找到下一个，回退
		visited[r*cols+c] = true;
		if (search(matrix, rows, cols, r-1, c, str, index+1, visited)||
				search(matrix, rows, cols, r, c-1, str, index+1, visited)||
				search(matrix, rows, cols, r+1, c, str, index+1, visited)||
				search(matrix, rows, cols, r, c+1, str, index+1, visited)) {
			return true;
		}
		//回退
		visited[r*cols+c] = false;
		return false;
	}
	
	/**
	 * 66.机器人走矩阵问题，可以上下左右走动，但是不能进入x+y大于k的格子，请问机器人能够到达多少个格子？
	 * 	    例如：k=18时，可以进入(35,37) 3+5+7+3=18，不可以进入(35,38) 3+5+3+8=19
	 * @param threshold
	 * @param rows
	 * @param cols
	 * @return
	 */
	public int movingCount(int threshold, int rows, int cols){
		//标记是否走过
		boolean[][] visited = new boolean[rows][cols];
		return countStep(threshold,0,0,rows,cols,visited);
	}

	private int countStep(int threshold, int r, int c, int rows, int cols,boolean[][] visited) {
		if (r<0 || r>=rows || c<0 || c<cols || visited[r][c] || bitSum(r)+bitSum(c)>threshold) {
			return 0;
		}
		visited[r][c] = true;
		return 1+countStep(threshold, r-1, c, rows, cols, visited) + countStep(threshold, r, c-1, rows, cols, visited)
				+ countStep(threshold, r+1, c, rows, cols, visited) + countStep(threshold, r, c+1, rows, cols, visited);
	}
	
	private int bitSum(int t){
		int count=0;
		while(t!=0){
			count +=t%10;
			t=t/10;
		}
		return count;
	}
}
