package com.xk.car.application.usecase.impl;


import com.xk.car.application.mapper.VehicleMapper;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.usecase.VehicleQueryUseCase;
import com.xk.car.domain.model.bo.VihicleBo;
import com.xk.car.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleQueryUseCaseImpl implements VehicleQueryUseCase {

    private final VehicleService service;
    private final VehicleMapper mapper;

    @Override
    public VehicleResponse getVehicleByStatusAndLicensePlate(VehicleRequest vehicleRequest) {
        VihicleBo result;
        var cmd = mapper.toCreateVehicleCmd(vehicleRequest);
        result = service.getVehicleByStatusAndLicensePlate(cmd);
        if(result != null){
            return  mapper.toResponseDto(result);
        }
        return null;
    }
}
