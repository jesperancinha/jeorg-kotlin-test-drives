package com.steelzack.coffee.system.objects;

public enum Payment {
	CREDITCARD(250), //
	CASH(500) //
	;
	private int timeToPay;

	private Payment(int timeToPay) {
		this.timeToPay = timeToPay;
	}
	
	public int getTimeToPay() {
		return timeToPay;
	}
}
