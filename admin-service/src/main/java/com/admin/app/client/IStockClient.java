package com.admin.app.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.app.models.Stock;
@FeignClient(name = "stock-service", url = "http://localhost:8083/stockService/api")
public interface IStockClient {

    @GetMapping("/searchById/{id}")
    public Stock getStockById(@PathVariable("id") int id);
    
    @PostMapping("/addStock")
	public String addStock(@RequestBody Stock stock);
    
    @DeleteMapping("/deleteStock/{id}")
	public boolean deleteStock(@PathVariable("id") Integer id);
    
    @PutMapping("/updateStock")
	public boolean updateStock(@RequestBody Stock stock);
    
    
}
