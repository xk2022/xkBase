package com.xk.car.domain.dao.repository;

import com.xk.car.domain.model.entity.VehicleEntity;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity , UUID> {

    @Query(value = """
     Select v From VehicleEntity v
         WHERE v.status = :status And  
         v.licensePlate LIKE CONCAT('%', :licensePlate, '%')
    """ , nativeQuery = false)
    VehicleEntity getVehicleByStatusAndLicensePlate(VehicleStatusEnum status, String licensePlate);
}
