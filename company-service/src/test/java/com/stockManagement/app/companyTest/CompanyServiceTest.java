package com.stockManagement.app.companyTest;

import com.company.app.model.Company;
import com.company.app.repository.ICompanyRepo;
import com.company.app.service.CompanyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private ICompanyRepo companyRepo;

    private Company company;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        company = new Company();
        company.setId(1);
        company.setName("Test Company");
    }

    // Test the searchById method
    @Test
    public void testSearchById_success() {
        // Arrange
        when(companyRepo.findById(1)).thenReturn(company);

        // Act
        Company result = companyService.searchById(1);

        // Assert
        assertNotNull(result);
        assertEquals(company.getId(), result.getId());
        assertEquals(company.getName(), result.getName());
    }

    @Test
    public void testSearchById_notFound() {
        // Arrange
        when(companyRepo.findById(1)).thenReturn(null);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> companyService.searchById(1));
    }

    // Test the searchAll method
    @Test
    public void testSearchAll() {
        // Arrange
        List<Company> companies = List.of(company);
        when(companyRepo.findAll()).thenReturn(companies);

        // Act
        List<Company> result = companyService.searchAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(company.getId(), result.get(0).getId());
    }

    // Test the searchByCompanyName method
    @Test
    public void testSearchByCompanyName_success() {
        // Arrange
        when(companyRepo.findByName("Test Company")).thenReturn(company);

        // Act
        Company result = companyService.searchByCompanyName("Test Company");

        // Assert
        assertNotNull(result);
        assertEquals(company.getName(), result.getName());
    }

    @Test
    public void testSearchByCompanyName_notFound() {
        // Arrange
        when(companyRepo.findByName("Nonexistent Company")).thenReturn(null);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> companyService.searchByCompanyName("Nonexistent Company"));
    }

    // Test the addCompany method
    @Test
    public void testAddCompany_success() {
        // Arrange
        when(companyRepo.save(company)).thenReturn(company);

        // Act
        String result = companyService.addCompany(company);

        // Assert
        assertEquals("Added", result);
    }

    @Test
    public void testAddCompany_failure() {
        // Arrange
        when(companyRepo.save(company)).thenReturn(null);

        // Act
        String result = companyService.addCompany(company);

        // Assert
        assertEquals("Not Added", result);
    }

    // Test the deleteCompany method
    @Test
    public void testDeleteCompany_success() {
        // Arrange
        when(companyRepo.existsById(1)).thenReturn(true);

        // Act
        String result = companyService.deteleCompany(1);

        // Assert
        assertEquals("Deleted", result);
        verify(companyRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCompany_notFound() {
        // Arrange
        when(companyRepo.existsById(1)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> companyService.deteleCompany(1));
    }

    // Test the updateCompany method
    @Test
    public void testUpdateCompany_success() {
        // Arrange
        when(companyRepo.findById(1)).thenReturn(company);
        when(companyRepo.save(company)).thenReturn(company);

        // Act
        String result = companyService.updateCompany(company);

        // Assert
        assertEquals("Updated", result);
    }

    @Test
    public void testUpdateCompany_notFound() {
        // Arrange
        when(companyRepo.findById(1)).thenReturn(null);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> companyService.updateCompany(company));
    }
}
