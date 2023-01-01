package com.pcc.carrental.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RentalTransactionDTO {

    private Long carId;

    private String trxStatus;

    private Date orderDate;

    private Date rentDate;

    private Date returnDate;
}
