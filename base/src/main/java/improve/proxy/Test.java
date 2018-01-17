package improve.proxy;

public class Test {

	public static void main(String[] args) {
		LiyuechunProxy proxy = new LiyuechunProxy();
		Person p = proxy.getProxy();
		
		String value = p.dance("艳舞");
		System.out.println(value);
	}
}
