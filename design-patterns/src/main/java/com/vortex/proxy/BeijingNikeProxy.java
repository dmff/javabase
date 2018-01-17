package com.vortex.proxy;


public class BeijingNikeProxy implements NikeProvider,NikeCustomer {

	private ChinaNikeProxy chinaNikeProxy;
	
	public BeijingNikeProxy(ChinaNikeProxy chinaNikeProxy) {
		super();
		this.chinaNikeProxy = chinaNikeProxy;
	}

	public ChinaNikeProxy getChinaNikeProxy() {
		return chinaNikeProxy;
	}
	


	@Override
	public double getDiscount() {
		return 0.98;
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
		if(location != NikeRetailCustomer.Location.BEIJING){//不是北京的，禁止出售
			throw new NotBeijingCustomerException();
		}else{//在该折扣率的基础上加100.0
			price.setValue(price.getValue() * customer2.getDiscount() + 100.0);
		}
		return shoes;
	}

	
	private class NotBeijingCustomerException extends RuntimeException{
		private static final long serialVersionUID = 8069669329270233939L;

		public NotBeijingCustomerException() {
			super("抱歉，无法给非北京客户提供服务！");
		}
	}
}
