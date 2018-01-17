package improve.generic;

import java.util.ArrayList;
import java.util.Collection;

public class Demo4 {

	public static void main(String[] args) {
		print(new ArrayList<Integer>());
		print(new ArrayList<String>());
	}
	
	//就不能对类型进行具体的操作
	public static void print(Collection<?> c){
		for (Object object : c) {
			System.out.println(object);
		}
	}
	
	//当使用?通配符时，就不能再调用与类型相关的方法,只能调与类型无关的方法
	public static void save(Collection<?> c){
		//c.add("1");
		c.size();
	}
}
