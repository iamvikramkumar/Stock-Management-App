package com.payment.app.service;

import com.payment.app.models.Payment;

public interface IPaymentService {
	public String generateFormattedReceipt(Payment payment);

}
