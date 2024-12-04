package com.stock.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stock.app.model.Stock;
public interface IStockService {
	public String addStock(Stock stock);
	public boolean deleteStock(int id);
	public Stock searchByStockId(int stockId);
	public Stock searchByCompanyName(String companyName);
	public List<Stock> searchByStockPriceGreaterThanEqual(double stockPrice);
	public List<Stock> searchByStockPriceLessThanEqual(double stockPrice);
	public List<Stock> searchByStockPriceBetween(double stockPrice1,double stockPrice2);
	public boolean updateStock(Stock stock);
}
