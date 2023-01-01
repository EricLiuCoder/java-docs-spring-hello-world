package com.pcc.carrental.model.mapper;

import com.pcc.carrental.model.RentalTransaction;
import com.pcc.carrental.response.RentalTransactionDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-01T10:32:43+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class RentalTransactionMapperImpl implements RentalTransactionMapper {

    @Override
    public RentalTransactionDTO toRentalTransactionDTO(RentalTransaction rentalTransaction) {
        if ( rentalTransaction == null ) {
            return null;
        }

        RentalTransactionDTO rentalTransactionDTO = new RentalTransactionDTO();

        rentalTransactionDTO.setCarId( rentalTransaction.getCarId() );
        rentalTransactionDTO.setTrxStatus( rentalTransaction.getTrxStatus() );
        rentalTransactionDTO.setOrderDate( rentalTransaction.getOrderDate() );
        rentalTransactionDTO.setRentDate( rentalTransaction.getRentDate() );
        rentalTransactionDTO.setReturnDate( rentalTransaction.getReturnDate() );

        return rentalTransactionDTO;
    }
}
