package biubiu.array;

public class Sum {

	/**
	 * 不适用乘除和逻辑判断求1+2+3+..+n
	 */
	public int sum(int n){
		int sum = n;
		boolean ans = true;
		while(ans){
			ans = (n>0)&((sum+=sum(n-1))>0);
		}
		return sum;
	}
	
	// 不用+、-、*、/求和
	public int sum(int num2,int num1){

		if (num2 == 0) return num1;
		return sum(num1^num2, (num1&num2)<<1 );
	}
	
	public static void main(String[] args) {
		Sum sub = new Sum();
		System.out.println(sub.sum(5));
	}
	
	
}
