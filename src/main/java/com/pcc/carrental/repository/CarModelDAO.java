package com.pcc.carrental.repository;

import com.pcc.carrental.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelDAO extends JpaRepository<CarModel, Long> {
}
