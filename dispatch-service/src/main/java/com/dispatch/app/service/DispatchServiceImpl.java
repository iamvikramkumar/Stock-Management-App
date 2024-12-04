package com.dispatch.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dispatch.app.exception.InvalidCompanyException;
import com.dispatch.app.exception.InvalidStockQuantityException;
import com.dispatch.app.feign.IFeignClientCompany;
import com.dispatch.app.feign.IFeignClients;
import com.dispatch.app.model.Company;
import com.dispatch.app.model.Dispatch;
import com.dispatch.app.model.Stock;
import com.dispatch.app.repository.IDispatchRepository;

@Service
public class DispatchServiceImpl implements IDispatchService {
	@Autowired
	private IDispatchRepository dRepository;
	@Autowired
	private IFeignClients fClients;

	@Autowired
	private IFeignClientCompany feignClientCompany;

	@Override
	public Dispatch findByDispatchId(int dispatchId) {

		return dRepository.findByDispatchId(dispatchId);
	}

	@Override
	public List<Dispatch> findByDateOfDispatch(LocalDate dateOfDispatch) {

		return dRepository.findByDateOfDispatch(dateOfDispatch);
	}

	@Override
	public String addDispatch(Dispatch dispatch) {

		System.out.println("searchBy stock id:" + dispatch.getStockId());
		Stock stock = fClients.getData(dispatch.getStockId());
		if(stock==null) {
			throw new InvalidCompanyException("Stock Does Not Exists");
		}
		System.out.println("got stock is:" + stock);
		if (stock != null) {
			Company c1 = feignClientCompany.getData(dispatch.getFromCompanyId());
			Company c2 = feignClientCompany.getData(dispatch.getToCompanyId());
			if (c1 == null || c2 == null) {
//				return "Company Does Not Exist";
				throw new InvalidCompanyException("Company Does Not Exists");
			} else if (!stock.getCompanyName().equals(c1.getName())) {
//				return "Company Does Not Match";
				throw new InvalidCompanyException("Company Does Not Match");
			} else if (dispatch.getQuantity() > stock.getQuantity()) {
//				return "Insufficient Quantity";
				throw new InvalidStockQuantityException("Insufficient Quantity");
			}
			Stock stock2 = fClients.getDataByCompanyName(c2.getName());
			stock.setQuantity(stock.getQuantity() - dispatch.getQuantity());
			stock2.setQuantity(stock2.getQuantity() + dispatch.getQuantity());

			if (fClients.updateStock(stock) && fClients.updateStock(stock2)) {
				dispatch.setDateOfDispatch(LocalDate.now());
				dispatch.setAmount(dispatch.getQuantity() * stock.getPrice());
				Dispatch d = dRepository.save(dispatch);
				if (d != null) {
					return "ADDED";
				}

			}
		}
		return "NOT ADDED";
		
	}

}
