package com.steelzack.coffee.system.objects;

public class Programmer {
	private static int GATHER_INFORMATION = 500;

	private static int FIND_A_CUP = 250;

	private static int PUT_UNDER_OUTLET = 250;

	private static int PICK_TYPE_OF_COFFEE = 250;

	private static int TAKE_CUP_AND_LEAVE = 250;

	private Coffee coffee;

	private Payment payment;

	private int offset = 0;
	
	private int currentTimeStamp = 0;

	public Programmer(Coffee coffee, Payment payment) {
		this.coffee = coffee;
		this.payment = payment;
	}

	public void addOffset(int offset) {
		this.offset += offset;
	}

	public void addTimeStamp(int timeStamp)
	{
		this.currentTimeStamp += timeStamp;
	}
	
	public boolean isDonePick()
	{
		return currentTimeStamp == GATHER_INFORMATION;
	}
	
	public boolean isDonePay()
	{
		return currentTimeStamp == 	payment.getTimeToPay()+ GATHER_INFORMATION;
	}
	
	public boolean isDoneGet()
	{
		return currentTimeStamp == getCompleteCoffeGet();
	}
	
	public int getCompleteTime() {
		return GATHER_INFORMATION + //
				payment.getTimeToPay() + //
				getCompleteCoffeGet();
	}

	private int getCompleteCoffeGet() {
		return FIND_A_CUP + //
				PUT_UNDER_OUTLET + //
				PICK_TYPE_OF_COFFEE + //
				offset + //
				coffee.getFillupTime() + //
				TAKE_CUP_AND_LEAVE;
	}
	
	public Coffee getCoffee() {
		return coffee;
	}
	
	public Payment getPayment() {
		return payment;
	}
}
