package com.vortex.component;

import java.util.ArrayList;
import java.util.List;

/**
 * 加盟店的组合模式，在总店消费的话，下面的分店的积分也会累加
 * @author dmf
 *
 */
public class PayDemo {
	
	public abstract class Market{
		String name;
		public abstract void add(Market market);
		public abstract void remove(Market market);
		public abstract void payByCard();
	}
	
	//分店
	public class MarketBranch extends Market{
		List<Market> lists = new ArrayList<Market>();

		public MarketBranch(String name) {
			this.name = name;
		}
		
		@Override
		public void add(Market market) {
			lists.add(market);
		}

		@Override
		public void remove(Market market) {
			lists.remove(market);
		}

		@Override
		public void payByCard() {
			//在总店消费，分店下面也会增加积分
			
			System.out.println(name+"消费，积分已累加入该会员卡...");
			for (Market market : lists) {
				market.payByCard();
			}
		}
	}
	
	//加盟店,没有下一级;在分店消费的话直接加入会员卡中
	public class MarketJoin extends Market{

		public MarketJoin(String name) {
			this.name = name;
		}
		
		@Override
		public void add(Market market) {}

		@Override
		public void remove(Market market) {}

		@Override
		public void payByCard() {
			System.out.println(name +"积分已累加入该会员卡...");
		}
	}
	
	public static void main(String[] args) {
		PayDemo demo = new PayDemo();
		
		Market root = demo.new MarketBranch("总店");
		Market branch = demo.new MarketBranch("秦皇岛分店");
		Market join1 = demo.new MarketJoin("秦皇岛分店1");
		Market join2 = demo.new MarketJoin("秦皇岛分店2");
		
		//分店下的加盟店
		branch.add(join1);
		branch.add(join2);
		
		//总店加盟下的分店
		root.add(branch);
		
		root.payByCard();
	}
}
