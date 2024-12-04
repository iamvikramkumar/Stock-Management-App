package com.stock.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.app.model.Stock;
import com.stock.app.service.IStockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class StockController {

	@Autowired
	private IStockService ss;

	@PostMapping("/addStock")
	public String addStock(@RequestBody Stock stock) {
		return ss.addStock(stock);

	}

	@GetMapping("/searchById/{id}")
	public Stock searchById(@PathVariable("id") Integer id) {
		log.info(this.getClass().getName() + "::searchById(id) is:" + id);
		return ss.searchByStockId(id);
	}

	@GetMapping("/searchByName/{companyName}")
	public Stock searchByCompanyName(@PathVariable("companyName") String companyName) {
		return ss.searchByCompanyName(companyName);
	}

	@GetMapping("searchByStockPriceGreater/{stockPrice}")
	public List<Stock> searchByStockPriceGreater(@PathVariable double stockPrice) {
		return ss.searchByStockPriceGreaterThanEqual(stockPrice);

	}

	@GetMapping("searchByStockPriceLess/{stockPrice}")
	public List<Stock> searchByStockPriceLess(@PathVariable double stockPrice) {
		return ss.searchByStockPriceLessThanEqual(stockPrice);

	}

	@GetMapping("searchByStockPriceBetween/{stockPrice1}/{stockPrice2}")
	public List<Stock> searchByStockPriceBetween(@PathVariable double stockPrice1, @PathVariable double stockPrice2) {
		return ss.searchByStockPriceBetween(stockPrice1, stockPrice2);

	}

	@DeleteMapping("/deleteStock/{id}")
	public boolean deleteStock(@PathVariable("id") Integer id) {
		return ss.deleteStock(id);

	}

	@PutMapping("/updateStock")
	public boolean updateStock(@RequestBody Stock stock) {
		return ss.updateStock(stock);

	}

}
