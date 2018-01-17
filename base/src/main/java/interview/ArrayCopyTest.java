package interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class ArrayCopyTest {

	static int[] arr=new int[10];
	
	public static void main(String[] args) {
		//System.out.println(16>>1);
		
		int[] num = {1,2,3,4,5};
		System.out.println(Arrays.toString(num));
		
		//int[] arr = Arrays.copyOfRange(num, 3, 5);
		//System.out.println(Arrays.toString(arr));
		
		//自身复制
		System.arraycopy(num, 0, num, 3, 2);
		System.out.println(Arrays.toString(num));
		
		int[] ids = new int[5];
		System.arraycopy(num, 2, ids, 0, 3);
		System.out.println(Arrays.toString(ids));
		
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
