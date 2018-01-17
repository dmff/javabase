package biubiu.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试string数组的去重，重写hashcode
 * 
 * 1.为什么hashcode是31*属性值？
 * 	  31首先是一个素数，与素数相乘运算后，能降低hashcode碰撞的概率；
 *    31其次是一个特殊的值(32-1)，32的二进制是100000，31的二进制是011111，31*N = N << 5 - N，运算速度会快。
 * 2.为什么在idea中只是需要重写31*1+数组中每一个元素hashcode相加的值
 * 
 * 3.进行数组比较时，都是先要一系列的先决条件的判断，然后在比较每一个元素的值
 * 
 * 4.说一说System.arraycopy(src, srcPos, dest, destPos, length);各个参数的含义
 * 	  从指定源数组,数组副本在指定的位置开始,到目标数组的指定位置
 * 	 src——源数组中；srcPos——源数组中的起始位置。 
     dest——目标数组；destPos——在目标数据起始位置。 
	 length——数组元素的数量被复制。	
 * @author dmf
 *
 */
public class TestHashCode {

	private  String[] arr;

	public TestHashCode(String[] arr) {
		super();
		this.arr = arr;
	}

	@Override
	public int hashCode() {
		//final int prime = 31;
		//int result = 1;
		//result = prime * result + Arrays.hashCode(arr);
		//return result;
		return Arrays.hashCode(arr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestHashCode other = (TestHashCode) obj;
		if (!Arrays.equals(arr, other.arr))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "TestHashCode [arr=" + Arrays.toString(arr) + "]";
	}

	public static void main(String[] args) {
		TestHashCode hashCode1 = new TestHashCode(new String[]{"a","b"});
		TestHashCode hashCode2 = new TestHashCode(new String[]{"a","c"});
		TestHashCode hashCode3 = new TestHashCode(new String[]{"a","b"});
		
		Set<Object> set = new HashSet<>();
		set.add(hashCode1);
		set.add(hashCode2);
		set.add(hashCode3);
		
		for (Object object : set) {
			System.out.println(object);
		}
		
		
	}
}
