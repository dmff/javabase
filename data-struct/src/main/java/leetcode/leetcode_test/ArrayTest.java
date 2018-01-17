package leetcode.leetcode_test;



import java.util.Arrays;

import org.junit.Test;

import com.leetcode.Array;
import com.leetcode.po.Point;
import com.leetcode.po.TreeNode;

public class ArrayTest {

	private Array array = new Array();;
	
	/*@Before
	public void before(){
		array = new Array();
	}*/
	
	@Test
	public void testEvalRPN(){
		int evalRPN = new Array().evalRPN(new String[]{"2", "1", "+", "3", "*"});
		System.out.println(evalRPN);
	}
	
	@Test
	public void testmaxPoints(){
		int maxPoints = new Array().maxPoints(new Point[]{
				new Point(1, 2),new Point(1, 3),new Point(1, 4)
		});
		System.out.println(maxPoints);
	}
	@Test
	public void testsolve(){
		char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		new Array().solve(board);
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testlongestConsecutive(){
		int length = new Array().longestConsecutive(new int[]{100,1,2,3,200,4});
		System.out.println(length);
	}
	
	@Test
	public void testmaxProfit(){
		int profit = new Array().maxProfitone2one(new int[]{20,10,30,40,50});
		System.out.println(profit);
	}
	@Test
	public void testmaxProfit2(){
		int profit = new Array().maxProfitone2money(new int[]{20,10,30,40,50});
		System.out.println(profit);
	}
	@Test
	public void testmaxProfit3(){
		int profit = new Array().maxProfitwithonestock(new int[]{30,10,30,40,50});
		System.out.println(profit);
	}
	
	@Test
	public void testsortedArrayToBST(){
		TreeNode node = new Array().sortedArrayToBST(new int[]{1,3});
		System.out.println(node);
	}
	
	@Test
	public void testmerge(){
		int[] A = new int[20];
		for(int i=1;i<=10;i++){
			A[i-1] = i*3;
		}
		
		int[] B = {1,2,4,6,8,10,14,16,18,20};
//		int[] A={1};
//		int[] B={};
		array.merge1(A, 10, B, B.length);
		System.out.println(Arrays.toString(A));
	}
}
