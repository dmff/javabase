package improve.generic;

import java.util.Arrays;

public class SwitchObject {

	public static void main(String[] args) {
		Integer arr[] = {1,2,3,4};
		reverse(arr);
		System.out.println(Arrays.asList(arr));
	}
	
	//编写一个泛形方法，接收一个任意数组，并颠倒数组中的所有元素。
	public static <T> void reverse(T arr[]){
		int start = 0;
		int end = arr.length-1;
		
		//交换
		for(;;){
			if (start>=end) {
				break;
			}
		
		
		T temp =arr[0];
		temp =arr[start];
		arr[start]=arr[end];
		arr[end]=temp;
		
		start++;
		end--;
		}
	}
}
