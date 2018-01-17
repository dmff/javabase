package com.concurrent.tickeseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
 *   
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue提高并发性
 * @author dmf
 *
 */
public class TicketSeller4 {

	//static List<String> tickets = new ArrayList<>();
	//static Vector<String> tickets = new Vector<>();
	static Queue<String> tickets =  new ConcurrentLinkedQueue<>();
	static{
		for(int i=0;i<1000;i++)
			tickets.add("票编号"+i);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(() -> {
				// 使用synchronized保证操作之间的原子性
				// synchronized (tickets)
				while(true){
					String s = tickets.poll();
					if(s == null) break;
					else System.out.println("销售了--" + s);
				}
			}, "窗口" + i).start();
		}
	}
}
