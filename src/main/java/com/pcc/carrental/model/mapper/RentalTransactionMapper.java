package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.RentalTransaction;
import com.pcc.carrental.response.RentalTransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalTransactionMapper {

    RentalTransactionMapper INSTANCE = Mappers.getMapper(RentalTransactionMapper.class);

    RentalTransactionDTO toRentalTransactionDTO(RentalTransaction rentalTransaction);
}
