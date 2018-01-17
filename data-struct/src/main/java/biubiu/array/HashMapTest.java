package biubiu.array;

public class HashMapTest {

	public static void main(String[] args) {
		//表示的值为NAN(not a number)异常，返回true，否则返回false
		System.out.println(Float.isNaN(0.0f/0.0f));
		
		Float float1 = new Float(-1.0/0.0);
		Float float2 = new Float(0.0/0.0);
		
		System.out.println(float1 + " = " + float1.isNaN());
		System.out.println(float2 + " = " + float2.isNaN());

		
		//ReentrantReadWriteLock
		
	}
}
