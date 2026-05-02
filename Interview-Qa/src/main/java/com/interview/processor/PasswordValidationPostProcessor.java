package com.interview.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.interview.common.User;

@Component
public class PasswordValidationPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		if (bean instanceof User user && !isValidPassword(user.getPassword())) {
			throw new IllegalArgumentException("Invalid password for user: " + user.getUsername());
		}

		return bean;
	}

	private boolean isValidPassword(String password) {

		return password.length() >= 8 && password.matches(".*[#@$%!].*");
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
