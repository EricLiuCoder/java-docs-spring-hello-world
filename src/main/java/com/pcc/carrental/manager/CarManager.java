package com.pcc.carrental.manager;

import com.pcc.carrental.model.Car;
import com.pcc.carrental.model.enums.CarStatus;
import com.pcc.carrental.repository.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarManager {

    @Autowired
    private CarDAO carDAO;

    public Long save(Car car) {
        carDAO.save(car);
        return car.getId();
    }

    public Long countByModelId(Long modelId) {
        Car example = new Car();
        example.setCarModelId(modelId);
        return carDAO.count(Example.of(example));
    }

    public boolean checkCarInStockAvailable(Long modelId) {
        return countByModelId(modelId) > 0;
    }

    public Long bookOne(Long modelId) {
        Car example = new Car();
        example.setCarModelId(modelId);
        example.setStatus(CarStatus.AVAILABLE);
        Optional<Car> carOpt = carDAO.findOne(Example.of(example));
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            car.setStatus(CarStatus.BOOKED);
            carDAO.save(car);
            return car.getId();
        }
        return null;
    }
}
