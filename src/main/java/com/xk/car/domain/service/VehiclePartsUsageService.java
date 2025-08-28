package com.xk.car.domain.service;

import com.xk.car.application.model.VehiclePartsUsageCmd;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;

import java.util.UUID;

public interface VehiclePartsUsageService {

    VehiclePartsUsageBo create(VehiclePartsUsageCmd cmd);

    VehiclePartsUsageBo update(UUID uuid, VehiclePartsUsageCmd cmd);

    void delete(UUID uuid);
}
