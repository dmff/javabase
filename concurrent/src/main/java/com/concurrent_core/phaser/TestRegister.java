package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

public class TestRegister {
	
	//测试单个注册
	public static void testRegister(){
		Phaser phaser = new Phaser(5);
		System.out.println(phaser.getRegisteredParties());
		
		phaser.register();
		System.out.println(phaser.getRegisteredParties());
		
		phaser.register();
		System.out.println(phaser.getRegisteredParties());
	}
	
	//测试批量注册
	public static void testBulkRegister(){
		Phaser phaser = new Phaser(5);
		
		phaser.bulkRegister(5);
		System.out.println(phaser.getRegisteredParties());
		
		phaser.bulkRegister(5);
		System.out.println(phaser.getRegisteredParties());
	}
	
	public static void main(String[] args) {
		//testRegister();
		testBulkRegister();
	}
}
