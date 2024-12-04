package com.company.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.model.Company;

@Repository
public interface ICompanyRepo extends CrudRepository<Company, Integer> {


	public Company findById(int id);

	public List<Company> findAll();

	public Company findByName(String name);

}
