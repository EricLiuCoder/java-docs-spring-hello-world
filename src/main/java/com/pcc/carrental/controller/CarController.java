package com.pcc.carrental.controller;

import com.pcc.carrental.request.CreateCarModelRequest;
import com.pcc.carrental.request.CreateCarRequest;
import com.pcc.carrental.response.CarInStockDTO;
import com.pcc.carrental.response.CarModelDTO;
import com.pcc.carrental.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "APIs for car model")
@Controller("/api/v1/car")
public class CarController {

    @Autowired
    private CarService carService;

    @ApiOperation(value = "create a new car model")
    @PostMapping("/models")
    ResponseEntity<String> createCarModel(CreateCarModelRequest request) {
        Long modelId = carService.createCarModel(request);
        return ResponseEntity.ok(String.valueOf(modelId));
    }

    @ApiOperation(value = "get all car models")
    @GetMapping("/models")
    ResponseEntity<List<CarModelDTO>> getCarModels() {
        List<CarModelDTO> carModelDTOs = carService.findAllCarModels();
        return ResponseEntity.ok(carModelDTOs);
    }

    @ApiOperation(value = "get in stock of a car model")
    @GetMapping("/models/{model_id}/in_stock")
    ResponseEntity<CarInStockDTO> getCarInStock(@PathVariable(name = "model_id") Long modelId) {
        CarInStockDTO carInStockDTO = carService.findCarInStockByModel(modelId);
        return ResponseEntity.ok(carInStockDTO);
    }

    @ApiOperation(value = "create a new car in stock")
    @PostMapping("/in_stock")
    ResponseEntity<String> createCar(CreateCarRequest request) {
        Long carId = carService.createCar(request);
        return ResponseEntity.ok(String.valueOf(carId));
    }

}
