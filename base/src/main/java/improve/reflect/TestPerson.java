package improve.reflect;

public class TestPerson {

	public static void main(String[] args) throws Exception {
		//获取字节码的三种方式
		//1.
		Class<?> clazz = Class.forName("com.day03.reflect.Person");
	   
		//2.
		Class<?> clazz2 = Person.class;
		System.out.println(clazz==clazz2);
		
		//3.
		Class<? extends Person> class3 = new Person().getClass();
		System.out.println(class3.getName());
		
		//通过类类型创建对象
		Person p  = (Person) clazz.newInstance();
		p.eat();
	}
}
