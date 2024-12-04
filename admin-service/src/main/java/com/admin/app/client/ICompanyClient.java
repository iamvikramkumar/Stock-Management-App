package com.admin.app.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.app.models.Company;
@FeignClient(name = "company-service", url = "http://localhost:8080/company/api")
public interface ICompanyClient {

	@GetMapping("/Companies/id/{id}")
    Company getCompanyById(@PathVariable("id") int id);
    
    @PostMapping("/Companies")
    public String addCompany(@RequestBody Company company);
    
    @PutMapping("/Companies")
    public String updateCompany(@RequestBody Company company);
    
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") Integer id);
}