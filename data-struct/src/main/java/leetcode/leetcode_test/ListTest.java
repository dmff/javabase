package leetcode.leetcode_test;

import org.junit.Test;

import com.leetcode.List;
import com.leetcode.po.ListNode;
import com.leetcode.po.TreeNode;

public class ListTest {

	@Test
	public void testsortList(){
		ListNode node = new ListNode(6);
		ListNode node2 = new ListNode(5);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(2);
		ListNode node6 = new ListNode(1);
		node.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(node6);
		
		
		//ListNode sortList = new List().sortList(node);
		ListNode sortList = new List().insertionSortList(node);
		System.out.println(sortList);
	}
	
	@Test
	public void testreorderList(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		new List().reorderList(node1);
		System.out.println(node1);
	}
	
	@Test
	public void testdetectCycle(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		@SuppressWarnings("unused")
		ListNode node4 = new ListNode(4);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node1);
		
		ListNode detectCycle = new List().detectCycleWithpoint(node1);
		System.out.println(detectCycle);
	}
	
	@Test
	public void testhasCycle(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		//ListNode node4 = new ListNode(4);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node2);
		
		boolean hasCycle = new List().hasCycle(node1);
		System.out.println(hasCycle);
	}
	
	@Test
	public void testcopy(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.random = node4;
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		
		ListNode node = new List().copyListNode(node1);
		System.out.println(node);
	}
	
	@Test
	public void testconverser(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		
		TreeNode treeNode = new List().sortedListToBST(node1);
		System.out.println(treeNode);
	}
}
