package com.payment.app.service;

import java.time.LocalDate;
import java.util.List;

import com.payment.app.models.Payment;

public interface IReportService {
	public Payment findById(int id);
	public Payment findByDispatchId(int dispatchId);
	public List<Payment> findByDate(LocalDate date);
	public List<Payment> findByDateOfDispatchBetween(LocalDate dateOfDispatch1,LocalDate dateOfDispatch2);

}
