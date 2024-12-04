package com.company.app.service;

import java.util.List;

import com.company.app.model.Company;

public interface ICompanyService {
	public String addCompany(Company company);
	public Company searchById(int id);
	public List<Company> searchAll();
	public Company searchByCompanyName(String name);
	String deteleCompany(Integer id);
	String updateCompany(Company company);

}
