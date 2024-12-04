package com.admin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.app.client.ICompanyClient;
import com.admin.app.models.Company;
@Service
public class CompanyServiceImpl implements ICompanyService{
	
	@Autowired
	private ICompanyClient icc;

	@Override
	public String addCompany(Company company) {
		return icc.addCompany(company);
		
	}

	@Override
	public Company searchById(int id) {
		return icc.getCompanyById(id);
	}

	@Override
	public String updateCompany(Company company) {
		// TODO Auto-generated method stub
		return icc.updateCompany(company);
	}

	@Override
	public String deleteCompany(int id) {
		// TODO Auto-generated method stub
		return icc.deleteCompany(id);
	}

}
