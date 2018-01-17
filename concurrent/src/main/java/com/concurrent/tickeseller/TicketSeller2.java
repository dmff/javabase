package com.concurrent.tickeseller;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有n张或者票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 
 * 分析下面程序会产生哪些问题？
 * 重复售票？超量售票？
 *    当有一个线程已经执行到买票的时候暂停，这时候票没有减少
 *    另外一个人也去买票就有可能买了那个暂停线程的票
 * 
 * 使用Vector或者Collections.synchronizedXX?
 * 	  不行，因为vectory只保证了size和remove这两个局部方法的原子性
 *   并不能保证在他们业务之间也是原子性的
 *   会造成越界买票
 * @author dmf
 *
 */
public class TicketSeller2 {

	//static List<String> tickets = new ArrayList<>();
	  static Vector<String> tickets = new Vector<>();
	static{
		for(int i=0;i<1000;i++)
			tickets.add("票编号"+i);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(tickets.size()>0){
					
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
				}
			}, "窗口"+i).start();
		}
	}
}
