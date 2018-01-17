package com.vortex.proxy;



public class NikeCompany implements NikeProvider{

	private final double standardUsPrice = 100.0;
	
	@Override
	public Shoes provideShoes(NikeCustomer customer) {
		//鞋子卖的价钱等于货币类型，和价钱*折扣
		return new Shoes("kobe 9", new Currency(Currency.CurrencyType.USD, standardUsPrice*customer.getDiscount()));
	}

}
