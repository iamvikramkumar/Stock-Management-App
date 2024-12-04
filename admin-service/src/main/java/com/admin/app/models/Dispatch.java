package com.admin.app.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispatch {
	
	private int dispatchId;
	private int fromCompanyId;
	private int toCompanyId;
	private int stockId;
	private int quantity;
	private LocalDate dateOfDispatch;
	private double amount;

}
