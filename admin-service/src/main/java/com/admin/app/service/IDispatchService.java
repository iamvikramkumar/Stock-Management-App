package com.admin.app.service;

import java.time.LocalDate;
import java.util.List;

import com.admin.app.models.Dispatch;

public interface IDispatchService {
	public String addDispatch(Dispatch dispatch);
	public Dispatch getById(int id);

}
