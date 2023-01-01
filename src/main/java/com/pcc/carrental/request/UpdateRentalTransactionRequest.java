package com.pcc.carrental.request;

import lombok.Data;

import java.sql.Date;

@Data
public class UpdateRentalTransactionRequest {

    private Long id;

    private String action;

    private Long carId;

    private Date orderDate;

    private Date rentDate;

    private Date returnDate;
}
