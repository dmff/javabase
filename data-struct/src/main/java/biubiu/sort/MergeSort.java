package biubiu.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author dmf
 *
 */
public class MergeSort {

	
	/**
	 * 自顶向下
	 * @param arr
	 * @param left
	 * @param right
	 */
	public void mergeSortUD(int[] arr, int left, int right) {
		if (arr==null || arr.length == 0) {
			return;
		}
		
		if (left>=right) {
			return;
		}
		
		//可以做一个优化，当数组小于15个元素时使用插入排序
		
		int mid = left +(right-left)/2;
		mergeSortUD(arr,left,mid);
		mergeSortUD(arr, mid+1, right);
		//只有前面数组中最大的数比后面数组中最小的数最大，说明需要合并排序
		if (arr[mid]>arr[mid+1]) {
			merge(arr,left,mid,right);			
		}
		
	}
	

	private void merge(int[] arr, int left, int mid, int right) {
		int[] temp = new int[right-left+1];  //临时数组
		int index = 0;
		int i=left,j=mid+1;
		while(i<=mid && j<=right){
			if (arr[i]<arr[j]) {
				temp[index++] = arr[i++];
			}else {
				temp[index++] = arr[j++];
			}
		}
		
		while(i<=mid){
			temp[index++] = arr[i++];
		}
		while(j<=right){
			temp[index++] = arr[j++];
		}
		//将排序好的临时数组里面的数复制回原数组
		for(int k=0;k<temp.length;k++){
			arr[k+left] = temp[k];
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2,5,4,3,6,1};
		MergeSort mergeSort = new MergeSort();
		mergeSort.mergeSortUD(arr,0,arr.length-1);
		Arrays.stream(arr).forEach(e->{System.out.print(e+" ");});
	}
}
