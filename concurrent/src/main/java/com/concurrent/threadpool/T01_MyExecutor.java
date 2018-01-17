package com.concurrent.threadpool;

import java.util.concurrent.Executor;

/**
 * 认识Executor执行器：执行线程的run方法
 * @author dmf
 *
 */
public class T01_MyExecutor implements Executor{

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();
	}
}
