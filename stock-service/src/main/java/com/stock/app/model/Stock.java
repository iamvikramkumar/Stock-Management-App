package com.stock.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
	@Id
	private Integer id; // 101
	private String name; // Laptop
	private String companyName; // HP
	private double price; // 890
	private int quantity; // 45
}
