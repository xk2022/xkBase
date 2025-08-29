package com.xk.car.domain.service;

import com.xk.car.application.model.VehiclePartsUsageCmd;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;

import java.util.UUID;

public interface VehiclePartsUsageService {

    /**
     *
     * @param cmd
     * @return
     */
    VehiclePartsUsageBo create(VehiclePartsUsageCmd cmd);

    /**
     *
     * @param uuid
     * @param cmd
     * @return
     */
    VehiclePartsUsageBo update(UUID uuid, VehiclePartsUsageCmd cmd);

    /**
     *
     * @param uuid
     */
    void delete(UUID uuid);
}
