package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.CarModel;
import com.pcc.carrental.response.CarModelDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-01T10:32:43+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class CarModelMapperImpl implements CarModelMapper {

    @Override
    public CarModelDTO toCarModelDTO(CarModel carModel) {
        if ( carModel == null ) {
            return null;
        }

        CarModelDTO carModelDTO = new CarModelDTO();

        carModelDTO.setName( carModel.getName() );
        carModelDTO.setBrand( carModel.getBrand() );
        carModelDTO.setType( carModel.getType() );

        return carModelDTO;
    }
}
