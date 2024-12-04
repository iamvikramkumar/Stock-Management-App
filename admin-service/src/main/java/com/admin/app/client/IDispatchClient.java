package com.admin.app.client;
import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.app.models.Dispatch;
@FeignClient(name = "dispatch-service", url = "http://localhost:8082/dispatchApp/api")
public interface IDispatchClient {

    @GetMapping("/getById/{dispatchId}")
    Dispatch getById(@PathVariable("dispatchId") int dispatchId);
    
    
    @PostMapping("/addDispatch")
    public String addDispatch(@RequestBody Dispatch dispatch);
}
