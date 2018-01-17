package improve.reflect;

import java.lang.reflect.Method;

public class TestMethod {

	//反射方法
	
	//反射：public void eat()

	public void test1() throws Exception{
		//Person p = new Person();
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
		
		//第一个参数为方法名，第二个为参数类型,没有用null表示
		Method method = clazz.getMethod("eat", null);
		
		//第一个参数为操作的对象，第二个为参数
		method.invoke(clazz.newInstance(), null);
	}
	
	//反射：run(String address)

	public void test2() throws Exception{
		
		Person p = new Person();
		
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
		
		Method method = clazz.getMethod("run", String.class);
		method.invoke(p, "北京");
	}
	
	//反射：public void run(String address,int num[],String ss[])

	public void test3() throws Exception{ //出错？？？？
		
		Person p = new Person();  //
		
		Class clazz = Class.forName("com.day03.reflect.Person");  //完整名称
		Method method = clazz.getMethod("run", String.class,int[].class,String[].class);
		method.invoke(p, "上海",new int[]{1,2},new String[]{"1","2"});
	}
	
	//反射：public String test(String str) (带返回值)

	public void test4() throws Exception{
        Person p = new Person();  //
		Class clazz = Class.forName("com.day03.reflect.Person");  //完整名称
		Method method = clazz.getMethod("test", String.class);
		String result = (String) method.invoke(p, "xxxx");
		System.out.println(result);
	}
	
	//反射：private String test2(String str)  私有方法

	public void test5() throws Exception{
		Person p = new Person();  //
		Class clazz = Class.forName("com.day03.reflect.Person");  //完整名称
		Method method = clazz.getDeclaredMethod("test2", String.class);
		method.setAccessible(true); //获得方法之后暴力反射，就能够使用私有方法
		String result = (String) method.invoke(p, "xxxx");
		System.out.println(result);	
	}
	
	//反射：public static String test3(String str){ 静态方法

	public void test6() throws Exception{  //反射静态方法不需要指定对象，他是直接通过类去调用
		Class clazz = Class.forName("com.day03.reflect.Person");  //完整名称
		Method method = clazz.getMethod("test3", String.class);
		String result = (String) method.invoke(null, "aaa");
		System.out.println(result);	
	}
	
	//反射：public static void main(String[] args) {  反射main方法  
	//通过反射调用带数组的方法，要注意处理

	public void test7() throws Exception{
		Class clazz = Class.forName("com.day03.reflect.Person");  //完整名称
		Method method = clazz.getMethod("main", String[].class);
		
		//当为一个参数的 时候要注意，他会自动的数组当成可变参数处理
		method.invoke(null, (Object)new String[]{"1","2"});  
		//method.invoke(null, new Object[]{new String[]{"1","2"}});//main(String args[]) //解决的方法
		
		//public Object invoke(Object obj, Object... args)  //jdk1.5
		//public Object invoke(Object obj, Object[] args)  //jdk1.4
		
		//public void run(String name,String password)  
		//method.invoke(p,new Object[]{"flx,123"})//1.4
		//method.invoke(p,"flx","123")
	}
	
}
