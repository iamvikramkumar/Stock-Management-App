package com.payment.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.app.client.ICompanyClient;
import com.payment.app.client.IDispatchClient;
import com.payment.app.client.IStockClient;
import com.payment.app.exception.DispatchNotFoundException;
import com.payment.app.exception.InvalidDateException;
import com.payment.app.exception.PaymentNotFoundException;
import com.payment.app.models.Payment;
import com.payment.app.repository.IPaymentRepository;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private IPaymentRepository paymentRepository;

	@Override
	public Payment findById(int id) {
		
        Payment payment = paymentRepository.findById(id);

        if (payment == null) {
            throw new PaymentNotFoundException("Payment with ID " + id + " not found.");
        }
		return payment;
		
	}

	@Override
	public List<Payment> findByDate(LocalDate date) {
		
        if (date == null) {
            throw new InvalidDateException("Invalid date provided");
        }

		
		return paymentRepository.findByDateOfDispatch(date);
	}

	@Override
	public List<Payment> findByDateOfDispatchBetween(LocalDate dateOfDispatch1, LocalDate dateOfDispatch2) {
		
        if (dateOfDispatch1 == null || dateOfDispatch2 == null || dateOfDispatch1.isAfter(dateOfDispatch2)) {
            throw new InvalidDateException("Invalid date range provided");
        }
		
		return paymentRepository.findByDateOfDispatchBetween(dateOfDispatch1, dateOfDispatch2);
		
		
	}

	@Override
	public Payment findByDispatchId(int dispatchId) {
		//Exception Here 
		Payment payment = paymentRepository.findByDispatchId(dispatchId);
		
        if (payment == null) {
            throw new DispatchNotFoundException("No payment found for dispatchId " + dispatchId);
        }
		
		return paymentRepository.findByDispatchId(dispatchId);
	}

	

}
