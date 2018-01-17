package com.vortex.singleton;

/**
 * 单例模式之饿汉模式--直接初始化
 * 不能懒加载
 * @author dmf
 *
 */
public class Singleton1 {
	
	private static Singleton1 singleton1 = new Singleton1();
	
	private  Singleton1() {}
	
	public static Singleton1 getInstance(){
		return singleton1;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				System.out.println(Singleton1.getInstance());
			}).start();;			
		}
	}
}
