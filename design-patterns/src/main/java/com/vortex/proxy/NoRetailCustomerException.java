package com.vortex.proxy;

public class NoRetailCustomerException extends RuntimeException {
	private static final long serialVersionUID = -198225507160799609L;

	public NoRetailCustomerException() {
		
		super("不是终端客户");
	}
}
	