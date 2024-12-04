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

import com.admin.app.controller.AdminDispatchController;
import com.admin.app.models.Dispatch;
import com.admin.app.service.IDispatchService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminDispatchControllerTest.class)
public class AdminDispatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDispatchService dispatchService;

    @InjectMocks
    private AdminDispatchController adminDispatchController;

    private Dispatch dispatch;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminDispatchController).build();
        dispatch = new Dispatch();
        dispatch.setStockId(1);
    }

    @Test
    void testGetById() throws Exception {
        when(dispatchService.getById(1)).thenReturn(dispatch);

        mockMvc.perform(get("/admin/dispatch/getById/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddDispatch() throws Exception {
        when(dispatchService.addDispatch(dispatch)).thenReturn("Dispatch added successfully");

        mockMvc.perform(post("/admin/dispatch/addDispatch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dispatch)))
                .andExpect(status().isOk());
    }
}