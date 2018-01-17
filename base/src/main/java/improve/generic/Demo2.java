package improve.generic;

import java.util.Arrays;

public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer arr[] = {1,2,3,4};
		reverse(arr);
		System.out.println(Arrays.asList(arr));
	}

	public static <T> void reverse(T arr[]){
		
		int start = 0;
		int end = arr.length-1;
		
		
		for(;;){
			if(start>=end){
				break;
			}
			T temp = arr[0];
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			
			start++;
			end--;
		}
		
	}


}
