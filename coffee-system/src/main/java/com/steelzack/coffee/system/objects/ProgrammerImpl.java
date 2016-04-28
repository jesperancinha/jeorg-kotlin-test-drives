package com.steelzack.coffee.system.objects;

import com.steelzack.coffee.system.enums.Coffee;
import com.steelzack.coffee.system.enums.Payment;
import com.steelzack.coffee.system.objecs.Programmer;

public class ProgrammerImpl implements Programmer {
	private static int GATHER_INFORMATION = 500;

	private static int FIND_A_CUP = 250;

	private static int PUT_UNDER_OUTLET = 250;

	private static int PICK_TYPE_OF_COFFEE = 250;

	private static int TAKE_CUP_AND_LEAVE = 250;

	private Coffee coffee;

	private Payment payment;

	private int offset = 0;
	
	private int currentTimeStamp = 0;

	public ProgrammerImpl(Coffee coffee, Payment payment) {
		this.coffee = coffee;
		this.payment = payment;
	}

	@Override
	public void addOffset(int offset) {
		this.offset += offset;
	}

	@Override
	public void addTimeStamp(int timeStamp)
	{
		this.currentTimeStamp += timeStamp;
	}

	@Override
	public boolean isDonePick()
	{
		return currentTimeStamp == GATHER_INFORMATION;
	}

	@Override
	public boolean isDonePay()
	{
		return currentTimeStamp == 	payment.getTimeToPay()+ GATHER_INFORMATION;
	}

	@Override
	public boolean isDoneGet()
	{
		return currentTimeStamp == getCompleteCoffeGet();
	}

	@Override
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


	@Override
	public Coffee getCoffee() {
		return coffee;
	}

	@Override
	public Payment getPayment() {
		return payment;
	}
}
