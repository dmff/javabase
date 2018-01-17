package com.concurrent_core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {

	private UserInfo userinfo;

	public MyRunnable(UserInfo userInfo) {
		super();
		this.userinfo = userInfo;
	}

	@Override
	public void run() {
		userinfo.setUsername("username");
		userinfo.setPassword("password");
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		UserInfo userinfo = new UserInfo();
		MyRunnable runnable = new MyRunnable(userinfo);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
		Future<UserInfo> future = executor.submit(runnable, userinfo);
		
		System.out.println("main A "+System.currentTimeMillis());
		UserInfo userInfo = future.get();
		
		System.out.println(userinfo.getUsername()+":"+userInfo.getPassword());
		
		System.out.println("main B "+System.currentTimeMillis());
	}

}
