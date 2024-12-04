package com.admin.app.service;

import com.admin.app.models.Stock;

public interface IStockService {
	public String addStock(Stock stock);
	public Stock searchById(int id);
	public String updateStock(Stock stock);
	public String deleteStock(int id);

}
