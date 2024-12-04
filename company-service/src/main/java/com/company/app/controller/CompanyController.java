package com.company.app.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.model.Company;
import com.company.app.service.CompanyService;

@RestController
@RequestMapping("/api/Companies")
public class CompanyController {

	@Autowired
    private CompanyService CompanyService;

    // Add or update a supplier
    @PostMapping
    public String addOrUpdateCompany(@RequestBody Company Company) {
        return CompanyService.addCompany(Company);
    }
    
    @PutMapping
    public String UpdateCompany(@RequestBody Company Company) {
        return CompanyService.updateCompany(Company);
    }

    // View a supplier by ID
    @GetMapping("/id/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return CompanyService.searchById(id);
    }
    
    
    
    @GetMapping("/{CompanyName}")
    public Company getCompanyByName(@PathVariable String CompanyName) {
        return CompanyService.searchByCompanyName(CompanyName);
    } 
    
    @GetMapping
    public List<Company> getAllCompanys() {
        return CompanyService.searchAll();
    }


	
    @DeleteMapping("/{CompanyId}")
    public String deleteCompany(@PathVariable("CompanyId")Integer id) {
    	return CompanyService.deteleCompany(id);
    	
    }
    
}
