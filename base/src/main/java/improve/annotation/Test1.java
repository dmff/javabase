package improve.annotation;

public class Test1 {

	@Deprecated
	public void doxx(){}
	
	@MyAnnotation(name="老邓",age=37,
			gender=Gender.MALE,clazz=String.class,
			my2=@MyAnnotation2(name="xxx"),arr={1,2,3},gs={Gender.FEMALE,Gender.MALE})
	public void doaa(){}
	
	//名称为value的属性可以直接赋值，前提只有这一个属性
	@MyAnnotation3({"1","2"})
	public void dobb(){}
}
