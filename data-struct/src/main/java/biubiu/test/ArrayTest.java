package biubiu.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.util.Arrays.copyOfRange;


public class ArrayTest {

	static int[] arr=new int[10];
	
	public static void main(String[] args) {
		//System.out.println(16>>1);
		
		int[] num = {1,2,3,4,5};
		System.out.println(Arrays.toString(num));
		
		int[] arr = copyOfRange(num, 3, 5);
		System.out.println(Arrays.toString(arr));
		
		//自身复制
		System.arraycopy(num, 0, num, 3, 2);
		System.out.println(Arrays.toString(num));
		
		int[] ids = new int[5];
		System.arraycopy(num, 2, ids, 0, 3);
		System.out.println(Arrays.toString(ids));
		
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	
	@Test
	public void test(){
		/*int[] arr = new int[]{4,8,6,12,16,14,10};
		int index=0;
		while(index<arr.length-1 && arr[index]<arr[arr.length-1]){
			index++;
		}
		
		System.out.println(index);*/
		
		System.out.println(4^6);
	}
	
	@Test
	public void test1(){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(new Integer(1));
		queue.add(new Integer(2));
		queue.add(new Integer(3));
		
		while(!queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}
	
	@Test
	public void test2(){
		String str = "1,#,#,";
		String[] split = str.split(",");
		System.out.println(Arrays.toString(split));
	}
	
}
