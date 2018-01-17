package improve.reflect;

import java.lang.reflect.Constructor;

public class TestConstruct {

	/**
	 * 利用Constructor创建对象
	 * @throws Exception 
	 */
	
	//反射类的无参构造方法
	public void test1() throws Exception{
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
		Constructor<?> c = clazz.getConstructor(null);
		Object obj =c.newInstance(null);
		System.out.println(obj.toString());
	}
	
	//反射有参的构造方法:public Person(String name)

	public void test2() throws Exception{
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
		Constructor<?> c = clazz.getConstructor(String.class);
		Person p = (Person) c.newInstance("fix");
		System.out.println(p);
	}
	
	//反射类私有的、有参的构造方法：private Person(int name)

	public void test3() throws Exception{
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
		//私有的方法必须用带声明的才可以获取
		Constructor<?> c = clazz.getDeclaredConstructor(int.class); 
		
		c.setAccessible(true);    //暴力反射，这样可以对私有的构造方法创建对象
		Person p = (Person) c.newInstance(1);
		System.out.println(p);
	}
	
}
