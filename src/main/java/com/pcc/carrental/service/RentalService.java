package com.pcc.carrental.service;

import com.pcc.carrental.request.RentalTransactionRequest;
import com.pcc.carrental.request.UpdateRentalTransactionRequest;
import com.pcc.carrental.response.RentalTransactionDTO;

public interface RentalService {

    Long newTransaction(RentalTransactionRequest request);

    RentalTransactionDTO getTransaction(Long trxId);

    void updateTransaction(UpdateRentalTransactionRequest request);
}
