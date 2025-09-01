package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleTripLogsRequest;
import com.xk.car.application.model.VehicleTripLogsResponse;
import jakarta.validation.Valid;

import java.text.ParseException;

public interface VehicleTripLogsCreateUseCase {

    VehicleTripLogsResponse create(@Valid VehicleTripLogsRequest request) throws ParseException;
}
