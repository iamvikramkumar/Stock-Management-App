package com.dispatch.app.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dispatch.app.model.Dispatch;
import com.dispatch.app.service.DispatchServiceImpl;


@RestController
@RequestMapping("/api")
public class DispatchController {
	@Autowired
	private DispatchServiceImpl ps;
	
	@PostMapping("/addDispatch")
	public String addDispatch(@RequestBody Dispatch dispatch) {
		return ps.addDispatch(dispatch);
		
	}
	@GetMapping("/getById/{id}")
	public Dispatch getById(@PathVariable("id") Integer id) {
		return ps.findByDispatchId(id);
	}
	@GetMapping("/getByDate/{date}")
	public List<Dispatch> getByDate(@PathVariable("date") LocalDate date) {
		return ps.findByDateOfDispatch(date);
	}
}
