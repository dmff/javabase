package com.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author dmf
 *
 */
public class T13_ThreadPoolExecutor {
	public static void main(String[] args) {
		ExecutorService service = new ThreadPoolExecutor(5, 
				5, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
	}
}
