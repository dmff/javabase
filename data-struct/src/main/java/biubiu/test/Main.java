package biubiu.test;

import java.util.Scanner;

/**
 * 类似菲比那切数列的问题
 * @author dmf
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int d = sc.nextInt();
			System.out.println(get(d));
		}
	}

	/*
	 * 由题意可知：第n次跌是发生在第x天，x与n的关系为： x = n^2/2 + 3n/2 + 1 n>=1; 也可把公式转化为： n =
	 * （根号）(2x+0.25) - 1.5 x>=1;
	 */
	private static int get(int date) {
		int price , priceDownNum;
		priceDownNum = (int) (Math.sqrt(2*date + 0.25) - 1.5);
		price = date - 2*priceDownNum;
		return price;
	}
}
