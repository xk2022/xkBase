package com.xk.car.infrastrcture.persistence.repository;

import com.xk.car.infrastrcture.persistence.model.po.VehicleTripLogsPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *
 * @author hank create 2025/09/01
 */
@Repository
public interface VehicleTripLogsRepository extends JpaRepository<VehicleTripLogsPo, UUID> {
}
