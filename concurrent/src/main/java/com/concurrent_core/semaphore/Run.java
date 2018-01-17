package com.concurrent_core.semaphore;

public class Run {

	public static void main(String[] args) {
		Service service = new Service();
		//Service2 service = new Service2();
		MyThread[] threads = new MyThread[12];
		for(int i=0;i<threads.length;i++){
			threads[i] = new MyThread(service);
			threads[i].start();
		}
	}
}
