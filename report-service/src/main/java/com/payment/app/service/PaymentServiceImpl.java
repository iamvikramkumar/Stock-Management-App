package com.payment.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.app.client.ICompanyClient;
import com.payment.app.client.IDispatchClient;
import com.payment.app.client.IStockClient;
import com.payment.app.exception.PaymentNotSavedException;
import com.payment.app.models.Dispatch;
import com.payment.app.models.Payment;
import com.payment.app.repository.IPaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IStockClient stockClient;
	
	@Autowired
	private ICompanyClient companyClient;
	
	@Autowired
	private IDispatchClient dispatchClient;
	
	@Autowired
	private IPaymentRepository paymentRepository;
	
	public String addPayment(int id,int dispatchId) {
		log.info("Reached");
		Dispatch dispatch=dispatchClient.getById(dispatchId);
		Payment payment=new Payment(id,dispatch.getStockId(),dispatch.getDispatchId(),companyClient.getCompanyById(dispatch.getToCompanyId()).getName(),dispatch.getDateOfDispatch(),dispatch.getAmount(),dispatch.getQuantity(),stockClient.getStockById(dispatch.getStockId()).getName(),stockClient.getStockById(dispatch.getStockId()).getPrice());
		payment=paymentRepository.save(payment);
        if (payment == null) {
            throw new PaymentNotSavedException("Payment could not be saved.");
        }
		return "PAYMENT ADDED";
	}
	@Override
	public String generateFormattedReceipt(Payment payment) {
        return "-------------------- Payment Receipt --------------------\n" +
                "Dispatch ID: " + payment.getDispatchId() + "\n" +
                "Stock: " + payment.getStockName() + " (ID: " + payment.getStockId() + ")\n" +
                "Company: " + payment.getCompanyName() + "\n" +
                "Quantity Dispatched: " + payment.getQuantity() + "\n" +
                "Stock Price: " + payment.getStockPrice() + "\n" +
                "Total Amount: " + payment.getAmount() + "\n" +
                "Payment Date: " + payment.getDateOfDispatch() + "\n" +
                "---------------------------------------------------------\n";
    }
	
}
