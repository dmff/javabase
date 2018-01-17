package improve.reflect;

import java.lang.reflect.Field;

public class TestFiled {

	
	
	//public String name;
	public void test1() throws Exception{
		
		Person p = new Person();
		
		Class clazz = Class.forName("com.day03.reflect.Person");
		
		Field f = clazz.getField("name");  //name
		
		f.set(p, "flx");
		
		System.out.println(p.getName());
		
	}
	
	//public String name;
	public void test2() throws Exception{
		
		Person p = new Person();
		p.setName("xxx");
		
		
		Class clazz = Class.forName("com.day03.reflect.Person");
		Field f = clazz.getField("name");  //name
		String result = (String) f.get(p);
		System.out.println(result);
		
	}
	
	//public final String password = "";
	public void test3() throws Exception{
		
		Person p = new Person();
		
		Class clazz = Class.forName("com.day03.reflect.Person");
		Field f = clazz.getField("password");  //name
		String result = (String)f.get(p);
		System.out.println(result);
		
	}
	
	
	//private int age;
	public void test4() throws Exception{
		
		Person p = new Person();
		
		Class clazz = Class.forName("com.day03.reflect.Person");
		Field f = clazz.getDeclaredField("age");
		f.setAccessible(true);
		
		f.set(p, 123);
		
		int result = (Integer) f.get(p);
		System.out.println(result);
		
		
		
	}
	
	

}
