package biubiu.find;

/**
 * 基于有序数组和无序数组的二分查找法
 * @author dmf
 *
 */
public class BinaryFind {

	/**
	 * 在有序数组中查找
	 * @param arr	数组
	 * @param temp  查找的数
	 * @return 在数组中的位置，没有返回-1
	 */
	public int find2sort(int arr[],int temp){
		if (arr==null||arr.length==0) {
			return -1;
		}
		int start = 0,end = arr.length-1;
		while(start<=end){
			int mid = start+ (end-start)/2;
			if (arr[mid]>temp) {
				end = mid-1;
			}else if (arr[mid]<temp) {
				start = mid+1;				
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 *	
	    1、给定有序数组A和关键字key，判断A中是否存在key，如果存在则返回下标值，不存在则返回-1。
		2、给定无序数组A和关键字key，判断A中是否存在key，如果存在则返回1，不存在则返回0。
		3、给定无序数组A和下标k，找到A[k]并输出，渐进时间O(n),基于快排的划分函数的二分思想。
	 * @param arr
	 * @param temp
	 * @return 存在返回true，不存在返回false
	 */
	public boolean find2nosort(int arr[],int start,int end,int temp){
		if (start<end) {
			int mid = binartInit(arr,start,end);
			if (arr[mid] == temp) {
				return true;
			}else if (arr[mid]>temp) {
				return find2nosort(arr, start, mid-1, temp);
			}else {
				return find2nosort(arr, mid+1, end, temp);
			}
		}
		if (start==end) {
			return arr[start]==temp; //只有一个元素
		}
		return false;
		
	}
	
	//进行partion
	private int binartInit(int[] arr, int start, int end) {
		int temp = arr[start];
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
		swap(arr, start, j);
		return j;
	}
	
	private void swap(int[] array,int a,int b){
		int temp = 0;
		temp =array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void main(String[] args) {
		BinaryFind binaryFind = new BinaryFind();
		int[] arr = {1,3,5,7,9};
		//int index = binaryFind.find2sort(arr, 3);
		boolean ishas = binaryFind.find2nosort(arr, 0, arr.length-1, 1);
		System.out.println(ishas);
	}
	
}
