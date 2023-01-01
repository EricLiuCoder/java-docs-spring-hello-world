package com.pcc.carrental.manager;

import com.pcc.carrental.model.RentalHistory;
import com.pcc.carrental.model.RentalTransaction;
import com.pcc.carrental.repository.RentalHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalHistoryManager {

    @Autowired
    private RentalHistoryDAO rentalHistoryDAO;

    public void save(RentalTransaction transaction) {
        RentalHistory rentalHistory = new RentalHistory();
        rentalHistory.setCarId(transaction.getCarId());
        rentalHistory.setRentDate(transaction.getRentDate());
        rentalHistory.setReturnDate(transaction.getReturnDate());
        rentalHistory.setUserId(transaction.getUserId());
        rentalHistoryDAO.save(rentalHistory);
    }
}
