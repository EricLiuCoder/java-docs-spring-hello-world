package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.Car;
import com.pcc.carrental.response.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    CarDTO toCarDTO(Car car);

}
