package improve.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo1 {

	//三种类加载器，父类委托机制
	
	public static void main(String[] args) throws FileNotFoundException {
		//classpath,类加载器
		Demo1 demo1= new Demo1();
		System.out.println(demo1.getClass().getClassLoader());
		
	
		FileInputStream in = new FileInputStream("c:\\1.txt");
		ClassLoader cl = in.getClass().getClassLoader();
		System.out.println(cl);
	}
}
