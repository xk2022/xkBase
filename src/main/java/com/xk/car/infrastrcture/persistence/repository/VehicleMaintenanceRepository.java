package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.infrastrcture.persistence.model.po.VehicleMaintenancePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleMaintenanceRepository  extends JpaRepository<VehicleMaintenancePo, UUID> {


    @Query(value = """
            Select * From vehicle_maintenance
            Where 1=1
            And uuid = :uuid
            """,nativeQuery = true)
    List<VehicleMaintenancePo> getMaintenanceByCarId(UUID uuid);
}
