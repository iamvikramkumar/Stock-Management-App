package com.dispatch.app.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dispatch.app.exception.InvalidCompanyException;
import com.dispatch.app.exception.InvalidStockQuantityException;
import com.dispatch.app.feign.IFeignClientCompany;
import com.dispatch.app.feign.IFeignClients;
import com.dispatch.app.model.Company;
import com.dispatch.app.model.Dispatch;
import com.dispatch.app.model.Stock;
import com.dispatch.app.repository.IDispatchRepository;
import com.dispatch.app.service.DispatchServiceImpl;



public class DispatchServiceImplTest {

    @Mock
    private IFeignClients feignClientStock; // Mocked Feign client for Stock
    @Mock
    private IFeignClientCompany feignClientCompany; // Mocked Feign client for Company
    @Mock
    private IDispatchRepository dispatchRepository; // Mocked repository for Dispatch
    
    @InjectMocks
    private DispatchServiceImpl dispatchService; // The service being tested

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void shouldThrowInvalidCompanyExceptionWhenStockDoesNotExist() {
        // Given a dispatch with a stock that doesn't exist
        Dispatch dispatch = new Dispatch();
        dispatch.setStockId(123);  // Set a stock ID that won't be found

        // When stock is not found
        when(feignClientStock.getData(dispatch.getStockId())).thenReturn(null);

        // Then an InvalidCompanyException should be thrown
        InvalidCompanyException exception = assertThrows(InvalidCompanyException.class, () -> {
            dispatchService.addDispatch(dispatch);
        });

        assertEquals("Stock Does Not Exists", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidCompanyExceptionWhenCompanyDoesNotExist() {
        // Given a dispatch with company IDs that don't exist
        Dispatch dispatch = new Dispatch();
        dispatch.setStockId(123);
        dispatch.setFromCompanyId(1);  // From company ID
        dispatch.setToCompanyId(2);    // To company ID
        dispatch.setQuantity(10);

        Stock stock = new Stock();
        stock.setCompanyName("Company A");

        Company company1 = null;  // Company does not exist
        Company company2 = new Company();
        company2.setName("Company B");

        // When stock exists but companies do not
        when(feignClientStock.getData(dispatch.getStockId())).thenReturn(stock);
        when(feignClientCompany.getData(dispatch.getFromCompanyId())).thenReturn(company1); // Null company
        when(feignClientCompany.getData(dispatch.getToCompanyId())).thenReturn(company2);

        // Then an InvalidCompanyException should be thrown
        InvalidCompanyException exception = assertThrows(InvalidCompanyException.class, () -> {
            dispatchService.addDispatch(dispatch);
        });

        assertEquals("Company Does Not Exists", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidCompanyExceptionWhenCompanyNamesDoNotMatch() {
        // Given a dispatch with stock and company names that don't match
        Dispatch dispatch = new Dispatch();
        dispatch.setStockId(123);
        dispatch.setFromCompanyId(1); // From company ID
        dispatch.setToCompanyId(2);   // To company ID
        dispatch.setQuantity(10);

        Stock stock = new Stock();
        stock.setCompanyName("Company A");

        Company company1 = new Company();
        company1.setName("Company B"); // Company name does not match stock

        Company company2 = new Company();
        company2.setName("Company C");

        // When stock exists but company names don't match
        when(feignClientStock.getData(dispatch.getStockId())).thenReturn(stock);
        when(feignClientCompany.getData(dispatch.getFromCompanyId())).thenReturn(company1); // Mismatch in company
        when(feignClientCompany.getData(dispatch.getToCompanyId())).thenReturn(company2);

        // Then an InvalidCompanyException should be thrown
        InvalidCompanyException exception = assertThrows(InvalidCompanyException.class, () -> {
            dispatchService.addDispatch(dispatch);
        });

        assertEquals("Company Does Not Match", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidStockQuantityExceptionWhenQuantityIsGreaterThanAvailableStock() {
        // Given a dispatch with quantity greater than available stock
        Dispatch dispatch = new Dispatch();
        dispatch.setStockId(123);
        dispatch.setFromCompanyId(1);
        dispatch.setToCompanyId(2);
        dispatch.setQuantity(20);

        Stock stock = new Stock();
        stock.setCompanyName("Company A");
        stock.setQuantity(10); // Available stock is 10

        Company company1 = new Company();
        company1.setName("Company A");

        Company company2 = new Company();
        company2.setName("Company B");

        // When stock exists but quantity exceeds available stock
        when(feignClientStock.getData(dispatch.getStockId())).thenReturn(stock);
        when(feignClientCompany.getData(dispatch.getFromCompanyId())).thenReturn(company1);
        when(feignClientCompany.getData(dispatch.getToCompanyId())).thenReturn(company2);

        // Then an InvalidStockQuantityException should be thrown
        InvalidStockQuantityException exception = assertThrows(InvalidStockQuantityException.class, () -> {
            dispatchService.addDispatch(dispatch);
        });

        assertEquals("Insufficient Quantity", exception.getMessage());
    }
}
