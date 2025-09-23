package com.xk.car.application.usecase.impl;


import com.xk.car.application.converter.VehicleConverter;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.usecase.VehicleQueryUseCase;

import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleQueryUseCaseImpl implements VehicleQueryUseCase {

    private final VehicleService service;
    private final VehicleConverter converter;

    @Override
    public VehicleResponse getVehicleByStatusAndLicensePlate(VehicleRequest vehicleRequest) {
        VehicleBo result;
        var cmd = converter.toCreateVehicleCmd(vehicleRequest);
        result = service.getVehicleByStatusAndLicensePlate(cmd);
        if(result != null){
            VehicleResponse resp = converter.toResponseDto(result);
            return  resp;
        }
        return null;
    }
}
