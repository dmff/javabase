package biubiu.sort;

import java.util.Arrays;

/**
 * 插入排序分为直接插入排序和希尔排序
 * 
 * @author dmf
 *
 */
public class InsertSort {

	/**
	 * 直接插入排序--使用交换
	 * 
	 * @param arr
	 */
	public void insertSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		for (int i = 1; i < arr.length; i++) {

			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				} else {
					// 每次把后面的数往前面插，如果没有交换，说明插入的当前数是有序的
					break;
				}
			}

			/*
			 * for(int j=i;j>0 && arr[j]<arr[j-1];j--) swap(arr,j,j-1);
			 */
		}
	}

	/**
	 * 直接插入排序采用赋值--提高效率
	 * 
	 * @param arr
	 */
	public void insertSort_advance(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i]; // 保存的是需要插入的数
			int j; // 保存需要插入元素的位置
			// 每次把后面的数往前插， 6 8 2--> 2 6 8
			for (j = i; j > 0 && arr[j - 1] > temp; j--) {
				// 最后是 6 8，j记录了2需要插入的位置
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	/**
	 * 希尔排序(最小增量排序):
	 * Shell首先将间隔设定为n/2，然后跳跃进行插入排序，再来将间隔n/4，跳跃进行排序动作，再来间隔设定为n/8、n/16， 直到间隔为1之后的最
	 * 后一次排序终止，由于上一次的排序动作都会将固定间隔内的元素排序好，所以当间隔越来越小时 某些元素位于正确位置的机率越高，因此最后几次的排序动作将
	 * 可以大幅减低。
	 * 
	 * @param arr
	 */
	public void shellSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素，逐个对其所在组进行直接插入排序操作
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				while (j - gap >= 0 && temp < arr[j - gap]) {
					arr[j] = arr[j - gap];
					j -=gap;
				}
				arr[j] = temp;
			}
		}

	}

	/**
	 * 希尔排序
	 * 
	 * @param arr
	 */
	public void shellSort2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		int h = 1;
		int length = arr.length;
		while(h<length/3){
			h = h*3+1;
			// 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
		}
		
		while(h>=1){
			for(int i=h;i<arr.length;i++){
				int temp = arr[i];
				int j;
				for(j=i;j>=h && temp<arr[j-h];j-=h){  //对分组内的数进行排序
					arr[j] = arr[j-h];
				}
				arr[j] = temp;
			}
			h/=3;
		}
	}

	private void swap(int[] arr, int j, int i) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	public static void main(String[] args) {
		InsertSort insertSort = new InsertSort();
		int[] arr = { 1,4,2,7,9,8,3,6};
		 //insertSort.insertSort(arr);
		// insertSort.insertSort_advance(arr);
	     //insertSort.shellSort(arr);
		insertSort.shellSort2(arr);
		Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
	}
}
