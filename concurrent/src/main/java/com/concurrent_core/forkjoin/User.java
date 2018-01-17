package com.concurrent_core.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class User {

	private String username;

	
	
	public User() {
		super();
	}

	public User(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	static class MyRunnable implements Runnable{

		private User user;
	
		public MyRunnable(User user) {
			super();
			this.user = user;
		}
		
		@Override
		public void run() {
			try {
				user.setUsername("设置usernmae");
				Thread.sleep(3000);
				System.out.println("已经设置好了username");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	//测试submit(callable,T)
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		User user = new User();
		Runnable task = new MyRunnable(user);
		ForkJoinTask<User> future = forkJoinPool.submit(task , user);
		
		System.out.println(future.get().getUsername());
		
		//Thread.sleep(5000);
		//取不到值，因为是异步执行
		
	/*	while(true){
			if (user.getUsername()!=null) {
				System.out.println("username ="+user.getUsername());
				break;
			}
		}*/
	}
}
