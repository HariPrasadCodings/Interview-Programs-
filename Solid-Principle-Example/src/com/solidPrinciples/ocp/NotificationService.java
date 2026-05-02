package com.solidPrinciples.ocp;

public interface NotificationService {
	public void sendOTP(String medium);

	public void sendTransactionNotification(String medium);
}
