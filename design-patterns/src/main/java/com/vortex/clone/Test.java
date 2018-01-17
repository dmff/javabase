package com.vortex.clone;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws Exception {
		/**
		 * 但是和明显，这两只羊的出生日期肯定是不同的，他们之间
		 * 还是存在联系;如何实现深复制呢？
		 */
		Sheep sheep = new Sheep("多利", new Date());
		System.out.println(sheep);
		
		TimeUnit.SECONDS.sleep(1);
		
		Sheep clone = (Sheep) sheep.clone();
		System.out.println(clone);
	}
}
