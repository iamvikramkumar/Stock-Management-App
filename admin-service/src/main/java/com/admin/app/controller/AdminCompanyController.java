package com.admin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.app.models.Company;
import com.admin.app.service.ICompanyService;

@RestController
@EnableFeignClients
@RequestMapping("/admin/company")
public class AdminCompanyController {
	@Autowired
	private ICompanyService ics;
	
	@GetMapping("/getCompanyById/{id}")
	public Company getCompanyById(@PathVariable("id") int id1) {
		return ics.searchById(id1);
	}
	@PostMapping("/addCompany")
	public String addCompany(@RequestBody Company company) {
	
		return ics.addCompany(company);
	}
	@PutMapping("/updateCompany")
	public String updateCompany(@RequestBody Company company) {
		return ics.updateCompany(company);
	}
	@DeleteMapping("/deleteCompany/{id}")
	public String deleteCompany(@PathVariable("id") int id) {
		return ics.deleteCompany(id);
	}

}
