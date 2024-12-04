package com.dispatch.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

	private Integer id; // 101
	private String name; // Laptop
	private String companyName; // HP
	private double price; // 890
	private int quantity; // 45

}