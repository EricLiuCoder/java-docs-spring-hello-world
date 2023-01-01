package com.pcc.carrental.model;

import com.pcc.carrental.model.enums.CarStatus;

import lombok.*;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "license_num", nullable = false)
    private String licenseNum;

    @Column(name = "svn", nullable = false)
    private String svn;

    @Column(name = "car_model_id", nullable = false)
    private Long carModelId;

    @Column(name = "status", nullable = false)
    private CarStatus status;

    @Column(name = "manufacture_date", nullable = false)
    private Date manufactureDate;

    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
