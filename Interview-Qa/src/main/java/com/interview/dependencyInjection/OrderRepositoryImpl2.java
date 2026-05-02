package com.interview.dependencyInjection;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl2 implements OrderRepository {

	@Override
	public void saveOrder() {
		System.out.println("OrderRepositoryImpl2::saveOrder() method executed..");
	}

}
