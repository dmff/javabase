package com.vortex.proxy;



public class HubeiNikeProxy implements NikeProvider,NikeCustomer {

	private ChinaNikeProxy chinaNikeProxy;
	
	public HubeiNikeProxy(ChinaNikeProxy chinaNikeProxy) {
		super();
		this.chinaNikeProxy = chinaNikeProxy;
	}

	public ChinaNikeProxy getChinaNikeProxy() {
		return chinaNikeProxy;
	}
	
	@Override
	public double getDiscount() {
		return 1.0;
	}

	@Override
	public Shoes provideShoes(NikeCustomer customer) {
		if(!(customer instanceof NikeRetailCustomer)){//不是终端客户
			throw new NoRetailCustomerException();
		}
		
		NikeRetailCustomer customer2 = (NikeRetailCustomer)customer;
		Shoes shoes = chinaNikeProxy.provideShoes(this);
		Currency price = shoes.getPrice();
		NikeRetailCustomer.Location location = customer2.getLocation();
		if(location != NikeRetailCustomer.Location.HUBEI){//不是湖北的，在该客户折扣率的基础上加价100元
			price.setValue(price.getValue() * customer2.getDiscount() + 100.0);
		}else{//在该折扣率的基础上加50.0
			price.setValue(price.getValue() * customer2.getDiscount() + 50.0);
		}
		return shoes;
	}

	
}
