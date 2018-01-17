package improve.annotation2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test2 {

	public static void main(String[] args) throws Exception {
		//1.得到需要注入的属性
		Field field = PersonDao.class.getDeclaredField("person");
		
		//2.得到需要注入属性的类型
		Class<?> clazz = field.getType();
		
		//3.创建对象
		Person person = (Person) clazz.newInstance();
		
		//4.反射出属性上的注解
		InjectPerson inject = field.getAnnotation(InjectPerson.class);
		
		//5.并用注解的信息填充person
		Method[] methods = inject.getClass().getMethods();
		for (Method m : methods) {
			String methodName = m.getName();
			//看person对象上有没有注解与之对应的属性
			try {
				PropertyDescriptor pd = new PropertyDescriptor(methodName,Person.class);
				Method setMethod = pd.getWriteMethod();
				setMethod.invoke(person, m.invoke(inject, null));
			} catch (Exception e) {
				continue;
			}
		}
		
		//6.把person对象整到dao中
		PersonDao dao = new PersonDao();
		field.setAccessible(true);
		field.set(dao, person);
		
		
		System.out.println(dao.getPerson().getAge());
		System.out.println(dao.getPerson().getName());
		
	}
}
