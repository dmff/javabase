package com.concurrent_core.semaphore;

public class MyThread extends Thread {

	//private Service2 service;
	private Service service;
	
	public MyThread(Service service) {
		super();
		this.service = service;
	}
	
	@Override
	public void run() {
		service.service();
	}

}
