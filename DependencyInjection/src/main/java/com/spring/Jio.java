package com.spring;

public class Jio implements Sim {
	@Override
	public void calling() {
		System.out.println("Calling using airtel sim");
	}

	@Override
	public void data() {
		System.out.println("Browsing data using airtel sim");
	}

}
