package biubiu.array;


public class Jump {
	/**
	 * 青蛙跳台阶问题:只能从n-1和n-2台阶跳
	 */
	public int jumpfloor(int number){
		if (number<=0) {
			return 0;
		}
		else if (number==1) {
			return 1;
		}
		else if (number==2) {
			return 2;
		}
		else {
			return jumpfloor(number-1)+jumpfloor(number-2);
		}
	}
	
	public int jumpfloor2(int number){
		int t1=1,t2=2,total=0;
		if (number==1||number==2) {
			return number;
		}
		
		//3,4,5台阶依次统计
		for(int i=3;i<=number;i++){
			total = t1+t2;
			t1 = t2;
			t2 = total;
		}
		return total;
	}
}
