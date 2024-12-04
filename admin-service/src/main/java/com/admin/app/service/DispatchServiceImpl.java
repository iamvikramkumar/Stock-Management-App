package com.admin.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.app.client.IDispatchClient;
import com.admin.app.models.Dispatch;

@Service
public class DispatchServiceImpl implements IDispatchService {
	
	@Autowired
	private IDispatchClient dc;

	@Override
	public String addDispatch(Dispatch dispatch) {
		
		return dc.addDispatch(dispatch);
	}

	@Override
	public Dispatch getById(int id) {
		return dc.getById(id);
	}


}
