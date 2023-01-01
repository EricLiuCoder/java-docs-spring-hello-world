package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.Car;
import com.pcc.carrental.response.CarDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-01T20:04:40+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDTO toCarDTO(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDTO carDTO = new CarDTO();

        return carDTO;
    }
}
