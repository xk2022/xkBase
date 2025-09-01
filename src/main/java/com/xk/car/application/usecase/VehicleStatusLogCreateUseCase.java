package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleStatusLogsRequest;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import jakarta.validation.Valid;

public interface VehicleStatusLogCreateUseCase {


    VehicleStatusLogsResponse create(@Valid VehicleStatusLogsRequest vehicleRequest);
}
