package com.pcc.carrental.service;

import com.pcc.carrental.exception.CarRentalException;
import com.pcc.carrental.manager.CarManager;
import com.pcc.carrental.manager.RentalHistoryManager;
import com.pcc.carrental.manager.RentalTransactionManager;
import com.pcc.carrental.model.RentalTransaction;
import com.pcc.carrental.model.enums.RentalTransactionStatus;
import com.pcc.carrental.model.mapper.RentalTransactionMapper;
import com.pcc.carrental.request.RentalTransactionRequest;
import com.pcc.carrental.request.UpdateRentalTransactionRequest;
import com.pcc.carrental.response.RentalTransactionDTO;
import com.pcc.carrental.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RentalServiceImpl implements RentalService{

    @Autowired
    private CarManager carManager;
    @Autowired
    private RentalTransactionManager rentalTransactionManager;
    @Autowired
    private RentalHistoryManager rentalHistoryManager;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long newTransaction(RentalTransactionRequest request) {
        return checkAndBook(request);
    }

    @Override
    public RentalTransactionDTO getTransaction(Long trxId) {
        checkTransaction(trxId);
        return RentalTransactionMapper.INSTANCE
                .toRentalTransactionDTO(rentalTransactionManager.findById(trxId));
    }

    private Long checkAndBook(RentalTransactionRequest request) {
        Long modelId = request.getCarModelId();
        if (!carManager.checkCarInStockAvailable(modelId)) {
            throw CarRentalException.noCarAvailable();
        }
        Long carId = carManager.bookOne(modelId);
        if (carId == null) {
            throw CarRentalException.noCarAvailable();
        }
        RentalTransaction rentalTransaction = new RentalTransaction();
        rentalTransaction.setCarId(carId);
        rentalTransaction.setUserId(request.getUserId());
        rentalTransaction.setTrxStatus(RentalTransactionStatus.CREATED.name());
        rentalTransaction.setOrderDate(DateTimeUtil.newDate());
        rentalTransaction.setRentDate(request.getRentDate());
        rentalTransaction.setReturnDate(request.getReturnDate());
        rentalTransactionManager.save(rentalTransaction);
        return rentalTransaction.getId();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateTransaction(UpdateRentalTransactionRequest request) {
        Long trxId = request.getId();
        checkTransaction(trxId);
        RentalTransaction rentalTransaction = rentalTransactionManager.findById(trxId);
        rentalTransaction.setCarId(request.getCarId());
        rentalTransaction.setRentDate(request.getRentDate());
        rentalTransaction.setReturnDate(request.getReturnDate());

        String action = request.getAction();
        RentalTransactionStatus targetStatus;
        switch (action) {
            case "PAY": targetStatus = RentalTransactionStatus.PAID; break;
            case "PICKUP": targetStatus = RentalTransactionStatus.RENTED; break;
            case "RETURN": targetStatus = RentalTransactionStatus.RETURNED; break;
            case "CANCEL": targetStatus = RentalTransactionStatus.CANCELLED; break;
            default: throw CarRentalException.illegalAction();
        }
        rentalTransaction.setTrxStatus(targetStatus.name());

        if (RentalTransactionStatus.RETURNED.equals(targetStatus)) {
            rentalHistoryManager.save(rentalTransaction);
        }

        rentalTransactionManager.save(rentalTransaction);
    }

    private void checkTransaction(Long id) {
        if (!rentalTransactionManager.exists(id)) {
            throw CarRentalException.rentalTransactionNotFound();
        }
    }
}
