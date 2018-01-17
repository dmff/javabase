package improve.annotation;

public @interface MyAnnotation {

	//注解可以使用如下类型注解包含的信息
	
	//基本数据类型
	String name();
	String password() default "123";
	double age() default 12;
	
	//枚举
	Gender gender() default Gender.FEMALE;
	Class<?> clazz();
	
	//注解
	MyAnnotation2 my2();
	
	//一维数组
	int[] arr() default {1,2,3};
	
	Gender[] gs();
}
