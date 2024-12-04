package com.payment.app.controller.test;


import com.payment.app.controller.ReportController;
import com.payment.app.models.Payment;
import com.payment.app.service.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Mock
    private ReportServiceImpl reportService;

    @InjectMocks
    private ReportController reportController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void testGetPaymentReportByDate() throws Exception {
        // Arrange
        LocalDate date = LocalDate.now();
        Payment payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000.0);
        List<Payment> payments = Arrays.asList(payment);
        when(reportService.findByDate(date)).thenReturn(payments);

        // Act & Assert
        mockMvc.perform(get("/report/payment/byDate?date=" + date))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'amount': 1000.0}]"));
    }

    @Test
    public void testGetPaymentReportById() throws Exception {
        // Arrange
        Payment payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000.0);
        when(reportService.findById(1)).thenReturn(payment);

        // Act & Assert
        mockMvc.perform(get("/report/payment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'amount': 1000.0}"));
    }

    @Test
    public void testGetPaymentsByDateRange() throws Exception {
        // Arrange
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now();
        Payment payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000.0);
        List<Payment> payments = Arrays.asList(payment);
        when(reportService.findByDateOfDispatchBetween(startDate, endDate)).thenReturn(payments);

        // Act & Assert
        mockMvc.perform(get("/report/payment/byDateRange?startDate=" + startDate + "&endDate=" + endDate))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'amount': 1000.0}]"));
    }

    @Test
    public void testGetPaymentByDispatchId() throws Exception {
        // Arrange
        Payment payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000.0);
        when(reportService.findByDispatchId(1)).thenReturn(payment);

        // Act & Assert
        mockMvc.perform(get("/report/payment/byDispatchId/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'amount': 1000.0}"));
    }

    @Test
    public void testGetPaymentByDispatchId_NotFound() throws Exception {
        // Arrange
        when(reportService.findByDispatchId(1)).thenThrow(new RuntimeException("No payment found"));

        // Act & Assert
        mockMvc.perform(get("/report/payment/byDispatchId/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));
    }
}

