package com.dispatch.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dispatch.app.model.Stock;

@FeignClient(name = "stock-service", url = "http://localhost:8083/stockService/api")
public interface IFeignClients {
	
	@GetMapping("/searchById/{id}")
	Stock getData(@PathVariable int id);

	@GetMapping("/searchByName/{companyName}")
	Stock getDataByCompanyName(@PathVariable String companyName);

	@PutMapping("/updateStock")
	boolean updateStock(@RequestBody Stock stock);

}
