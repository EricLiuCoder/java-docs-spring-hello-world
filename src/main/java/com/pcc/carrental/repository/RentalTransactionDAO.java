package com.pcc.carrental.repository;

import com.pcc.carrental.model.RentalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalTransactionDAO extends JpaRepository<RentalTransaction, Long> {

    @Modifying
    @Query(value = "UPDATE RentalTransaction rt set rt.trxStatus =:status where rt.id =:id",
            nativeQuery = true)
    void setStatus(@Param("id") Long id, @Param("status") String status);
}
