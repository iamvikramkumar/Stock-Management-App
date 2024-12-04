package com.payment.app.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payment.app.client.ICompanyClient;
import com.payment.app.client.IDispatchClient;
import com.payment.app.client.IStockClient;
import com.payment.app.exception.PaymentNotSavedException;
import com.payment.app.models.Company;
import com.payment.app.models.Dispatch;
import com.payment.app.models.Payment;

import com.payment.app.repository.IPaymentRepository;
import com.payment.app.service.PaymentServiceImpl;
import com.stock.app.model.Stock;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private IStockClient stockClient;

    @Mock
    private ICompanyClient companyClient;

    @Mock
    private IDispatchClient dispatchClient;

    @Mock
    private IPaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private Dispatch dispatch;

    @BeforeEach
    public void setUp() {
        dispatch = new Dispatch();
        dispatch.setDispatchId(1);
        dispatch.setStockId(1);
        dispatch.setToCompanyId(1);
        dispatch.setAmount(1000.0);
        dispatch.setQuantity(10);

        payment = new Payment();
        payment.setId(1);
        payment.setDispatchId(dispatch.getDispatchId());
        payment.setAmount(dispatch.getAmount());
    }

    @Test
    public void testAddPayment() {
        // Arrange
        Stock stock = new Stock(1, "Stock A", "HP", 100.0, 10); // Correct Stock initialization
        Company company = new Company(1, "Company A");
        
        when(dispatchClient.getById(1)).thenReturn(dispatch);
        when(stockClient.getStockById(1)).thenReturn(stock);
        when(companyClient.getCompanyById(1)).thenReturn(company);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // Act
        String result = paymentService.addPayment(1, 1);

        // Assert
        assertEquals("PAYMENT ADDED", result);
    }

    @Test
    public void testAddPaymentFailedToSave() {
        // Arrange
        Stock stock = new Stock(1, "Stock A", "HP", 100.0, 10); // Correct Stock initialization
        Company company = new Company(1, "Company A");
        
        when(dispatchClient.getById(1)).thenReturn(dispatch);
        when(stockClient.getStockById(1)).thenReturn(stock);
        when(companyClient.getCompanyById(1)).thenReturn(company);
        when(paymentRepository.save(any(Payment.class))).thenReturn(null);

        // Act & Assert
        PaymentNotSavedException exception = assertThrows(PaymentNotSavedException.class, () -> {
            paymentService.addPayment(1, 1);
        });
        assertEquals("Payment could not be saved.", exception.getMessage());
    }

    @Test
    public void testGenerateFormattedReceipt() {
        // Arrange
        payment.setStockName("Stock A");
        payment.setCompanyName("Company A");
        payment.setQuantity(10);
        payment.setStockPrice(100.0);
        payment.setAmount(1000.0);
        payment.setDateOfDispatch(LocalDate.now());

        String expectedReceipt = "-------------------- Payment Receipt --------------------\n" +
                "Dispatch ID: 1\n" +
                "Stock: Stock A (ID: 1)\n" +
                "Company: Company A\n" +
                "Quantity Dispatched: 10\n" +
                "Stock Price: 100.0\n" +
                "Total Amount: 1000.0\n" +
                "Payment Date: " + payment.getDateOfDispatch() + "\n" +
                "---------------------------------------------------------\n";

        // Act
        String receipt = paymentService.generateFormattedReceipt(payment);

        // Assert
        assertEquals(expectedReceipt, receipt);
    }
}
