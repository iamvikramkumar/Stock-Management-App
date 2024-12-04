package com.dispatch.app.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispatch {
	@Id
	private int dispatchId;
	private int fromCompanyId;
	private int toCompanyId;
	private int stockId;
	private int quantity;
	private LocalDate dateOfDispatch;
	private double amount;
}
