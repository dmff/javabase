package biubiu.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 * 			 这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
 * @author dmf
 *
 */
public class RadixSort {
	
	public static void main(String[] args) {
		 int[] arr={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,101,56,17,18,23,34,15,35,25,53,51};
		 RadixSort radixSort = new RadixSort();
		 radixSort.radixSort(arr);
		 Arrays.stream(arr).forEach(e->System.out.print(e+" "));
	}

	public void radixSort(int[] arr){
		//1.首先获取位数，确定趟数
		int max = arr[0];
		for(int i=1;i<arr.length;i++){
			if (arr[i]>max) {
				max = arr[i];
			}
		}
		
		int bit = 0;
		while(max>0){
			max /=10;
			bit++;
		}
		
		//2.创建10个队列用来存储1-10的数
		List<List<Integer>> queues = new ArrayList<>();
		for(int i=0;i<10;i++){
			List<Integer> queue = new ArrayList<>();
			queues.add(queue);
		}
		
		//3.给元素按照bit为进行排序
		for(int i=0;i<bit;i++){
			for(int j=0;j<arr.length;j++){  //依次获取个十百位的数
				//首先%10,除以1，获取个数；然后%100，除以10，获取十位
				int x = arr[j] % (int)Math.pow(10, i+1) / (int)Math.pow(10, i);
				List<Integer> queue2 = queues.get(x);
				queue2.add(arr[j]);
				queues.set(x,queue2);
			}
			
			//一轮排序之后需要给数组重新排序
			int index = 0;  //元素计数器
			for(int k=0;k<10;k++){
				while(!queues.get(k).isEmpty()){
					List<Integer> queue3 = queues.get(k);
					arr[index++] = queue3.remove(0);
				}
			}
		}
		
	}
}
