package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.car.infrastrcture.persistence.model.po.VehiclePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehiclePo, UUID> {

    @Query(value = """
     Select v From vehicle v
         WHERE 1=1
         And v.status = :status
         And v.license_plate LIKE CONCAT('%', :licensePlate, '%')
         And v.deleted = '0'
    """ , nativeQuery = true)
    VehiclePo getVehicleByStatusAndLicensePlate(String status, String licensePlate);


    @Query(value = """
             Select * From vehicle v
              WHERE 1=1
              And v.license_plate = :licensePlate
              And v.deleted = '0'
            """ , nativeQuery = true)
    Optional<VehiclePo> findByLicensePlate(String licensePlate);

    @Query(value = """
            Select * From vehicle v
            WHERE 1=1
            And v.brand_model = :brandModel
            And v.license_plate LIKE CONCAT('%', :licensePlate, '%')
            And v.year = :year
            And v.status = :statusStr
            And v.deleted = '0'
            """,nativeQuery = true)
    Optional<VehiclePo> findByLicensePlateAndBrandModelAndYearAndStatus(String licensePlate, String brandModel, String year, String statusStr);
}
