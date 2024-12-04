package com.admin.app.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

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
