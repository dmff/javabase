package improve.annotation2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test1 {

	//注入方法信息
	public static void main(String[] args) throws Exception {
		//1.得到要注入的属性,内省
		PropertyDescriptor pd= new PropertyDescriptor("person",PersonDao.class);
		
		//2.得到注入属性需要的类型
		Class<?> clazz = pd.getPropertyType();
		
		//3.创建属性需要的对象
		Object person = clazz.newInstance();
		
		//4.得到属性的写方法
		Method setPerson = pd.getWriteMethod();
		
		//5.反射出方法上的注解
		InjectPerson inject = setPerson.getAnnotation(InjectPerson.class);
		
		//6.得到注解上的信息，填充person对象
		Method[] methods = inject.getClass().getMethods();
		for(Method me:methods){
			String methodName = me.getName();  //得到注解方法名，属性名
			try{
				Field field = Person.class.getDeclaredField(methodName);
				Object value = me.invoke(inject, null);
				field.setAccessible(true);
				field.set(person, value);
			}catch (Exception e) {
				continue;
			}
		}
		
		//7.把填充了数据的person通过setPerson方法整到personDao对象上
		PersonDao dao = new PersonDao();
		setPerson.invoke(dao, person);
		
		System.out.println(dao.getPerson().getAge());
	}
}
