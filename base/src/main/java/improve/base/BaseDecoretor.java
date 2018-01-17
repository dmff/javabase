package improve.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseDecoretor {

	/*
	 * 基本数据类型的包装类
	   byte short int long float double boolean char
	   Byte Short Integer  Long................  Character
	 *
	 */
	public static void main(String[] args) {
		Integer i=1;  //装箱   Integer i = new Integer(1);
		int j=i;  //拆箱   j=i.intValue();
		System.out.println(j);
		
		//典型应用
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			int k = it.next();
			System.out.println(k);
		}
	}
}
