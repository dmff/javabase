package interview;

import java.util.Scanner;

public class Sring_Intreview {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int len = getSubString(scanner.next(), scanner.next());
			System.out.println(len);
		}
	}
	/**
	 * 获取两个字符串子串的最大长度
	 * GCCCTAGCCAGDE、GCGCCAGTGDE
	 * @return
	 */
	public static int getSubString(String str1,String str2){
		if (str1==null || str1.equals("") || str2==null || str1.equals("")) {
			return 0;
		}
		int len1 = str1.length();
		int len2 = str2.length();
		
		String min = len1<=len2 ?str1:str2;
		String max = len1>len2 ?str1:str2;
		String target = null;
		//直接找到最大的去匹配
		for(int i=min.length();i>=1;i--){
			for(int j=0;j<=min.length()-i;j++){
				target = min.substring(j, j+i);
				for(int k=0;k<=max.length()-i;k++){
					if (max.substring(k, k+i).equals(target)) {
						return i;
					}
				}
			}
		}
		return 0;
	}
}
