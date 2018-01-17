package improve.proxy;

public class Liyuechun implements Person{

	@Override
	public String sing(String name) {
		System.out.println("春哥唱"+name+"歌！！");
		return "飞吻！！";
	}

	@Override
	public String dance(String name) {
		System.out.println("春哥跳"+name+"舞！！");
		return "多谢多谢老板！！";
	 }
}

