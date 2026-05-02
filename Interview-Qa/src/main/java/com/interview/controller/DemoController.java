package com.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.scope.SessionScopeBean;

@RestController
public class DemoController {

//	@Autowired
//	private RequestScopeBean scopeBean;

	@Autowired
	private SessionScopeBean sessionScopeBean;

	@GetMapping("/message")
	private String getMessage() {
		return sessionScopeBean.getMessage();
	}

}
