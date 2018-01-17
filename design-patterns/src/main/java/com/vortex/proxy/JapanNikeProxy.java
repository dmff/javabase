package com.vortex.proxy;



public class JapanNikeProxy implements NikeProvider,NikeCustomer{

	private NikeCompany nikeCompany;
	private double discount;
	public JapanNikeProxy(NikeCompany nikeCompany,double discount) {
		super();
		this.nikeCompany = nikeCompany;
		this.discount = discount;
	}

	public NikeCompany getNikeCompany() {
		return nikeCompany;
	}
	
	@Override
	public Shoes provideShoes(NikeCustomer customer) {
		Shoes shoes = nikeCompany.provideShoes(this);
		shoes.setName("ブラックマンバ");
		Currency price = shoes.getPrice();
		double usPrice = price.getValue();
		double yenPrice = getYenPrice(usPrice) * customer.getDiscount();
		price.setType(Currency.CurrencyType.YEN);
		price.setValue(yenPrice);
		return shoes;
	}

	private double getYenPrice(double usPrice){
		return Rate.getYenRate() * usPrice;
	}

	@Override
	public double getDiscount() {
		return discount;
	}
}
