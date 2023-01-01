package com.pcc.carrental.repository;

import com.pcc.carrental.model.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalHistoryDAO extends JpaRepository<RentalHistory, Long> {
}
