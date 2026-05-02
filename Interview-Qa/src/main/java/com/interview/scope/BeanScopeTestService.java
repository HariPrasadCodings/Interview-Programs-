package com.interview.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class BeanScopeTestService {

	public BeanScopeTestService() {
		System.out.println("BeanScopeTestService instance created...");
	}

}
