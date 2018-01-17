package biubiu.sort;

import java.util.Arrays;

/**
 * 冒泡排序，每次冒出最小的
 * @author dmf
 *
 */
public class BubbleSort {

	public void bubbleSort(int[] arr){
		if (arr==null || arr.length==0) {
			return;
		}
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-i-1;j++){  //最后一个最大的数会冒到最上面，所以不用比较
				if (arr[j]>arr[j+1]) {
					swap(arr,j,j+1);
				}
			}
		}
	}
	
	/**
	 * 优化版本的冒泡排序，如果在内层循环中没有交换，说明数组已经排好序
	 * @param arr
	 */
	public void bubbleSort_Advance(int[] arr){
		if (arr==null || arr.length==0) {
			return;
		}
		boolean flag = true;
		for(int i=0;i<arr.length-1 && flag;i++){
			flag = false;
			for(int j=0;j<arr.length-i-1;j++){
				if (arr[j]>arr[j+1]) {
					swap(arr,j,j+1);
					flag = true;
				}
			}
		}
	}
	
	private void swap(int[] arr, int j, int i) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
	
	public static void main(String[] args) {
		BubbleSort bubbleSort = new BubbleSort();
		int[] arr={5,2,4,6,3};
		//bubbleSort.bubbleSort(arr);
		bubbleSort.bubbleSort_Advance(arr);
		Arrays.stream(arr).forEach(e->System.out.print(e+" "));
	}
}
