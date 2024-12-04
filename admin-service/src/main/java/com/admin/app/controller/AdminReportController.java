package com.admin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.app.models.Payment;
import com.admin.app.service.IReportService;

@RestController
@EnableFeignClients
@RequestMapping("/admin/report")
public class AdminReportController {
	@Autowired
	private IReportService rs;
	
	@PostMapping("/addPayment/add/{id}/{dispatchId}")
	public String addPayment(@PathVariable("id") int id, @PathVariable("dispatchId") int dispatchId) {
		return rs.addPayment(id, dispatchId);
	}
	
	@GetMapping("/payment/{paymentId}")
	public Payment getPaymentReportById(@PathVariable("paymentId") int id) {
		return rs.getPaymentReportById(id);
	}
	
	@GetMapping("/payment/byDispatchId/{dispatchId}")
	public Payment getPaymentByDispatchId(@PathVariable("dispatchId") int dispatchId) {
		return rs.getPaymentByDispatchId(dispatchId);
	}

}
