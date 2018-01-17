package improve.introspector;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class Demo1 {

	
	//通过内省api操作bean的name属性
	@Test
	public void test1() throws Exception{
		
		Student s = new Student();
		
		PropertyDescriptor pd = new PropertyDescriptor("name",Student.class);
		Method method = pd.getWriteMethod();  //setName()
		method.invoke(s, "flx");
		
		//System.out.println(s.getName());
		method = pd.getReadMethod();   // getName
		String result = (String) method.invoke(s, null);
		System.out.println(result);
		
	}
	
	
	//操作bean的所有属性
	@Test
	public void test2() throws Exception{
		
		BeanInfo info = Introspector.getBeanInfo(Student.class);
		PropertyDescriptor pds[] = info.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds){
			System.out.println(pd.getName());
		}
		
	}
	
	
}
