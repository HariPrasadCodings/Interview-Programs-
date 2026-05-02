package com.interview.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class TrailerService {

	@Autowired
	//@Qualifier(value = "orderRepositoryImpl1") // Dependency will be injected by type
	@Resource(name = "orderRepositoryImpl2")
	private OrderRepository orderRepository;

}
