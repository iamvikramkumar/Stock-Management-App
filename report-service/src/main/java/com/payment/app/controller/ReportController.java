package com.payment.app.controller;

import com.payment.app.models.Payment;
import com.payment.app.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;
    @GetMapping("/payment/byDate")
    public ResponseEntity<List<Payment>> getPaymentReportByDate(@RequestParam("date") LocalDate date) {
        List<Payment> paymentReport = reportService.findByDate(date);
        return ResponseEntity.ok(paymentReport);
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getPaymentReportById(@PathVariable("paymentId") int paymentId) {
        Payment paymentReport = reportService.findById(paymentId);
        if (paymentReport != null) {
            return ResponseEntity.ok(paymentReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/payment/byDateRange")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        try {
            List<Payment> payments = reportService.findByDateOfDispatchBetween(startDate, endDate);
            
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/payment/byDispatchId/{dispatchId}")
    public ResponseEntity<Payment> getPaymentByDispatchId(@PathVariable("dispatchId") int dispatchId) {
        try {
            Payment payment = reportService.findByDispatchId(dispatchId);
   
            if (payment != null) {
                return ResponseEntity.ok(payment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
