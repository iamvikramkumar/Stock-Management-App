package com.payment.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.payment.app.models.Dispatch;

@FeignClient(name = "dispatch-service", url = "http://localhost:8082/dispatchApp/api")
public interface IDispatchClient {

    @GetMapping("/getById/{dispatchId}")
    Dispatch getById(@PathVariable("dispatchId") int dispatchId);
}
