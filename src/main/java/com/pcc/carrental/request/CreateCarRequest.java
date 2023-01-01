package com.pcc.carrental.request;

import com.pcc.carrental.model.enums.CarStatus;
import lombok.Data;

import java.sql.Date;

@Data
public class CreateCarRequest {

    private String licenseNum;

    private String svn;

    private Long carModelId;

    private Date manufactureDate;

    private Date purchaseDate;
}
