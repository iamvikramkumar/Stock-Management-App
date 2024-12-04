package com.payment.app.controller.test;


import com.payment.app.controller.PaymentController;
import com.payment.app.models.Payment;
import com.payment.app.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void testAddPayment_Success() throws Exception {
        // Arrange
        when(paymentService.addPayment(1, 1)).thenReturn("PAYMENT ADDED");

        // Act & Assert
        mockMvc.perform(post("/payment/add/1/1"))
                .andExpect(status().isCreated())
                .andExpect(content().string("PAYMENT ADDED"));
    }

    @Test
    public void testAddPayment_Failure() throws Exception {
        // Arrange
        when(paymentService.addPayment(1, 1)).thenReturn("FAILED");

        // Act & Assert
        mockMvc.perform(post("/payment/add/1/1"))
                .andExpect(status().isBadRequest());
    }



}
