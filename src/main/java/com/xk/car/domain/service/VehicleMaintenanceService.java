package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleMaintenanceCreateCmd;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;

import java.util.UUID;

public interface VehicleMaintenanceService {


    VehicleMaintenanceBo create(VehicleMaintenanceCreateCmd cmd);

    VehicleMaintenanceBo update(UUID uuid, VehicleMaintenanceCreateCmd cmd);
}
