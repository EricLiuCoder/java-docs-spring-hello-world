package com.pcc.carrental.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarInStockDTO {

    private CarModelDTO carModel;

    private Long inStockCount;
}
