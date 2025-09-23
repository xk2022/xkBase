package com.xk.car.application.usecase;


import com.xk.car.application.model.VehicleMaintenanceResponse;

import java.util.List;

public interface VehicleMaintenanceQueryUseCase {

    List<VehicleMaintenanceResponse> getMaintenanceByCarId(String licensePlate, String brandModel, String year, String status);
}
