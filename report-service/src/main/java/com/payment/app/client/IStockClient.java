package com.payment.app.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stock.app.model.Stock;

@FeignClient(name = "stock-service", url = "http://localhost:8083/stockService/api")
public interface IStockClient {

    @GetMapping("/searchById/{stockId}")
    Stock getStockById(@PathVariable("stockId") int stockId);
}
