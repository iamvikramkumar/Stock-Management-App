package com.dispatch.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dispatch.app.model.Dispatch;
import java.util.List;
import java.time.LocalDate;



@Repository
public interface IDispatchRepository extends CrudRepository<Dispatch, Integer>{
	public Dispatch findByDispatchId(int dispatchId);
	public List<Dispatch> findByDateOfDispatch(LocalDate dateOfDispatch);

}
