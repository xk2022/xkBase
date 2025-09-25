package com.xk.car.application.usecase;


import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import jakarta.validation.Valid;

import java.text.ParseException;

public interface VehiclePartsUsageCreateUseCase {

    VehiclePartsUsageResponse create(@Valid VehiclePartsUsageRequest request) throws ParseException;
}
