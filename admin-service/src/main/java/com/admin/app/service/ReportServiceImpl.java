package com.admin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.app.client.IReportClient;
import com.admin.app.models.Payment;
@Service
public class ReportServiceImpl implements IReportService {
	@Autowired
	IReportClient rc;
	
	@Override
	public String addPayment(int id, int dispatchId) {
		return rc.addPayment(id, dispatchId);
	}

	@Override
	public String printPaymentReceipt(int paymentId) {
		
		return rc.printPaymentReceipt(paymentId);
	}

	@Override
	public Payment getPaymentReportById(int id) {
		
		return rc.getPaymentReportById(id);
	}

	@Override
	public Payment getPaymentByDispatchId(int id) {
		
		return rc.getPaymentByDispatchId(id);
	}

}
