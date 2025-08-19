package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;

public interface VehicleQueryUseCase {

    VehicleResponse getVehicleByStatusAndLicensePlate(VehicleRequest vehicleRequest);
}
