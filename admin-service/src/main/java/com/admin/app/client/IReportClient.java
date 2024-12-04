package com.admin.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.admin.app.models.Payment;

@FeignClient(name = "report-service", url = "http://localhost:8081/reportService")
public interface IReportClient {
	@GetMapping("/payment/print/{paymentId}")
	public String printPaymentReceipt(@PathVariable("paymentId") int paymentId);
	
	@PostMapping("/payment/add/{id}/{dispatchId}")
	public String addPayment(@PathVariable("id") int id, @PathVariable("dispatchId") int dispatchId);
	
	@GetMapping("/report/payment/{paymentId}")
	public Payment getPaymentReportById(@PathVariable("paymentId") int paymentId);
	
	@GetMapping("/report/payment/byDispatchId/{dispatchId}")
	public Payment getPaymentByDispatchId(@PathVariable("dispatchId") int dispatchId);

}
