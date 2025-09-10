package com.xk.car.application.usecase.impl;


import com.xk.car.application.converter.VehiclePartsUsageConverter;
import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import com.xk.car.application.usecase.VehiclePartsUsageCreateUseCase;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;
import com.xk.car.domain.service.VehiclePartsUsageService;
import com.xk.car.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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

    private final VehiclePartsUsageConverter converter;
    private final VehiclePartsUsageService service;
    private final VehicleService vehicleService;

    @Transactional
    @Override
    public VehiclePartsUsageResponse create(VehiclePartsUsageRequest request) {
        log.info("[UseCase] {}耗損與維修項目紀錄 request={} " ,request.getUuid() ==null?"建立":"更新" ,request);
        //查詢車輛資訊
        VehicleBo vehicleBo = vehicleService.findByLicensePlate(request.getLicensePlate());

        BigDecimal cost = new BigDecimal(request.getCost());
        BigDecimal mileage = new BigDecimal(request.getMileage());

        var cmd =  converter.toCreateVehiclePartsUsageCmd(request);
        cmd.setCost(cost);
        cmd.setMileage(mileage);
        cmd.setVehicleType(vehicleBo.getVehicleType());
        cmd.setCarId(String.valueOf(vehicleBo.getUuid()));

        VehiclePartsUsageBo result =  (vehicleBo.getUuid() ==null)
                ? service.create(cmd)
                : service.update(vehicleBo.getUuid(), cmd);
        VehiclePartsUsageResponse response = converter.toResponseDto(result);
        response.setVehicleType(String.valueOf(result.getVehicleType()));
        response.setMileage(String.valueOf(result.getMileage()));
        response.setCost(String.valueOf(result.getCost()));
        response.setUsedAt(String.valueOf(result.getUsedAt()));
        response.setCreatedTime(String.valueOf(ZonedDateTime.now()));

        return response;
    }
}
