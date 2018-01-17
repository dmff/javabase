package offer.offer_test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.offer.Offer66;
import com.offer.Offer66.SerialTree;
import com.offer.vo.ListNode;
import com.offer.vo.TreeLinkNode;
import com.offer.vo.TreeNode;

public class Offer60Test {

	Offer66 offer = new Offer66();
	
	@Test
	public void testSum(){
		int sum = offer.sum(1);
		System.out.println(sum);
	}
	
	@Test
	public void testAdd(){
		int sum = offer.add(5, -5);
		System.out.println(sum);
	}
	
	@Test
	public void teststrToint(){
		int i = offer.strToint("0123");
		System.out.println(i);
	}
	
	@Test
	public void test(){
		boolean[] flag = new boolean[2];
		System.out.println(flag[0]+" "+flag[1]);
	}
	
	@Test
	public void testduplicate(){
		int[] arr1 = {2,3,1,0,2,5,3};
		int[] duplication = new int[1];
		boolean flag = offer.duplicate(arr1, arr1.length, duplication);
		if (flag) {
			System.out.println(duplication[0]);
		}else {
			System.out.println("运行中出现错误");
		}
	}
	
	@Test
	public void testmultiply(){
		int[] multiply = offer.multiply_aviliable(new int[]{1,2,3,4,5});
		Arrays.stream(multiply).forEach(e->{System.out.print(e+" ");});
	}
	
	@Test
	public void testisnumber(){
		boolean isnumber = offer.isNumeric(new char[]{'-','1','E','-','1','6'});
		System.out.println(isnumber);
	}
	
	@Test
	public void testdelete(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(4);
		ListNode node6 = new ListNode(4);
		ListNode node7 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		ListNode node = offer.deleteDuplication(node1);
		while(node!=null){
			System.out.println(node.val);
			node = node.next;
		}
	}
	
	@Test
	public void testgetNext(){
		TreeLinkNode node1 = new TreeLinkNode(1);
		TreeLinkNode node2 = new TreeLinkNode(2);
		TreeLinkNode node3 = new TreeLinkNode(3);
		TreeLinkNode node4 = new TreeLinkNode(4);
		TreeLinkNode node5 = new TreeLinkNode(5);
		TreeLinkNode node6 = new TreeLinkNode(6);
		TreeLinkNode node7 = new TreeLinkNode(7);
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		node2.parent = node1;
		
		node3.left = node6;
		node3.right = node7;
		node3.parent = node1;
		
		System.out.println(offer.getNext(node1));
	}
	
	@Test
	public void testprint(){
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		
		node3.left = node6;
		node3.right = node7;
		
		ArrayList<ArrayList<Integer>> arrayList = offer.print1(node1);
		for (ArrayList<Integer> arrayList2 : arrayList) {
			for (Integer integer : arrayList2) {
				System.out.print(integer+" ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testSer(){
		TreeNode node1 = new TreeNode(8);
		TreeNode node2 = new TreeNode(6);
		TreeNode node3 = new TreeNode(10);
		TreeNode node4 = new TreeNode(5);
		TreeNode node5 = new TreeNode(7);
		TreeNode node6 = new TreeNode(9);
		TreeNode node7 = new TreeNode(11);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		
		node3.left = node6;
		node3.right = node7;
//		SerialTree serialTree = new SerialTree();
//		String string = serialTree.serialize(node1);
//		System.out.println(string);
		TreeNode node = offer.kthNode(node1, 3);
		System.out.println(node);
	}
	
	@Test
	public void testmaxInWindows(){
		ArrayList<Integer> arrayList = offer.maxInWindows_Advance(new int[]{2,3,4,2,6,2,5,1}, 3);
		arrayList.stream().forEach(e->{System.out.print(e+" ");});
	}
}
