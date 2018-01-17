package biubiu.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序：
 * 		1.单路快排
 			从左往右排序，找到标志位，左边的数比标志位小，右边的数比标志位大
 			然后在依次把标志位两边的数进行快排
 * 		2.双路快排
 * 			从两边找排序，增加排序的效率
 * 		3.三路快排
 * 			添加一个等于标志的索引，解决大量重复元素使用
 * @author dmf
 *
 */
public class QuickSort {

	public static void quick_sort(int[] arr,int start,int end){
		if (start>end) {
			return;
		}
//		int partion = partion(arr,start,end);
		int partion = partion2(arr,start,end);
		quick_sort(arr, start, partion-1);
		quick_sort(arr, partion+1, end);
	}

	/**
	 * 单路快排，找一个标志位，使得左边的元素都比当前元素小，右边元素都比当前元素大
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int partion(int[] arr, int start, int end) {
		int random = (int) (Math.random()*(end-start+1)+start);
		swap(arr, start, random);
		int temp = arr[start];
		int j=start;  //记录需要交换元素的位置
		for(int i=start+1;i<=end;i++){
			//比标志位小的元素不动，使用j表示比标志位小的元素的位置
			if (arr[i]<temp) {
				j++;
				swap(arr, i,j );
			}
		}
		swap(arr, start, j);
		return j;
	}
	
	/**
	 * 双路快排,加快交换的速度
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	private static int partion2(int[] arr, int start, int end) {
		int random = (int) (Math.random()*(end-start+1)+start);
		swap(arr, start, random);
		int temp = arr[start];
		//left和right记录索引的位置
		int i = start+1,j=end;
		while(true){
			while(i<=end && arr[i]<temp)  //找到比标志位大的元素
				i++;
			while(j>start+1 && arr[j]>temp)  //找到比标志位小的元素
				j--;
			if (i>j) {
				break;
			}
			swap(arr, i, j);
			i++;
			j--;
		}
		swap(arr, start, j); //最后出现left=right的情况
		return j;
	}
	
	/**
	 * 三路快排:一般使用双路快排，三路快排是大量重复元素的时候效率比较高
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static void partion3(int[] arr, int start, int end) {
		if (start>end) {
			return;
		}
		int random = (int) (Math.random()*(end-start+1)+start);
		swap(arr, start, random);
		int temp = arr[start];
		int lt=start; //arr[start+1,...,lt]<temp
		int gt = end+1; //arr[gt,...,end]>temp
		int i = start+1; //arr[lt+1,...,gt-1]=temp
		while(i<gt){
			if (arr[i]<temp) {
				swap(arr, i, lt+1);
				i++;
				lt++;
			}else if (arr[i]>temp) {
				swap(arr, i, gt-1);
				gt--;
			}else {
				i++;
			}
		}
		swap(arr, start, lt);
		partion3(arr, start, lt-1);
		partion3(arr, gt, end);
	}

	private static void swap(int[] arr, int left, int right) {
		if (left == right) {
			return;
		}
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
	public static int[] getArr(int n){
		int[] arr = new int[n];
		Random random = new Random();
		for(int i=0;i<n;i++){
			arr[i] = random.nextInt(n);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		//long start = System.currentTimeMillis();
		int[] arr = getArr(50);
		
		//quick_sort(arr, 0, arr.length-1);
		partion3(arr, 0, arr.length-1);
		Arrays.stream(arr).forEach(e->System.out.print(e+" "));
		//System.out.println(System.currentTimeMillis()-start);
	}
	
}
