package improve.base;

import java.util.Arrays;
import java.util.List;

public class ChangedArgs {

	public static void main(String[] args) {
		sum(1,2,3,4,5);
		//如果一个方法接收可变参数，向参数传入数组也一样
		Integer[] arr = {1,2,3,4,5};  //这里必须用一个包装对象
		sum(arr);
		
		@SuppressWarnings("rawtypes")
		List list= Arrays.asList(1,2,3);  //jdk典型的可变参数的例子
		System.out.println(list);
		
		Integer arr2[] = {1,2,3,4,5};
		list=Arrays.asList(arr2);
		System.out.println(list);
		
		//注意问题
		int[] a = {1,2,3};
		list = Arrays.asList(a);  //基本数据类型传进去的时候会当成一个对象处理
		System.out.println(list);
		
		sum2(1, 2,3,4,5);
	}
	
	public static void sum(Integer... args){
		//可变参数在编程时当成数组即可
		int sum=0;
		for (Integer integer : args) {
			sum += integer;
		}
		System.out.println(sum);
	}
	
	public static void sum2(Integer num,Integer...args){
		//可变参数一般只有一个，且是最后一个参数
		int sum=0;
		for (Integer i : args) {
			sum += i;
		}
		System.out.println(sum);
	}
}
