package com.pcc.carrental.service;

import com.pcc.carrental.exception.CarRentalException;
import com.pcc.carrental.manager.CarManager;
import com.pcc.carrental.manager.CarModelManager;
import com.pcc.carrental.model.Car;
import com.pcc.carrental.model.CarModel;
import com.pcc.carrental.model.enums.CarStatus;
import com.pcc.carrental.model.mapper.CarModelMapper;
import com.pcc.carrental.repository.CarDAO;
import com.pcc.carrental.repository.CarModelDAO;
import com.pcc.carrental.request.CreateCarModelRequest;
import com.pcc.carrental.request.CreateCarRequest;
import com.pcc.carrental.response.CarInStockDTO;
import com.pcc.carrental.response.CarModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarManager carManager;
    @Autowired
    private CarModelManager carModelManager;

    @Override
    public Long createCarModel(CreateCarModelRequest request) {
        CarModel carModel = new CarModel();
        carModel.setBrand(request.getBrand());
        carModel.setName(request.getName());
        carModel.setType(request.getType());
        return carModelManager.save(carModel);
    }

    @Override
    public Long createCar(CreateCarRequest request) {
        checkModel(request.getCarModelId());
        Car car = new Car();
        car.setCarModelId(request.getCarModelId());
        car.setLicenseNum(request.getLicenseNum());
        car.setSvn(request.getSvn());
        car.setStatus(CarStatus.AVAILABLE);
        car.setManufactureDate(request.getManufactureDate());
        car.setPurchaseDate(request.getPurchaseDate());
        return carManager.save(car);
    }

    @Override
    public List<CarModelDTO> findAllCarModels() {
        return carModelManager.findAllModels();
    }

    @Override
    public CarInStockDTO findCarInStockByModel(Long modelId) {
        CarModelDTO carModelDTO = carModelManager.findByModelId(modelId);
        if (carModelDTO == null) {
            throw CarRentalException.carModelNotFound();
        }
        long inStock = carManager.countByModelId(modelId);
        return CarInStockDTO.builder()
                .carModel(carModelDTO)
                .inStockCount(inStock)
                .build();
    }

    private void checkModel(Long modelId) {
        if (!carModelManager.exists(modelId)) {
            throw CarRentalException.carModelNotFound();
        }
    }
}
