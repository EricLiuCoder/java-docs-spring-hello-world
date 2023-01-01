package com.pcc.carrental.request;

import com.pcc.carrental.model.enums.CarBrand;
import com.pcc.carrental.model.enums.CarType;
import lombok.Data;

@Data
public class CreateCarModelRequest {

    private String name;

    private CarBrand brand;

    private CarType type;
}
