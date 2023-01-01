package com.pcc.carrental.manager;

import com.pcc.carrental.repository.RentalHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalHistoryManager {

    @Autowired
    private RentalHistoryDAO rentalHistoryDAO;
}
