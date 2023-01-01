package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.CarModel;
import com.pcc.carrental.response.CarModelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarModelMapper {

    CarModelMapper INSTANCE = Mappers.getMapper( CarModelMapper.class );

    CarModelDTO toCarModelDTO(CarModel carModel);

}
