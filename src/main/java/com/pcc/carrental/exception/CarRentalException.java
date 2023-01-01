package com.pcc.carrental.exception;

public class CarRentalException extends RuntimeException {

    public static final String NO_CAR_AVAILABLE = "No car available";
    public static final String CAR_MODEL_NOT_FOUND = "Car model not found";
    public static final String RENTAL_TRANSACTION_NOT_FOUND = "Rental transaction not found";
    public static final String ILLEGAL_ACTION = "Illegal action";

    public CarRentalException(String error) {
        super(error);
    }

    public static CarRentalException noCarAvailable() {
        return new CarRentalException(NO_CAR_AVAILABLE);
    }

    public static CarRentalException rentalTransactionNotFound() {
        return new CarRentalException(RENTAL_TRANSACTION_NOT_FOUND);
    }

    public static CarRentalException carModelNotFound() {
        return new CarRentalException(CAR_MODEL_NOT_FOUND);
    }

    public static CarRentalException illegalAction() {
        return new CarRentalException(ILLEGAL_ACTION);
    }
}
