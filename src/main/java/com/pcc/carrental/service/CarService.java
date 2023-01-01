package com.pcc.carrental.service;

import com.pcc.carrental.request.CreateCarModelRequest;
import com.pcc.carrental.request.CreateCarRequest;
import com.pcc.carrental.response.CarInStockDTO;
import com.pcc.carrental.response.CarModelDTO;

import java.util.List;

public interface CarService {

    List<CarModelDTO> findAllCarModels();

    CarInStockDTO findCarInStockByModel(Long modelId);

    Long createCarModel(CreateCarModelRequest request);

    Long createCar(CreateCarRequest request);
}
