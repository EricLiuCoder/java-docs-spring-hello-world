package com.pcc.carrental;

import com.pcc.carrental.exception.CarRentalException;
import com.pcc.carrental.model.enums.CarBrand;
import com.pcc.carrental.model.enums.CarType;
import com.pcc.carrental.request.*;
import com.pcc.carrental.response.CarInStockDTO;
import com.pcc.carrental.response.CarModelDTO;
import com.pcc.carrental.response.RentalTransactionDTO;
import com.pcc.carrental.response.UserDTO;
import com.pcc.carrental.service.CarService;
import com.pcc.carrental.service.RentalService;
import com.pcc.carrental.service.UserService;
import com.pcc.carrental.util.DateTimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
public class ApplicationTests {

    @Autowired
    private CarService carService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;

    @Test
    public void testCreateAndGetAnUser(){
        CreateUserRequest request = new CreateUserRequest();
        request.setAlias("xxxx");
        request.setFirstName("Demo");
        request.setLastName("Bean");
        request.setEmailAddress("demo.bean@example");
        request.setMobilePhoneNum("3128729321");
        Long userId = userService.createUser(request);
        UserDTO userDTO = userService.findUser(userId);
        Assertions.assertEquals("xxxx", userDTO.getAlias());
        Assertions.assertEquals("Demo", userDTO.getFirstName());
        Assertions.assertEquals("Bean", userDTO.getLastName());
        Assertions.assertEquals("demo.bean@example", userDTO.getEmailAddress());
        Assertions.assertEquals("3128729321", userDTO.getMobilePhoneNum());
    }

