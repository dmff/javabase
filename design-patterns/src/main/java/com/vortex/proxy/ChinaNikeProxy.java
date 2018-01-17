package com.vortex.proxy;



public class ChinaNikeProxy implements NikeCustomer,NikeProvider{

	private NikeCompany nikeCompany;
	private double discount;
	
	public ChinaNikeProxy(NikeCompany nikeCompany, double discount) {
		super();
		this.nikeCompany = nikeCompany;
		this.discount = discount;
	}

	public NikeCompany getNikeCompany() {
		return nikeCompany;
	}

	public void setNikeCompany(NikeCompany nikeCompany) {
		this.nikeCompany = nikeCompany;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public Shoes provideShoes(NikeCustomer customer) {
		Shoes shoes = nikeCompany.provideShoes(this);
		shoes.setName("黑曼巴9代");
		Currency price = shoes.getPrice();
		double usPrice = price.getValue();
		double rmbPrice = getRmbPrice(usPrice) * customer.getDiscount();
		price.setType(Currency.CurrencyType.RMB);
		price.setValue(rmbPrice);
		return shoes;
	}

	private double getRmbPrice(double usPrice){
		return Rate.getRMBRate() * usPrice;
	}
	
	@Override
	public double getDiscount() {
		return discount;
	}

}
