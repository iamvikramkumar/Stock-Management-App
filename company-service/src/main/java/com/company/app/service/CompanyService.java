package com.company.app.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.model.Company;
import com.company.app.repository.ICompanyRepo;

import jakarta.transaction.Transactional;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private ICompanyRepo cRepo;
	
	

	@Override
	public Company searchById(int CompanyId) {
		
		System.out.println(this.getClass().getName() + " searchByCompanyId(CompanyId) is:" + CompanyId);
		Company company = cRepo.findById(CompanyId);
		
		if(company == null) {
			throw new NoSuchElementException("Company with ID " + CompanyId + " not found");
		}
		System.out.println(this.getClass().getName() + " received company is:" + company);
		
		return cRepo.findById(CompanyId);
	}

	@Override
	public List<Company> searchAll() {
		
		return cRepo.findAll();
	}

	@Override
	public Company searchByCompanyName(String name) {
		
		System.out.println(this.getClass().getName() + " searchByCompanyName(name) is:" + name);
		Company company = cRepo.findByName(name);
		
		if(company == null) {
			throw new NoSuchElementException("Company with Name " + name + " not found");
		}
		System.out.println(this.getClass().getName() + " received company is:" + company);
		
		return cRepo.findByName(name);
	}

	@Override
	@Transactional
	public String addCompany(Company Company) {
		Company c=cRepo.save(Company);
		if(c!=null) {
			return "Added";
		}else {
			return "Not Added";
		}
	}

	@Override
	@Transactional
	public String deteleCompany(Integer id) {
		if(cRepo.existsById(id)) {
			cRepo.deleteById(id);
			return "Deleted";
		}
		
		throw new NoSuchElementException("Company with Id " + id + " not found");
	}

	@Override
	public String updateCompany(Company company) {
		Company c=cRepo.findById(company.getId());
		
		//Company c1 = cRepo.findByName(company.getName());
		
		if(c!=null) {
			cRepo.delete(c);
			cRepo.save(company);
			return "Updated";
		}else {
			
			throw new NoSuchElementException("Company with Id " + company + " not found");
		}
		
	
	}

}
