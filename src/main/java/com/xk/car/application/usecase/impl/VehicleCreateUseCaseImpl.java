package com.xk.car.application.usecase.impl;


import com.xk.car.application.converter.VehicleConverter;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.usecase.VehicleCreateUseCase;

import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 📌 `VehicleCreateUseCaseImpl` - 负责車輛系统管理的创建逻辑
 *
 * - 处理 `VehicleRequest` 并转换为 `Vehicle`
 * - 通过 `VehicleService` 进行业务验证和存储
 * - 返回 `VehicleResponse`
 *
 * @author hank  Created on 2025/08/15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleCreateUseCaseImpl implements VehicleCreateUseCase {

    private final VehicleConverter converter;
    private final VehicleService service;

    @Transactional
    @Override
    public VehicleResponse create(VehicleRequest createDTO) {
        log.info("[UseCase] {}建立車輛資訊 request={}", createDTO.getUuid() ==null ?"建立":"更新",createDTO);
        VehicleEnum vehicleType =VehicleEnum.fromString(createDTO.getVehicleType());
        BigDecimal mileage = new BigDecimal(createDTO.getMileage());
        var cmd = converter.toCreateVehicleCmd(createDTO);
        cmd.setVehicleType(vehicleType);
        cmd.setMileage(mileage);

        VehicleBo result = (createDTO.getUuid()==null)
                ? service.create(cmd)
                : service.update(UUID.fromString(createDTO.getUuid()), cmd);

        VehicleResponse response =  converter.toResponseDto(result);
        response.setVehicleType(String.valueOf(result.getVehicleType()));
        response.setMileage(String.valueOf(result.getMileage()));
        response.setStatus(String.valueOf(result.getStatus()));
        response.setCreatedTime(String.valueOf(result.getCreatedTime()));


        return response;
    }

}
