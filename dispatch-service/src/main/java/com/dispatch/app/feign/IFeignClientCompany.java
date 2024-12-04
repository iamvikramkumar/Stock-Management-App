package com.dispatch.app.feign;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dispatch.app.model.Company;
 
 
@FeignClient(name = "company-service", url = "http://localhost:8080/company/api/Companies/id")
public interface IFeignClientCompany {
    @GetMapping("/{id}")
    Company getData(@PathVariable int id);
}