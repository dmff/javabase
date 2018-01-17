package biubiu.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

	public static void main(String[] args) {
		List<Integer> list = Stream.of(Arrays.asList(1,2,3,4,5),Arrays.asList(6,7,8,9))
			.flatMap(Number->Number.stream())  //合并，惰性求值
			.peek(value->System.out.println(value.toString()+" "))  //遍历输出
			.collect(Collectors.toList());     //输出list  
		
		Integer result = list.stream()		//List转化为Stream对象 stream()串行流，parallelStream()并行流
			.parallel()		//将Stream并行化处理
			.filter(value -> value%2!=0)	//过滤奇数
			.map((value)->value*3+1)		  //乘以3，加上1
			.reduce(0,(acc,elem)->acc+elem);	// 累加求和，初始值为0
		
		System.out.println("result:"+result);
	}
}
