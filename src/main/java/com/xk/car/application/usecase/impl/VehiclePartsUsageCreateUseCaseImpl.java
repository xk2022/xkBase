package com.xk.car.application.usecase.impl;


import com.xk.car.application.mapper.VehiclePartsUsageMapper;
import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import com.xk.car.application.usecase.VehiclePartsUsageCreateUseCase;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;
import com.xk.car.domain.service.VehiclePartsUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehicleMaintenanceCreateUseCaseImpl` - 负责車輛維修管理的创建逻辑
 *
 * - 处理 `VehiclePartsUsageRequest` 并转换为 `VehiclePartsUsage`
 * - 通过 `VehiclePartsUsageService` 进行业务验证和存储
 * - 返回 `VehiclePartsUsageResponse`
 *
 * @author hank  Created on 2025/08/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VehiclePartsUsageCreateUseCaseImpl implements VehiclePartsUsageCreateUseCase {

    private final VehiclePartsUsageMapper mapper;
    private final VehiclePartsUsageService service;

    @Transactional
    @Override
    public VehiclePartsUsageResponse create(VehiclePartsUsageRequest request) {
        VehiclePartsUsageBo result;
        VehiclePartsUsageResponse response = new VehiclePartsUsageResponse();

        if (request.getUuid() ==null){
            log.info("建立耗損與維修項目紀錄 request={}" , request);
            BigDecimal cost = new BigDecimal(request.getCost());
            BigDecimal mileage = new BigDecimal(request.getMileage());

            var cmd =  mapper.toCreateVehiclePartsUsageCmd(request);
            cmd.setCost(cost);
            cmd.setMileage(mileage);

            result = service.create(cmd);
            response =  mapper.toResponseDto(result);
            response.setMileage(String.valueOf(result.getMileage()));
            response.setCost(String.valueOf(result.getCost()));
            response.setUsedAt(String.valueOf(result.getUsedAt()));
            response.setCreatedTime(String.valueOf(ZonedDateTime.now()));


        }else{
            log.info("更新耗損與維修項目紀錄 request={}" , request);
            var cmd = mapper.toUpdateCmd(request);
            result = service.update(UUID.fromString(request.getUuid()) , cmd);
            response =  mapper.toResponseDto(result);
            response.setUpdatedTime(String.valueOf(ZonedDateTime.now()));
            response.setMileage(String.valueOf(result.getMileage()));
            response.setCost(String.valueOf(result.getCost()));
            response.setUsedAt(String.valueOf(result.getUsedAt()));
        }


        return response;
    }
}
