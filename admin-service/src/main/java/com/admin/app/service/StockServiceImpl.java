package com.admin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.app.client.IStockClient;
import com.admin.app.models.Stock;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements IStockService{
	@Autowired
	private IStockClient isc;

	@Override
	public String addStock(Stock stock) {
		log.info("Reached "+stock);
		
		return isc.addStock(stock);
	}

	@Override
	public Stock searchById(int id) {
		
		return isc.getStockById(id);
	}

	@Override
	public String updateStock(Stock stock) {
		
		if(isc.updateStock(stock)) {
			return "Updated ";
		}
		return "Not Updated";
	}

	@Override
	public String deleteStock(int id) {
		
		
		if(isc.deleteStock(id)) {
			return "Deleted ";
		}
		return "Not Deleted";
	}

}
