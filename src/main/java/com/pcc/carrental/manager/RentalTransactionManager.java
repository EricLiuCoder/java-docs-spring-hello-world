package com.pcc.carrental.manager;

import com.pcc.carrental.model.RentalTransaction;
import com.pcc.carrental.repository.RentalTransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalTransactionManager {

    @Autowired
    private RentalTransactionDAO rentalTransactionDAO;

    public void save(RentalTransaction rentalTransaction) {
        rentalTransactionDAO.save(rentalTransaction);
    }

    public boolean exists(Long id) {
        return rentalTransactionDAO.existsById(id);
    }

    public RentalTransaction findById(Long id) {
        return rentalTransactionDAO.findById(id).orElse(null);
    }

    public void setTransactionStatus(Long id, String status) {
        rentalTransactionDAO.setStatus(id, status);
    }
}
