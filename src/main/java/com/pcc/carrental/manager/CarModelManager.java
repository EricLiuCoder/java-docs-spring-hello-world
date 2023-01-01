package com.pcc.carrental.manager;

import com.pcc.carrental.model.CarModel;
import com.pcc.carrental.model.mapper.CarModelMapper;
import com.pcc.carrental.repository.CarModelDAO;
import com.pcc.carrental.response.CarModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarModelManager {

    @Autowired
    private CarModelDAO carModelDAO;

    public List<CarModelDTO> findAllModels() {
        return carModelDAO.findAll()
                .stream()
                .map(CarModelMapper.INSTANCE::toCarModelDTO)
                .collect(Collectors.toList());
    }

    public CarModelDTO findByModelId(Long modelId) {
        return CarModelMapper.INSTANCE.toCarModelDTO(
                carModelDAO.findById(modelId).orElse(null));
    }

    public Long save(CarModel carModel) {
        carModelDAO.save(carModel);
        return carModel.getId();
    }

    public boolean exists(Long modelId) {
        return carModelDAO.existsById(modelId);
    }
}
