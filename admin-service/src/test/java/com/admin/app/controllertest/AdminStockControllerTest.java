package com.admin.app.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.admin.app.controller.AdminStockController;
import com.admin.app.models.Stock;
import com.admin.app.service.IStockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminStockControllerTest.class)
public class AdminStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStockService stockService;

    @InjectMocks
    private AdminStockController adminStockController;

    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminStockController).build();
        stock = new Stock();
        stock.setId(1);
        stock.setName("Test Stock");
    }

    @Test
    void testGetStockById() throws Exception {
        when(stockService.searchById(1)).thenReturn(stock);

        mockMvc.perform(get("/admin/stock/getStockById/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddStock() throws Exception {
        when(stockService.addStock(stock)).thenReturn("Stock added successfully");

        mockMvc.perform(post("/admin/stock/addStock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateStock() throws Exception {
        when(stockService.updateStock(stock)).thenReturn("Stock updated successfully");

        mockMvc.perform(put("/admin/stock/updateStock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteStock() throws Exception {
        when(stockService.deleteStock(1)).thenReturn("Stock deleted successfully");

        mockMvc.perform(delete("/admin/stock/deleteStock/1"))
                .andExpect(status().isOk());
    }
}