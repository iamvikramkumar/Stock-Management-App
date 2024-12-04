package com.stock.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.app.exception.InvalidRangeException;
import com.stock.app.exception.StockAlreadyExistsException;
import com.stock.app.exception.StockNotFoundException;
import com.stock.app.model.Stock;
import com.stock.app.repository.IStockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockService implements IStockService {

	@Autowired
	private IStockRepository sr;

	@Override
	public Stock searchByStockId(int stockId) {

		System.out.println(this.getClass().getName() + " searchByStockId(stockId) is:" + stockId);
		Stock stock = sr.findById(stockId);
		if(stock == null) {
			throw new StockNotFoundException("Stock with ID " + stockId + " not found");
		}
		System.out.println(this.getClass().getName() + " received stock is:" + stock);
		return stock;

	}

	@Override
	public List<Stock> searchByStockPriceGreaterThanEqual(double stockPrice) {
        if (stockPrice < 0) {
            throw new IllegalArgumentException("Stock price cannot be negative");
        }
		return sr.findByPriceGreaterThanEqual(stockPrice);
	}

	@Override
	public List<Stock> searchByStockPriceLessThanEqual(double stockPrice) {

        if (stockPrice < 0) {
            throw new IllegalArgumentException("Stock price cannot be negative");
        }
		return sr.findByPriceLessThanEqual(stockPrice);
	}

	@Override
	public List<Stock> searchByStockPriceBetween(double stockPrice1, double stockPrice2) {
		
        if (stockPrice1 < 0 || stockPrice2 < 0) {
            throw new IllegalArgumentException("Stock prices cannot be negative");
        }
        if (stockPrice1 > stockPrice2) {
            throw new InvalidRangeException("The first stock price cannot be greater than the second stock price");
        }
        
		return sr.findByPriceBetween(stockPrice1, stockPrice2);
	}

	@Override
	@Transactional
	public String addStock(Stock stock) {
//		Stock s = sr.findById(stock.getId()).get();
        
//            if (s == null) {
            	Stock res = sr.save(stock);
                return "Stock Added";
//            } else {
//            	throw new StockAlreadyExistsException("Stock with this ID or name already exists");
//            }
       

	}

	@Override
	@Transactional
	public boolean deleteStock(int id) {
        if (sr.existsById(id)) {
            sr.deleteById(id);
            return true;
        } else {
            throw new StockNotFoundException("Stock with ID " + id + " not found");
        }
	}

	@Override
	@Transactional
	public boolean updateStock(Stock stock) {
        if (sr.findById(stock.getId()) == null) {
            throw new StockNotFoundException("Stock with ID " + stock.getId() + " not found");
        }
		if (sr.findById(stock.getId()) != null) {
			sr.deleteById(stock.getId());
			sr.save(stock);
			return true;
		}
		return false;

	}

	@Override
	public Stock searchByCompanyName(String companyName) {
		Stock stock = sr.findByCompanyName(companyName);
        if (stock == null) {
            throw new StockNotFoundException("Stock with company name " + companyName + " not found");
        }
		return sr.findByCompanyName(companyName);

	}

}
