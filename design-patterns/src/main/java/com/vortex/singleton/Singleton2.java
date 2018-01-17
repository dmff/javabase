package com.vortex.singleton;

/**
 * 单例模式之懒汉模式:锁住类对象,注重效率很低
 * @author dmf
 *
 */
public class Singleton2 {
	
	private static Singleton2 singleton2;
	
	private  Singleton2() {}
	
	public static synchronized Singleton2 getInstance(){
		if (null !=singleton2) {
			return singleton2;
		}
		return singleton2 = new Singleton2();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				System.out.println(Singleton2.getInstance());
			}).start();;			
		}
	}
}
