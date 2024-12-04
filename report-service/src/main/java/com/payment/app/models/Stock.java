package com.payment.app.models;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Stock {
	
	private Integer id; 
	private String name; 
	private String companyName;
	private double price;
	private int quantity; 
}
