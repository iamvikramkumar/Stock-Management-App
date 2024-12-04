package com.admin.app.service;

import com.admin.app.models.Company;

public interface ICompanyService {
	
	public String addCompany(Company company);
	public Company searchById(int id);
	public String updateCompany(Company company);
	public String deleteCompany(int id);

}
