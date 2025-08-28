package com.xk.car.application.usecase;


import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import jakarta.validation.Valid;

public interface VehiclePartsUsageCreateUseCase {

    VehiclePartsUsageResponse create(@Valid VehiclePartsUsageRequest request);
}
