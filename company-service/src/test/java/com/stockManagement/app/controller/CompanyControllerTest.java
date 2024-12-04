package com.stockManagement.app.controller;

	import static org.mockito.Mockito.when;
	import static org.mockito.Mockito.verify;
	import static org.mockito.ArgumentMatchers.any;
	
	import java.util.Arrays;
	import java.util.List;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.junit.jupiter.MockitoExtension;
	import org.springframework.http.MediaType;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.setup.MockMvcBuilders;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.company.app.controller.CompanyController;
import com.company.app.model.Company;
	import com.company.app.service.CompanyService;
	import com.fasterxml.jackson.databind.ObjectMapper;

	@ExtendWith(MockitoExtension.class)
		public class CompanyControllerTest {
	    private MockMvc mockMvc;

	    @Mock
	    private CompanyService companyService;

	    @InjectMocks
	    private CompanyController companyController;

	    private ObjectMapper objectMapper = new ObjectMapper();

	    @BeforeEach
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
	    }

	    @Test
	    public void testAddOrUpdateCompany() throws Exception {
	        Company company = new Company();
	        when(companyService.addCompany(any(Company.class))).thenReturn("Company added/updated successfully");

	        mockMvc.perform(MockMvcRequestBuilders.post("/api/Companies")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(company)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("Company added/updated successfully"));

	        verify(companyService).addCompany(any(Company.class));
	    }

	    @Test
	    public void testUpdateCompany() throws Exception {
	        Company company = new Company();
	        when(companyService.updateCompany(any(Company.class))).thenReturn("Company updated successfully");

	        mockMvc.perform(MockMvcRequestBuilders.put("/api/Companies")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(company)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("Company updated successfully"));

	        verify(companyService).updateCompany(any(Company.class));
	    }

	    @Test
	    public void testGetCompanyById() throws Exception {
	        Company company = new Company();
	        when(companyService.searchById(1)).thenReturn(company);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/Companies/id/1"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(company)));

	        verify(companyService).searchById(1);
	    }

	    @Test
	    public void testGetCompanyByName() throws Exception {
	        Company company = new Company();
	        when(companyService.searchByCompanyName("TestCompany")).thenReturn(company);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/Companies/TestCompany"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(company)));

	        verify(companyService).searchByCompanyName("TestCompany");
	    }

	    @Test
	    public void testGetAllCompanies() throws Exception {
	        List<Company> companies = Arrays.asList(new Company(), new Company());
	        when(companyService.searchAll()).thenReturn(companies);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/Companies"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(companies)));

	        verify(companyService).searchAll();
	    }

	    @Test
	    public void testDeleteCompany() throws Exception {
	        when(companyService.deteleCompany(1)).thenReturn("Company deleted successfully");

	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Companies/1"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("Company deleted successfully"));

	        verify(companyService).deteleCompany(1);
	    }
	}

	
	

