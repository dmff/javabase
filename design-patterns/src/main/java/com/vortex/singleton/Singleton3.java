package com.vortex.singleton;

/**
 * 单例模式之懒汉模式:锁住类对象,注重效率很低
 * @author dmf
 *
 */
public class Singleton3 {
	
	private static volatile Singleton3 singleton3;
	
	private  Singleton3() {}
	
	public static  Singleton3 getInstance(){
		if (null ==singleton3) {
			synchronized (Singleton3.class) {
				if (null ==singleton3) {
					return singleton3 = new Singleton3();
				}
			}	
		}
		return singleton3;
		
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			new Thread(()->{
				System.out.println(Singleton3.getInstance());
			}).start();;			
		}
	}
}
