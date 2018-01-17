package biubiu.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author dmf
 *
 */
public class SelectSort {

	public void selectSort(int[] arr){
		if (arr==null || arr.length==0) {
			return;
		}
		//只需要n-1趟,最后一个数自然是最大的
		for(int i=0;i<arr.length-1;i++){
			int minIndex = i;
			//找到最小元素的索引，进行交换
			for(int j=i+1;j<arr.length;j++){
				if (arr[j]<arr[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(arr, i, minIndex);
			}
		}
	}
	
	private void swap(int[] arr, int j, int i) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
	
	public static void main(String[] args) {
		SelectSort selectSort = new SelectSort();
		int[] arr={5,2,4,6,3};
		selectSort.selectSort(arr);
		Arrays.stream(arr).forEach(e->System.out.print(e+" "));
	}
}
