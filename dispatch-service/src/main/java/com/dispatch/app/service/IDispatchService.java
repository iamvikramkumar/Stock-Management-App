package com.dispatch.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import com.dispatch.app.model.Dispatch;


public interface IDispatchService {
	
	public Dispatch findByDispatchId(int dispatchId);
	public List<Dispatch> findByDateOfDispatch(LocalDate dateOfDispatch);
	public String addDispatch(Dispatch dispatch);

}