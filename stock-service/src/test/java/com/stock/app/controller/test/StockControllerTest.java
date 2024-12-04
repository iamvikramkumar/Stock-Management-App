package com.stock.app.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.app.controller.StockController;
import com.stock.app.model.Stock;
import com.stock.app.service.IStockService;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    private IStockService stockService;

    @InjectMocks
    private StockController stockController;

    private MockMvc mockMvc;

    private Stock stock;

    @BeforeEach
    public void setUp() {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();

        // Initialize the stock object
        stock = new Stock(1, "Stock A", "Company A", 100.0, 10);
    }

    // Test for addStock
    @Test
    public void testAddStock_Success() throws Exception {
        // Arrange: Mock the service method to return a success message
        when(stockService.addStock(any(Stock.class))).thenReturn("Stock Added");

        // Act & Assert: Send POST request and verify the response
        mockMvc.perform(post("/api/addStock")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock Added"));

        // Verify service method was called
        verify(stockService, times(1)).addStock(any(Stock.class));
    }

    @Test
    public void testAddStock_Failure() throws Exception {
        // Arrange: Mock the service method to throw an exception
        when(stockService.addStock(any(Stock.class))).thenReturn("Stock with this ID or name already exists");

        // Act & Assert: Send POST request and verify the response
        mockMvc.perform(post("/api/addStock")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock with this ID or name already exists"));

        // Verify service method was called
        verify(stockService, times(1)).addStock(any(Stock.class));
    }

    // Test for searchById
    @Test
    public void testSearchById_Success() throws Exception {
        // Arrange: Mock the service method to return a stock
        when(stockService.searchByStockId(1)).thenReturn(stock);

        // Act & Assert: Send GET request and verify the response
        mockMvc.perform(get("/api/searchById/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Stock A"));

        // Verify service method was called
        verify(stockService, times(1)).searchByStockId(1);
    }



    // Test for searchByCompanyName
    @Test
    public void testSearchByCompanyName_Success() throws Exception {
        // Arrange: Mock the service method to return a stock
        when(stockService.searchByCompanyName("Company A")).thenReturn(stock);

        // Act & Assert: Send GET request and verify the response
        mockMvc.perform(get("/api/searchByName/{companyName}", "Company A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Company A"));

        // Verify service method was called
        verify(stockService, times(1)).searchByCompanyName("Company A");
    }


    // Test for searchByStockPriceGreater
    @Test
    public void testSearchByStockPriceGreater_Success() throws Exception {
        // Arrange: Mock the service method to return a list of stocks
        when(stockService.searchByStockPriceGreaterThanEqual(50.0)).thenReturn(List.of(stock));

        // Act & Assert: Send GET request and verify the response
        mockMvc.perform(get("/api/searchByStockPriceGreater/{stockPrice}", 50.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        // Verify service method was called
        verify(stockService, times(1)).searchByStockPriceGreaterThanEqual(50.0);
    }

    // Test for searchByStockPriceLess
    @Test
    public void testSearchByStockPriceLess_Success() throws Exception {
        // Arrange: Mock the service method to return a list of stocks
        when(stockService.searchByStockPriceLessThanEqual(150.0)).thenReturn(List.of(stock));

        // Act & Assert: Send GET request and verify the response
        mockMvc.perform(get("/api/searchByStockPriceLess/{stockPrice}", 150.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        // Verify service method was called
        verify(stockService, times(1)).searchByStockPriceLessThanEqual(150.0);
    }

    // Test for searchByStockPriceBetween
    @Test
    public void testSearchByStockPriceBetween_Success() throws Exception {
        // Arrange: Mock the service method to return a list of stocks
        when(stockService.searchByStockPriceBetween(50.0, 150.0)).thenReturn(List.of(stock));

        // Act & Assert: Send GET request and verify the response
        mockMvc.perform(get("/api/searchByStockPriceBetween/{stockPrice1}/{stockPrice2}", 50.0, 150.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        // Verify service method was called
        verify(stockService, times(1)).searchByStockPriceBetween(50.0, 150.0);
    }

    // Test for deleteStock
    @Test
    public void testDeleteStock_Success() throws Exception {
        // Arrange: Mock the service method to return true
        when(stockService.deleteStock(1)).thenReturn(true);

        // Act & Assert: Send DELETE request and verify the response
        mockMvc.perform(delete("/api/deleteStock/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify service method was called
        verify(stockService, times(1)).deleteStock(1);
    }


    // Test for updateStock
    @Test
    public void testUpdateStock_Success() throws Exception {
        // Arrange: Mock the service method to return true
        when(stockService.updateStock(any(Stock.class))).thenReturn(true);

        // Act & Assert: Send PUT request and verify the response
        mockMvc.perform(put("/api/updateStock")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify service method was called
        verify(stockService, times(1)).updateStock(any(Stock.class));
    }
}

