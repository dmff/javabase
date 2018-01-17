package improve.generic;

public class Demo1<T> {

	/**
	 * 泛型的声明和使用
	 * 
	 */

	public T  aa(T t){
		return null;
	}
	
	public void bb(T t){}
	
	//类上声明的泛型只对非静态成员有效
	public static <T> void cc(T t){}
}
