package com.pcc.carrental.response;

import com.pcc.carrental.model.enums.CarBrand;
import com.pcc.carrental.model.enums.CarType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarModelDTO {

    private String name;

    private CarBrand brand;

    private CarType type;
}
