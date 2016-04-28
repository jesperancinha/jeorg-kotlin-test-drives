package com.steelzack.coffee.system.enums;

public enum Coffee {
	Expresso(250), //
	LatteMachiatto(500), //
	Cappuccino(750) //
	;
	private int fillupTime;
	Coffee(int fillupTime)
	{
		this.fillupTime = fillupTime;
	}
	
	public int getFillupTime() {
		return fillupTime;
	}	
}
