package com.admin.app.service;

import com.admin.app.models.Payment;

public interface IReportService {
	public String addPayment(int id,int dispatchId);
	public String printPaymentReceipt(int paymentId);
	public Payment getPaymentReportById(int id);
	public Payment getPaymentByDispatchId(int id);
	

}
