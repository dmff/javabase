package offer.offer_test;

import java.util.Arrays;
import java.util.List;

import com.offer.Offer15;
import com.offer.Offer15.MyQueue;
import com.offer.vo.ListNode;
import com.offer.vo.TreeNode;

public class Offer15Test {

	public static void main(String[] args) {
		Offer15 offer = new Offer15();
		
		//测试1.
		int[][] arr={{1,2,3},{2,3,4},{3,4,5}};
		boolean flag = offer.find1(5, arr);
		System.out.println(flag);
		
		//测试2.
		String replaceSpace = offer.replaceSpace(new StringBuffer("we are happy"));
		System.out.println(replaceSpace);
		
		//测试3.
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		
		//List<Integer> list = offer.printListFromTailToHead(node1);
		List<Integer> list = offer.printListFromTailToHead2(node1);
		System.out.println(list);
		
		//测试4.
		TreeNode treeNode = offer.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8},new int[]{4,7,2,1,5,3,8,6});
		System.out.println(treeNode);
		
		//测试5
		MyQueue queue = new MyQueue();
		queue.push(1);
		queue.push(2);
		int pop = queue.pop();
		System.out.println(pop);
		
		//测试6.
		int min = offer.minNumberInRotateArray1(new int[]{3,4,5,1,2});
		System.out.println(min);
		
		//测试7.
		int num = offer.Fibonacci1(4);
		System.out.println(num);
		
		//测试8.
		int floor = offer.jumpFloor(4);
		System.out.println(floor);
		
		//测试9.
		int floorII = offer.jumpFloorII(3);
		System.out.println(floorII);
		
		//System.out.println(Integer.toBinaryString(-6));
		
		//测试11.
		int numberof1 = offer.Numberof1(3);
		System.out.println(numberof1);
		
		//测试12.
		System.out.println(offer.Power(2.0, 3));
		
		//测试13.
		int[] arr1= new int[]{1,2,3,4,6,5,7,8,9};
		offer.reOrderArray1(arr1);
		System.out.println(Arrays.toString(arr1));
		
		//测试14.
		System.out.println(offer.findKthToTail1(node1, 0));
		
		//测试15
		ListNode node = offer.reverseList(node1);
		while(node!=null){
			System.out.println(node);
			node=node.next;
		}
		
		
	}

}
