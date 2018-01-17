package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.leetcode.po.ListNode;
import com.leetcode.po.TreeNode;

public class List {

	/**
	 * 1.使用O(nlogn) 时间复杂度给链表排序 考点：1.快慢指针 2.归并排序 1.找到链表中点
	 * （快慢指针思路，快指针一次走两步，慢指针一次走一步，快指针在链表末尾时，慢指针恰好在链表中点） 2.写出merge函数，即如何合并链表。
	 * （见merge-two-sorted-lists 一题解析） 3.写出mergesort函数，实现上述步骤
	 * 
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		// 空或者只有一个
		if (head == null || head.getNext() == null) {
			return head;
		}

		ListNode middle = getMiddle(head);
		// 断开,获取了两部分
		ListNode midnext = middle.getNext();
		middle.setNext(null);
		return mergeTwoLists(sortList(head), sortList(midnext));
	}

	// 给两个链表排序，前提以及排好序
	private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode preHead = new ListNode(0), cur1 = list1, cur2 = list2, cur = preHead;
		while (cur1 != null && cur2 != null) {
			if (cur1.getVal() < cur2.getVal()) {
				cur.setNext(cur1);
				cur1 = cur1.getNext();
			} else {
				cur.setNext(cur2);
				cur2 = cur2.getNext();
			}
			cur = cur.getNext();
		}
		// 判断到底list1或者list2未空
		cur.setNext(cur1 == null ? cur2 : cur1);
		return preHead.getNext();
	}

	// 获取中节点
	private ListNode getMiddle(ListNode head) {
		// 空或者只有一个
		if (head == null || head.getNext() == null) {
			return head;
		}
		ListNode fast, slow; // 快慢指针
		fast = slow = head;
		// 快指针是慢指针的两倍
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			// 偶数时取第一个
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}
		return slow;
	}

	/**
	 * 2.使用插入排序对链表进行排序
	 * 
	 * @param head
	 * @return
	 */
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.getNext() == null) {
			return head;
		}

		ListNode sortedList = new ListNode(Integer.MIN_VALUE);
		ListNode cur = head; // 当前结点
		while (cur != null) {
			// 保存当前结点的下一节点,因为cur会移动
			ListNode next = cur.getNext();
			ListNode pre = sortedList;
			// 如果前一节点的后一个节点不为空，且小于当前节点值，则顺移
			while (pre.getNext() != null && pre.getNext().getVal() < cur.getVal()) {
				pre = pre.getNext();
			}
			// 交换两个节点，cur节点放入pre的next节点中
			cur.next = pre.next;
			pre.next = cur;

			// 处理下一个节点
			cur = next;
		}
		return sortedList.getNext();
	}

	/**
	 * 重要 3.反转链表：{1，2，3，4}->{1,4,2,3} 解题思想：分为三步，1.利用快慢指针找到中节点
	 * 2.反转中节点后的链表，3.合并两个链表
	 * 
	 * @param head
	 */
	public void reorderList(ListNode head) {
		if (head == null || head.getNext() == null) {
			return;
		}
		// 1.找到中节点
		ListNode fast, slow;
		fast = slow = head;
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		// 2.拆分链表，并且反转之后的链表
		ListNode after = slow.getNext();
		slow.setNext(null);
		ListNode pre = null;
		while (after != null) {
			// 一次和后面的节点交换，达到反转的目的
			ListNode temp = after.getNext();
			after.setNext(pre);
			pre = after;
			after = temp;
		}

		// 3.合并来给你个链表
		ListNode first = head;
		after = pre;
		while (first != null && after != null) {
			ListNode ftemp = first.getNext();
			ListNode atemp = after.getNext();
			// 把after节点加到first上
			first.setNext(after);
			// first往后移
			first = ftemp;
			// 把first节点加到after上
			after.setNext(first);
			// after往后移
			after = atemp;
		}
	}

	/**
	 * 4.找到循环的节点，没有则返回null，不借助任何辅助空间
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {

		/*
		 * while(head!=null){ //存在某一结点的next指向next，则存在循环节点 ListNode next =
		 * head.getNext(); while(next!=null){ if (next == head) { return head; }
		 * next = next.getNext(); }
		 * 
		 * head = next; } return null;
		 */

		// 使用hashset,通过
		Set<ListNode> sets = new HashSet<>();
		while (head != null) {
			if (!sets.add(head)) {
				return head;
			}
			head = head.next;
		}
		return null;
	}

	/**
	 * 使用快慢指针解题： 1.还是先用快慢指针方法，找出快慢指针相遇的点； 2.重新定义两个指针，一个为head，另一个为快慢指针相遇点；
	 * 3.两个指针每次走一步，相遇点则是链表环的起点；
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycleWithpoint(ListNode head) {
		if (head == null) {
			return null;
		}
		// 1.找到相遇节点
		ListNode fast, slow;
		fast = slow = head;
		while (fast != null && fast.getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
			if (fast == slow) {
				break;
			}
		}
		// 2.判断相遇节点是否为空
		if (slow == null) {
			return null;
		}

		fast = head;
		while (slow != fast) {
			slow = slow.getNext();
			fast = fast.getNext();
		}
		return slow;
	}

	/**
	 * 判断是否存在环： 解题思路：1.使用set，2.使用快慢指针，相遇则存在环
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		/*
		 * while(head!=null){ ListNode next = head.getNext(); while(next!=null){
		 * if (next == head) { return true; } next = next.getNext(); } head =
		 * head.getNext(); } return false;
		 */

		ListNode fast, slow;
		fast = slow = head;
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 5.深复制链表，多了random指针 主要思想还是先拷贝新节点，插入到原节点的后边；然后再 拷贝随机指针；
	 * 最后将新节点从原链表中分离出，注意要保证原链表正常。
	 */
	public ListNode copyListNodeWithMap(ListNode head) {
		if (head == null) {
			return null;
		}

		Map<ListNode, ListNode> map = new HashMap<>();
		ListNode node = head;
		while (node != null) {
			map.put(node, new ListNode(node.getVal()));
			node = node.getNext();
		}

		node = head;
		while (node != null) {
			map.get(node).setNext(map.get(node.getNext())); // 设置next节点
			map.get(node).setRandom(map.get(node.getRandom())); // 设置random节点
			node = node.getNext();
		}

		return map.get(head);
	}

	public ListNode copyListNode(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode node = head;
		ListNode newNode;
		// 第一遍扫描：对每个结点进行复制，把复制出来的新结点插在原结点之后
		while (node != null) { // 复制每一个节点
			newNode = new ListNode(node.getVal());
			// 把复制出的新节点加到原节点之后,插入节点的操作
			newNode.setNext(node.getNext());
			node.next = newNode;
			node = newNode.next;
		}

		node = head;
		// 第二遍扫描：根据原结点的random，给新结点的random赋值
		while (node != null) {
			if (node.random != null) {
				node.next.random = node.random;
				node = node.next.next;
			}
		}

		ListNode newhead = head.next;
		// 第三遍扫描，把新节点从原链表中分离
		node = head;
		while (node != null) {
			// 删除节点操作,node.next节点,也就是newNode节点
			newNode = node.next;
			node.next = newNode.next;
			// 把删除的节点加到newhead
			// newhead.next = newNode;
			if (newNode.next != null) {
				newhead = newNode.next.next;
			}
			node = node.next;
		}

		return newhead;
	}

	/**
	 * 6.把已经排好序的链表转化成二叉搜索树 :需要找到中节点
	 * 二叉搜索树：左边小，右边大：主要有serach、insert、delete、traversal
	 * 
	 * @param head
	 * @return
	 */
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) 
			return null;
		if (head.next == null) 
			return new TreeNode(head.getVal());
		
		ListNode fast,slow,mid=null;
		fast = slow = head;
		//找到中节点
		while(fast!=null&&fast.next!=null){
			mid = slow;
			slow =slow.next;
			fast = fast.next.next;
			
		}
		
		TreeNode root = new TreeNode(slow.getVal());
		//断开链表, 中节点为root节点，需要在前一位断开
		mid.next = null;
		root.left = sortedListToBST(head);
		root.right = sortedListToBST(slow.next);
		return root;
	}
	
	
}
