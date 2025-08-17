package com.xk.car.domain.dao.repository;

import com.xk.car.domain.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity , UUID> {


}
