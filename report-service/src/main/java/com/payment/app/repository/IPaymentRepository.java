package com.payment.app.repository;

import com.payment.app.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;



@Repository
public interface IPaymentRepository extends CrudRepository<Payment, Integer> {
	public Payment findById(int id);
	public Payment findByDispatchId(int dispatchId);
	public List<Payment> findByDateOfDispatch(LocalDate dateOfDispatch);
	public List<Payment> findByDateOfDispatchBetween(LocalDate dateOfDispatch1,LocalDate dateOfDispatch2);
	
}
