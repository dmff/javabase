package improve.base;

import java.util.Arrays;

import static java.lang.System.out;

public class StaiticImoport {

	public static void main(String[] args) {
		System.out.println("hello!");
		//静态导入
		out.print("hello!");
		int i = Arrays.binarySearch(new String[]{"1"}, "1");
		System.out.println(i);
	
	}
}
