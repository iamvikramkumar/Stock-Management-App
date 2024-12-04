package com.admin.app.controllertest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.admin.app.controller.AdminReportController;
import com.admin.app.models.Payment;
import com.admin.app.service.IReportService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminReportControllerTest.class)
public class AdminReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReportService reportService;

    @InjectMocks
    private AdminReportController adminReportController;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminReportController).build();
        payment = new Payment();
        payment.setId(1);
        payment.setDispatchId(1);
        payment.setAmount(1000.0);
    }

    @Test
    void testAddPayment() throws Exception {
        when(reportService.addPayment(1, 1)).thenReturn("Payment added successfully");

        mockMvc.perform(post("/admin/report/addPayment/add/1/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPaymentReportById() throws Exception {
        when(reportService.getPaymentReportById(1)).thenReturn(payment);

        mockMvc.perform(get("/admin/report/payment/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPaymentByDispatchId() throws Exception {
        when(reportService.getPaymentByDispatchId(1)).thenReturn(payment);

        mockMvc.perform(get("/admin/report/payment/byDispatchId/1"))
                .andExpect(status().isOk());
    }
}