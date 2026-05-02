package com.interview.dependencyInjection;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

	public void saveOrder();

}
