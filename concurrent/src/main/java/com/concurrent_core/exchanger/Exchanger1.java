package com.concurrent_core.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Exchanger1 {

	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//没有超时，将会一直阻塞
					//System.out.println("取得线程A的值"+exchanger.exchange("我是中国人a"));
					System.out.println("取得线程A的值"+exchanger.exchange("我是中国人a",2,TimeUnit.SECONDS));
				} catch (InterruptedException | TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("a end!!!");
			}
		},"threadA");
		threadA.start();
		System.out.println("main end!!");
	}
}
