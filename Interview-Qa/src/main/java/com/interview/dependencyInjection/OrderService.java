package com.interview.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private RestClientService restClientService;

	@Autowired
	@Lazy
	public void setRestClientService(RestClientService restClientService) {
		this.restClientService = restClientService;
	}

	// private OrderRepository orderRepository;

//	// //Setter DI
//	// optional dependency injection
//	// not immutable in nature
//	// circular dependency can't resolve
//	@Autowired
//	public void setOrderRepository(OrderRepository orderRepository) {
//		this.orderRepository = orderRepository;
//	}

	/**
	 * Constrcutor Dependency Injection is mandatory D.I Immutable in nature: once
	 * the fields got injected we can modify it Circular dependency can't resolve
	 */
//	public OrderService(OrderRepository orderRepository) {
//		super();
//		this.orderRepository = orderRepository;
//	}

}
