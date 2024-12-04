package com.admin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.app.models.Stock;
import com.admin.app.service.IStockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableFeignClients
@RequestMapping("/admin/stock")
@Slf4j
public class AdminStockController {
	@Autowired
	private IStockService iss;

	@GetMapping("/getStockById/{id}")
	public Stock getStockById(@PathVariable("id") int id) {
		return iss.searchById(id);
	}

	@PostMapping("/addStock")
	public String addStock(@RequestBody Stock stock) {
		log.info("Reached:{}", stock);
		return iss.addStock(stock);
	}

	@PutMapping("/updateStock")
	public String updateStock(@RequestBody Stock stock) {
		return iss.updateStock(stock);
	}

	@DeleteMapping("/deleteStock/{id}")
	public String deleteStock(@PathVariable("id") int id) {
		return iss.deleteStock(id);
	}
}