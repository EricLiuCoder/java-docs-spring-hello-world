package com.pcc.carrental.request;

import lombok.Data;

import java.sql.Date;

@Data
public class RentalTransactionRequest {

    private Long carModelId;

    private Long userId;

    private Date rentDate;

    private Date returnDate;
}
