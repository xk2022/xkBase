package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.infrastrcture.persistence.model.po.VehiclePartsUsagePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehiclePartsUsageRepository  extends JpaRepository<VehiclePartsUsagePo , UUID> {

}
