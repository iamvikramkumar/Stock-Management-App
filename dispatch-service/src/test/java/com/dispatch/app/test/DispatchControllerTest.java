package com.dispatch.app.test;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dispatch.app.controller.DispatchController;
import com.dispatch.app.model.Dispatch;
import com.dispatch.app.service.DispatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DispatchControllerTest {

    @Mock
    private DispatchServiceImpl dispatchService; // Mock the service layer

    @InjectMocks
    private DispatchController dispatchController; // Inject the mocked service into the controller

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Initialize the mockMvc object
        mockMvc = MockMvcBuilders.standaloneSetup(dispatchController).build();
    }

    @Test
    public void shouldAddDispatch() throws Exception {
        // Given a dispatch object
        Dispatch dispatch = new Dispatch();
        dispatch.setStockId(123);
        dispatch.setFromCompanyId(1);
        dispatch.setToCompanyId(2);
        dispatch.setQuantity(10);
        
        // Mock the service response
        when(dispatchService.addDispatch(any(Dispatch.class))).thenReturn("ADDED");

        // Perform the POST request and verify the response
        mockMvc.perform(post("/api/addDispatch")
                .contentType("application/json")
                .content("{\"stockId\": 123, \"fromCompanyId\": 1, \"toCompanyId\": 2, \"quantity\": 10}"))
                .andExpect(status().isOk())  // Assert status is OK
                .andExpect(content().string("ADDED"));  // Assert the response body matches
    }

    @Test
    public void shouldGetDispatchById() throws Exception {
        // Given a dispatch with an ID
        Dispatch dispatch = new Dispatch();
        dispatch.setDispatchId(1);
        dispatch.setStockId(123);
        dispatch.setFromCompanyId(1);
        dispatch.setToCompanyId(2);
        dispatch.setQuantity(10);

        // Mock the service to return the dispatch by ID
        when(dispatchService.findByDispatchId(1)).thenReturn(dispatch);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/getById/{id}", 1))
                .andExpect(status().isOk())  // Assert status is OK
                .andExpect(jsonPath("$.dispatchId").value(1))  // Assert the dispatchId matches
                .andExpect(jsonPath("$.quantity").value(10));  // Assert the quantity matches
    }

    @Test
    public void shouldGetDispatchByDate() throws Exception {
        // Given a list of dispatches for a specific date
        Dispatch dispatch1 = new Dispatch();
        dispatch1.setDispatchId(1);
        dispatch1.setStockId(123);
        dispatch1.setFromCompanyId(1);
        dispatch1.setToCompanyId(2);
        dispatch1.setQuantity(10);
        dispatch1.setDateOfDispatch(LocalDate.of(2024, 11, 27));

        Dispatch dispatch2 = new Dispatch();
        dispatch2.setDispatchId(2);
        dispatch2.setStockId(124);
        dispatch2.setFromCompanyId(3);
        dispatch2.setToCompanyId(4);
        dispatch2.setQuantity(5);
        dispatch2.setDateOfDispatch(LocalDate.of(2024, 11, 27));

        List<Dispatch> dispatchList = Arrays.asList(dispatch1, dispatch2);

        // Mock the service to return the dispatches for the given date
        when(dispatchService.findByDateOfDispatch(LocalDate.of(2024, 11, 27))).thenReturn(dispatchList);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/getByDate/{date}", "2024-11-27"))
                .andExpect(status().isOk())  // Assert status is OK
                .andExpect(jsonPath("$.length()").value(2))  // Assert the size of the list
                .andExpect(jsonPath("$[0].dispatchId").value(1))  // Assert the first dispatch ID
                .andExpect(jsonPath("$[1].dispatchId").value(2));  // Assert the second dispatch ID
    }
}
