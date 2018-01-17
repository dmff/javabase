package interview;

public class RMBConver {

	private static final char[] data = {'零','壹','贰','三','四','伍','陆','七','八','玖'};
	private static final char[] units = {'元','十','百','千','万','十','百','千','亿'};
	
	public static String convert(int money){
		StringBuilder sb = new StringBuilder();
		int unit = 0;
		while(money!=0){
			sb.insert(0, units[unit++]);
			int number = money%10;
			sb.insert(0, data[number]);
			
			money = money/10;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(convert(323456));
	}
}
