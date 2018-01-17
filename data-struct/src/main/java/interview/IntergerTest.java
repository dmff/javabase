package interview;

public class IntergerTest {

	public static void main(String[] args) {
		int i = 5;
		int j = 10;
		
		/**
		 * ~为按位取反运算
		 * 正数的原码等于反码，也等于补码
		 * 负数符号位不变，其他位取反，补码在反码基础上加一
		 *  j: 0000 1010
		 * 
		 */
		System.out.println(i + ~j);
	}
}