    @Test
    public void testCreateACarModelAndFindAll() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        carService.createCarModel(request);
        List<CarModelDTO> carModelDTOList = carService.findAllCarModels();
        CarModelDTO carModelDTO = carModelDTOList.get(0);
        Assertions.assertEquals("BMW", carModelDTO.getBrand().name());
        Assertions.assertEquals("325Li", carModelDTO.getName());
        Assertions.assertEquals("SEDAN", carModelDTO.getType().name());
    }

    @Test
    public void testCreateACarInStock() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        Long modelId = carService.createCarModel(request);

        Date date = DateTimeUtil.newDate();
        CreateCarRequest createCarRequest = new CreateCarRequest();
        createCarRequest.setCarModelId(modelId);
        createCarRequest.setLicenseNum("AF2393N");
        createCarRequest.setSvn("NXTK293872038M8N");
        createCarRequest.setPurchaseDate(date);
        createCarRequest.setManufactureDate(date);

        carService.createCar(createCarRequest);
        CarInStockDTO inStockDTO = carService.findCarInStockByModel(modelId);
        Assertions.assertEquals("BMW", inStockDTO.getCarModel().getBrand().name());
        Assertions.assertEquals("SEDAN", inStockDTO.getCarModel().getType().name());
        Assertions.assertEquals(1L, inStockDTO.getInStockCount());
    }

    @Test
    public void testFindUnExistedModel() {
        CarRentalException exception = Assertions.assertThrows(
                CarRentalException.class, () -> carService.findCarInStockByModel(-1L));
        Assertions.assertEquals("Car model not found", exception.getMessage());
    }

    @Test
    public void testStartANewRentalTransactionWithNoCarAvailable() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        Long modelId = carService.createCarModel(request);

        RentalTransactionRequest request2 = new RentalTransactionRequest();
        Date rentDate = DateTimeUtil.newDate();
        Date returnDate = DateTimeUtil.newDate();
        request2.setUserId(1L);
        request2.setCarModelId(modelId);
        request2.setRentDate(rentDate);
        request2.setReturnDate(returnDate);

        CarRentalException exception = Assertions.assertThrows(
                CarRentalException.class, () -> rentalService.newTransaction(request2));
        Assertions.assertEquals("No car available", exception.getMessage());
    }

    @Test
    public void testStartANewRentalTransaction() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        Long modelId = carService.createCarModel(request);

        Date date = DateTimeUtil.newDate();
        CreateCarRequest request2 = new CreateCarRequest();
        request2.setCarModelId(modelId);
        request2.setLicenseNum("AF2393N");
        request2.setSvn("NXTK293872038M8N");
        request2.setPurchaseDate(date);
        request2.setManufactureDate(date);
        Long carId = carService.createCar(request2);

        RentalTransactionRequest request3 = new RentalTransactionRequest();
        Date rentDate = DateTimeUtil.newDate();
        Date returnDate = DateTimeUtil.newDate();
        request3.setUserId(1L);
        request3.setCarModelId(modelId);
        request3.setRentDate(rentDate);
        request3.setReturnDate(returnDate);

        Long trxId = rentalService.newTransaction(request3);

        RentalTransactionDTO transactionDTO = rentalService.getTransaction(trxId);
        Assertions.assertEquals("CREATED", transactionDTO.getTrxStatus());
        Assertions.assertEquals(carId, transactionDTO.getCarId());
        Assertions.assertEquals(rentDate, transactionDTO.getRentDate());
        Assertions.assertEquals(returnDate, transactionDTO.getReturnDate());
    }

    @Test
    public void testFindInExistedTransaction() {
        CarRentalException exception = Assertions.assertThrows(
                CarRentalException.class, () -> rentalService.getTransaction(-1L));
        Assertions.assertEquals("Rental transaction not found", exception.getMessage());
    }

    @Test
    public void testUpdateATransaction() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        Long modelId = carService.createCarModel(request);

        Date date = DateTimeUtil.newDate();
        CreateCarRequest request2 = new CreateCarRequest();
        request2.setCarModelId(modelId);
        request2.setLicenseNum("AF2393N");
        request2.setSvn("NXTK293872038M8N");
        request2.setPurchaseDate(date);
        request2.setManufactureDate(date);
        Long carId = carService.createCar(request2);

        RentalTransactionRequest request3 = new RentalTransactionRequest();
        Date rentDate = DateTimeUtil.newDate();
        Date returnDate = DateTimeUtil.newDate();
        request3.setUserId(1L);
        request3.setCarModelId(modelId);
        request3.setRentDate(rentDate);
        request3.setReturnDate(returnDate);

        Long trxId = rentalService.newTransaction(request3);

        UpdateRentalTransactionRequest request4 = new UpdateRentalTransactionRequest();
        request4.setId(trxId);
        request4.setCarId(carId);
        request4.setAction("PAY");
        request4.setOrderDate(DateTimeUtil.newDate());
        request4.setRentDate(rentDate);
        request4.setReturnDate(returnDate);

        rentalService.updateTransaction(request4);
        Assertions.assertEquals("PAID", rentalService.getTransaction(trxId).getTrxStatus());
    }

    @Test
    public void testUpdateATransactionWithIllegalAction() {
        CreateCarModelRequest request = new CreateCarModelRequest();
        request.setBrand(CarBrand.BMW);
        request.setName("325Li");
        request.setType(CarType.SEDAN);
        Long modelId = carService.createCarModel(request);

        Date date = DateTimeUtil.newDate();
        CreateCarRequest request2 = new CreateCarRequest();
        request2.setCarModelId(modelId);
        request2.setLicenseNum("AF2393N");
        request2.setSvn("NXTK293872038M8N");
        request2.setPurchaseDate(date);
        request2.setManufactureDate(date);
        Long carId = carService.createCar(request2);

        RentalTransactionRequest request3 = new RentalTransactionRequest();
        Date rentDate = DateTimeUtil.newDate();
        Date returnDate = DateTimeUtil.newDate();
        request3.setUserId(1L);
        request3.setCarModelId(modelId);
        request3.setRentDate(rentDate);
        request3.setReturnDate(returnDate);

        Long trxId = rentalService.newTransaction(request3);

        UpdateRentalTransactionRequest request4 = new UpdateRentalTransactionRequest();
        request4.setId(trxId);
        request4.setCarId(carId);
        request4.setAction("DO");
        request4.setOrderDate(DateTimeUtil.newDate());
        request4.setRentDate(rentDate);
        request4.setReturnDate(returnDate);

        CarRentalException exception = Assertions.assertThrows(
                CarRentalException.class, () -> rentalService.updateTransaction(request4));
        Assertions.assertEquals("Illegal action", exception.getMessage());
    }
}
