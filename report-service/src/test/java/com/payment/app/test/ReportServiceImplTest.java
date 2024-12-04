package com.payment.app.test;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payment.app.exception.DispatchNotFoundException;
import com.payment.app.exception.InvalidDateException;
import com.payment.app.exception.PaymentNotFoundException;
import com.payment.app.models.Payment;
import com.payment.app.repository.IPaymentRepository;
import com.payment.app.service.ReportServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @Mock
    private IPaymentRepository paymentRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Payment payment;
    private LocalDate testDate;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000.0);
        payment.setDateOfDispatch(LocalDate.now());
        testDate = LocalDate.now();
        startDate = testDate.minusDays(5);
        endDate = testDate.plusDays(5);
    }

    @Test
    public void testFindByIdPaymentNotFound() {
        // Arrange
        when(paymentRepository.findById(1)).thenReturn(null);

        // Act & Assert
        PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class, () -> {
            reportService.findById(1);
        });
        assertEquals("Payment with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testFindById() {
        // Arrange
        when(paymentRepository.findById(1)).thenReturn(payment);

        // Act
        Payment foundPayment = reportService.findById(1);

        // Assert
        assertNotNull(foundPayment);
        assertEquals(1, foundPayment.getId());
    }

    @Test
    public void testFindByDateInvalid() {
        // Act & Assert
        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> {
            reportService.findByDate(null);
        });
        assertEquals("Invalid date provided", exception.getMessage());
    }

    @Test
    public void testFindByDate() {
        // Arrange
        when(paymentRepository.findByDateOfDispatch(testDate)).thenReturn(List.of(payment));

        // Act
        List<Payment> payments = reportService.findByDate(testDate);

        // Assert
        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void testFindByDateOfDispatchBetweenInvalidRange() {
        // Act & Assert
        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> {
            reportService.findByDateOfDispatchBetween(endDate, startDate);
        });
        assertEquals("Invalid date range provided", exception.getMessage());
    }

    @Test
    public void testFindByDateOfDispatchBetween() {
        // Arrange
        when(paymentRepository.findByDateOfDispatchBetween(startDate, endDate)).thenReturn(List.of(payment));

        // Act
        List<Payment> payments = reportService.findByDateOfDispatchBetween(startDate, endDate);

        // Assert
        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void testFindByDispatchIdDispatchNotFound() {
        // Arrange
        when(paymentRepository.findByDispatchId(1)).thenReturn(null);

        // Act & Assert
        DispatchNotFoundException exception = assertThrows(DispatchNotFoundException.class, () -> {
            reportService.findByDispatchId(1);
        });
        assertEquals("No payment found for dispatchId 1", exception.getMessage());
    }

}

