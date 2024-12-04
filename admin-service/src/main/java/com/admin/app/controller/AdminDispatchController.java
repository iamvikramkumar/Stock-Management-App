package com.admin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.app.models.Dispatch;
import com.admin.app.service.IDispatchService;

@RestController
@EnableFeignClients
@RequestMapping("/admin/dispatch")
public class AdminDispatchController {
	@Autowired
	private IDispatchService ds;
	
	@GetMapping("/getById/{id}")
	Dispatch getById(@PathVariable("id") int id) {
		return ds.getById(id);
	}
	
	@PostMapping("/addDispatch")
	String addDispatch(@RequestBody Dispatch dispatch) {
		return ds.addDispatch(dispatch);
	}
		
}
