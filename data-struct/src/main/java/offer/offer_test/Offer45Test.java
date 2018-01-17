package offer.offer_test;

import java.util.ArrayList;

import org.junit.Test;

import com.offer.Offer45;
import com.offer.vo.ListNode;
import com.offer.vo.TreeNode;

public class Offer45Test {
	
	Offer45 offer = new Offer45();

	@Test
	public void testfirstNotRepeatingChar(){
		int index = offer.firstNotRepeatingChar("abcdab");
		System.out.println("第一次只出现一次的字符的位置："+index);
	}
	
	@Test
	public void testFindFirstCommonNode1(){
		ListNode node0 = new ListNode(0);
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(5);
		ListNode node3 = new ListNode(1);
		ListNode node4 = new ListNode(2);
		ListNode node5 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node0.next = node4;
		node4.next=node5;
		ListNode node = offer.FindFirstCommonNode1(node1, node0);
		System.out.println(node);
	}
	
	@Test
	public void testGetNumberOfK(){
		int numberOfK = offer.GetNumberOfK(new int[]{3,3,3,3,4,5}, 3);
		System.out.println(numberOfK);
	}
	
	@Test
	public void testFindNumsAppearOnce(){
		int[] num1=new int[1];
		int[] num2=new int[1];
		offer.FindNumsAppearOnce(new int[]{2,4,3,6,3,2,5,5}, num1, num2);
		System.out.println(num1[0]+"  "+num2[0]);
	}
	
	@Test
	public void testFindContinuousSequence(){
		ArrayList<ArrayList<Integer>> arrayList = offer.FindContinuousSequence(100);
		for (ArrayList<Integer> arrayList2 : arrayList) {
			for (Integer integer : arrayList2) {
				System.out.print(integer+" ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testFindNumbersWithSum(){
		ArrayList<Integer> list = offer.FindNumbersWithSum(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}, 21);
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
	
	@Test
	public void testleftRotateString(){
		String leftRotateString = offer.leftRotateString1("abcXYZdef", 3);
		System.out.println(leftRotateString);
	}
	
	@Test
	public void testdeept(){
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
		int depth1 = offer.treeDepth1(node1);
		System.out.println(depth1);
	}
}
