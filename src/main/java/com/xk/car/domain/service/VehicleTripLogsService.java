package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleTripLogsCmd;
import com.xk.car.domain.model.bo.VehicleTripLogsBo;

import java.util.UUID;

/**
 *
 *   @author hank Created on 2025/09/01.
 */
public interface VehicleTripLogsService {
    VehicleTripLogsBo create(VehicleTripLogsCmd cmd);

    VehicleTripLogsBo update(UUID uuid, VehicleTripLogsCmd cmd);

    void delete(UUID uuid);
}
