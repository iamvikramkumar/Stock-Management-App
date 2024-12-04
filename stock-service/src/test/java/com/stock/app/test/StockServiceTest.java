package com.stock.app.test;

import com.stock.app.exception.InvalidRangeException;
import com.stock.app.exception.StockAlreadyExistsException;
import com.stock.app.exception.StockNotFoundException;
import com.stock.app.model.Stock;
import com.stock.app.repository.IStockRepository;
import com.stock.app.service.StockService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private IStockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = new Stock(1, "Stock A", "Company A", 100.0, 10);
    }

    // Test for searchByStockId when stock is found
    @Test
    public void testSearchByStockId_Success() {
        // Arrange: Mock the findById method to return Optional.of(stock)
        when(stockRepository.findById(1)).thenReturn(stock);

        // Act
        Stock result = stockService.searchByStockId(1);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(stockRepository, times(1)).findById(1);
    }

    // Test for searchByStockPriceGreaterThanEqual
    @Test
    public void testSearchByStockPriceGreaterThanEqual() {
        // Arrange: Mock findByPriceGreaterThanEqual
        when(stockRepository.findByPriceGreaterThanEqual(50.0)).thenReturn(List.of(stock));

        // Act
        List<Stock> result = stockService.searchByStockPriceGreaterThanEqual(50.0);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(stockRepository, times(1)).findByPriceGreaterThanEqual(50.0);
    }

    @Test
    public void testSearchByStockPriceGreaterThanEqual_InvalidPrice() {
        // Act & Assert: Expect IllegalArgumentException for negative price
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockService.searchByStockPriceGreaterThanEqual(-1.0);
        });
        assertEquals("Stock price cannot be negative", exception.getMessage());
    }

    // Test for searchByStockPriceLessThanEqual
    @Test
    public void testSearchByStockPriceLessThanEqual() {
        // Arrange: Mock findByPriceLessThanEqual
        when(stockRepository.findByPriceLessThanEqual(150.0)).thenReturn(List.of(stock));

        // Act
        List<Stock> result = stockService.searchByStockPriceLessThanEqual(150.0);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(stockRepository, times(1)).findByPriceLessThanEqual(150.0);
    }

    @Test
    public void testSearchByStockPriceLessThanEqual_InvalidPrice() {
        // Act & Assert: Expect IllegalArgumentException for negative price
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockService.searchByStockPriceLessThanEqual(-1.0);
        });
        assertEquals("Stock price cannot be negative", exception.getMessage());
    }

    // Test for searchByStockPriceBetween
    @Test
    public void testSearchByStockPriceBetween_Success() {
        // Arrange: Mock findByPriceBetween
        when(stockRepository.findByPriceBetween(50.0, 150.0)).thenReturn(List.of(stock));

        // Act
        List<Stock> result = stockService.searchByStockPriceBetween(50.0, 150.0);

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(stockRepository, times(1)).findByPriceBetween(50.0, 150.0);
    }

    @Test
    public void testSearchByStockPriceBetween_InvalidRange() {
        // Act & Assert: Expect InvalidRangeException for invalid range
        InvalidRangeException exception = assertThrows(InvalidRangeException.class, () -> {
            stockService.searchByStockPriceBetween(150.0, 50.0);
        });
        assertEquals("The first stock price cannot be greater than the second stock price", exception.getMessage());
    }

  

    // Test for deleteStock
    @Test
    public void testDeleteStock_Success() {
        // Arrange: Mock existsById to return true (stock exists)
        when(stockRepository.existsById(1)).thenReturn(true);

        // Act
        boolean result = stockService.deleteStock(1);

        // Assert: Verify the result and interactions
        assertTrue(result);
        verify(stockRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteStock_StockNotFound() {
        // Arrange: Mock existsById to return false (stock does not exist)
        when(stockRepository.existsById(1)).thenReturn(false);

        // Act & Assert: Expect StockNotFoundException to be thrown
        StockNotFoundException exception = assertThrows(StockNotFoundException.class, () -> {
            stockService.deleteStock(1);
        });
        assertEquals("Stock with ID 1 not found", exception.getMessage());
    }



    // Test for searchByCompanyName

}
