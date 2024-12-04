package com.payment.app.controller;

import com.payment.app.models.Payment;
import com.payment.app.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;
    
    @PostMapping("/add/{id}/{dispatchId}")
    public ResponseEntity<String> addPayment(@PathVariable("id") int id, @PathVariable("dispatchId") int dispatchId) {
        try {

            String paymentResult = paymentService.addPayment(id, dispatchId);

            if ("PAYMENT ADDED".equals(paymentResult)) {
                return new ResponseEntity<>(paymentResult, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/print/{paymentId}")
    public ResponseEntity<String> printPaymentReceipt(@PathVariable("paymentId") Payment payment) {
        try {

            String receipt = paymentService.generateFormattedReceipt(payment);
            
            if (receipt != null) {
                return new ResponseEntity<>(receipt, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Payment not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating receipt.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
