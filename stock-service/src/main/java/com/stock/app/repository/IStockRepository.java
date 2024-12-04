package com.stock.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stock.app.model.Stock;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Integer> {

	public Stock findById(int id);

	public Stock findByCompanyName(String companyName);

	public List<Stock> findByPriceGreaterThanEqual(double price);

	public List<Stock> findByPriceLessThanEqual(double price);

	public List<Stock> findByPriceBetween(double price1, double price2);
}
