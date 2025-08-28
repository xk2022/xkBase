package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.infrastrcture.persistence.model.po.VehicleMaintenancePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleMaintenanceRepository  extends JpaRepository<VehicleMaintenancePo, UUID> {


}
