package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.car.infrastrcture.persistence.model.po.VehiclePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehiclePo, UUID> {

    @Query(value = """
     Select v From VehiclePo v
         WHERE v.status = :status And
         v.licensePlate LIKE CONCAT('%', :licensePlate, '%')
    """ , nativeQuery = false)
    VehiclePo getVehicleByStatusAndLicensePlate(VehicleStatusEnum status, String licensePlate);
}
