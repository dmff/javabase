package com.vortex.singleton;

/**
 * 单例模式之静态内部类，类只会加载一次，类加载的时候会锁住类对象
 * 不能懒加载
 * @author dmf
 *
 */
public class Singleton4 {
	
	private  Singleton4() {}
	
	static class innerSingleton4{
		private static Singleton4 singleton4 = new Singleton4(); 
	}
	
	public static Singleton4 getInstance(){
		return innerSingleton4.singleton4;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				System.out.println(Singleton4.getInstance());
			}).start();;			
		}
	}
}
