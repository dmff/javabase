package improve.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test2 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//反射出方法
		Class clazz = JdbcUtils.class;
		Method method = clazz.getMethod("getConn",  new Class[]{String.class,String.class,String.class});
		
		//通过方法获取注解信息
		DbInfo dbInfo = method.getAnnotation(DbInfo.class);
		String url = dbInfo.url();
		String username = dbInfo.username();
		String password = dbInfo.password();
		
		//把参数信息注入放法中运行
		method.invoke(null, url,username,password);
	}
}
