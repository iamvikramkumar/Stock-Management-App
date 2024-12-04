package com.payment.app.models;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private int id;
    private int stockId;
    private int dispatchId;
    private String companyName;
    private LocalDate dateOfDispatch;
    private double amount;
    private int quantity;
    private String stockName;
    private double stockPrice;
}
