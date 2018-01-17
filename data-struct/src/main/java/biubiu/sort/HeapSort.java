package biubiu.sort;

import java.util.Arrays;

/**
 * 堆排序是一种树形选择排序，是对直接选择排序的有效改进。
 * 	 a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
　　 b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
　　 c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 * @author dmf
 *
 */
public class HeapSort {

	/**
	 * 最大堆调整算法，从0开始调整 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2) start --
	 * 被上调节点的起始位置(一般为数组中最后一个元素的索引)
	 * 
	 * @param start
	 */

	public void sort(int[] arr) {
		// 1.构建最大堆，从最后一个非叶子节点开始调整
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		// 2.调整堆结构+交换堆顶元素与末尾元素
		for (int j = arr.length - 1; j > 0; j--) {
			swap(arr, 0, j); // 将堆顶元素和末尾元素交换,使得末尾元素最大
			adjustHeap(arr, 0, j);
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];// 先取出当前元素i
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {// 从i结点的左子结点开始，也就是2i+1处开始
			if (k + 1 < length && arr[k] < arr[k + 1]) {// 如果左子结点小于右子结点，k指向右子结点
				k++;
			}
			if (arr[k] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}
		arr[i] = temp;// 将temp值放到最终的位置
	}
	
	public static void main(String[] args) {
		int []arr = {9,8,7,6,5,4,3,2,1};
        new HeapSort().sort(arr);;
        System.out.println(Arrays.toString(arr));
	}
}
