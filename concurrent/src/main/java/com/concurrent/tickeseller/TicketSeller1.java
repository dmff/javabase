package com.concurrent.tickeseller;

import java.util.ArrayList;
import java.util.List;

/**
 * 有n张或者票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 
 * 分析下面程序会产生哪些问题？
 * 重复售票？超量售票？
 * 
 * 当有一个线程已经执行到买票的时候暂停，这时候票没有减少
 * 另外一个人也去买票就有可能买了那个暂停线程的票
 * @author dmf
 *
 */
public class TicketSeller1 {

	static List<String> tickets = new ArrayList<>();
	
	static{
		for(int i=0;i<100;i++)
			tickets.add("票编号"+i);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(tickets.size()>0){
					System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
				}
			}, "窗口"+i).start();
		}
	}
}
