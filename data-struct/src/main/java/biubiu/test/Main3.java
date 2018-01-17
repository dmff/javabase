package biubiu.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();  //物品数量
			int V = sc.nextInt();  //总价值
			int[] a = new int[n];  //物品数组
			for(int i=0;i<n;i++){
				a[i] = sc.nextInt();
			}
			//排序，取出最小值
			Arrays.sort(a);
			int count = 0;  //拿到物品数量
			int money = 0;  //拿到物品总价值
			int i=0;
			
			//相等时会继续往下运行，这样count++
			while(money<=V){
				money+=a[i];
				
				if (i<n-1) {
					i++;
				}
				
				if (count<=n) {
					count++;
				}else {
					break;
				}
			}
			System.out.println(count-1);
		}
	}
}
