package offer.offer_test;

import java.util.ArrayList;
import java.util.List;

import com.offer.Offer30;
import com.offer.vo.ListNode;
import com.offer.vo.RandomListNode;
import com.offer.vo.TreeNode;

public class Offer30Test {

	public static void main(String[] args) {
		//testMerge();
		//testprintMatrix();
//		PrintFromTopToBottom();
		//verifySquenceOfBST();
		//findPath();
		//conver();
		//FindGreatestSumOfSubArray();
		testcopy();
	}

	@SuppressWarnings("unused")
	private static void testMerge() {
		//测试16
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(5);
		
		node1.next = node2;
		node2.next =node3;
		
		ListNode node4 = new ListNode(2);
		ListNode node5 = new ListNode(4);
		ListNode node6 = new ListNode(6);
		node4.next=node5;
		node5.next=node6;
		
		Offer30 offer = new Offer30();
		//ListNode merge = offer.Merge(node1, node4);
		ListNode merge = offer.Merge1(node1, node4);
		while(merge!=null){
			System.out.println(merge);
			merge=merge.next;
		}
	}
	
	private static void testcopy(){
		RandomListNode node1 = new RandomListNode(1);
		RandomListNode node2 = new RandomListNode(2);
		RandomListNode node3 = new RandomListNode(3);
		node1.next =node2;
		node1.random = node3;
		node2.next = node3;
		node3.random = node2;
		Offer30 offer = new Offer30();
		RandomListNode clone = offer.clone(node1);
		while(clone!=null){
			System.out.print(clone.label+" ");
			if (clone.random==null) {
				System.out.println("clone random is null");				
			}else {
				System.out.println(clone.random.label);				
			}
			clone = clone.next;
		}
	}
	
	@SuppressWarnings("unused")
	private static void testprintMatrix(){
//		int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] matrix={{1},{2},{3},{4},{5}};
		Offer30 offer = new Offer30();
		List<Integer> list = offer.printMatrix(matrix);
		for (Integer integer : list) {
			System.out.print(integer+" ");
		}
	}
	
	@SuppressWarnings("unused")
	private static void PrintFromTopToBottom(){
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		node1.left=node2;
		node1.right=node3;
		node2.left=node4;
		Offer30 offer = new Offer30();
		ArrayList<Integer> list = offer.PrintFromTopToBottom(node1);
		for (Integer integer : list) {
			System.out.print(integer+" ");
		}
	}
	
	@SuppressWarnings("unused")
	private static void verifySquenceOfBST(){
		Offer30 offer = new Offer30();
		boolean b = offer.verifySquenceOfBST(new int[]{4,8,6,12,16,14,10});
		System.out.println(b);
	}
	
	@SuppressWarnings("unused")
	private static void findPath(){
		Offer30 offer = new Offer30();
		
		TreeNode root = new TreeNode(10);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(12);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(7);
		
		root.left=node1;
		root.right = node2;
		
		node1.left = node3;
		node1.right = node4;
		List<List<Integer>> findPath = offer.findPath(root, 22);
		for (List<Integer> list : findPath) {
			for (Integer integer : list) {
				System.out.print(integer+" ");
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private static void conver(){
		Offer30 offer = new Offer30();
		
		TreeNode root = new TreeNode(10);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(12);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(7);
		
		root.left=node1;
		root.right = node2;
		
		node1.left = node3;
		node1.right = node4;
		
		TreeNode linkList = offer.convertLinkListWithStack(root);
		while(linkList!=null){
			System.out.print(linkList.val+" ");
			linkList = linkList.right;
		}	
	}
	
	@SuppressWarnings("unused")
	private static void FindGreatestSumOfSubArray(){
		Offer30 offer = new Offer30();
		int max = offer.FindGreatestSumOfSubArray1(new int[]{6,-3,-2,7,-15,1,2,2});
		System.out.println(max);
	}
}
