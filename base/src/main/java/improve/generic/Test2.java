package improve.generic;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		Integer arr[] = {1,2,3};
		swap(arr, 1, 2);
		
		System.out.println(Arrays.asList(arr));
		
		Integer arr2[] = {1,2,3,4,5};
		reverse1(arr2);
		System.out.println(Arrays.asList(arr2));
	}
	
	//编写一个泛形方法，交换数组上的任意两个位置的元素。
		/*public void swap(int[] arr,int pos1,int pos2){
			
	}*/
	//泛型都是先声明，后使用
	public static <T> void swap(T arr[],int pos1,int pos2){
		T temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
	
	//编写一个泛形方法，接收一个任意数组，并颠倒数组中的所有元素。
		public static <T> void reverse(T arr[]){
			
			int start = 0;
			int end = arr.length-1;
			
			while(true){
			
				if(start>=end){
					break;
				}
				
				
				T temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				
				start++;
				end--;
			}
	  }
	//重要
	public static <T> void reverse1(T arr[]){
		for(int i=0;i<arr.length;i++){
			arr[i]=arr[arr.length-1];
		}
	}
}
