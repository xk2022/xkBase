package com.xk.car.application.usecase.impl;

import com.xk.car.application.model.VehicleMaintenanceResponse;
import com.xk.car.application.usecase.VehicleMaintenanceQueryUseCase;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.service.VehicleMaintenanceService;
import com.xk.car.domain.service.VehicleService;
import com.xk.common.util.VehicleStatusConverterUtils;
import com.xk.common.util.XkBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 📌 `VehicleMaintenanceQueryUseCaseImpl` - 负责車輛維修提醒系统管理的查詢逻辑
 *
 *
 * @author hank  Created on 2025/09/23
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class VehicleMaintenanceQueryUseCaseImpl implements VehicleMaintenanceQueryUseCase {

    private final VehicleStatusConverterUtils vehicleStatusConverterUtils;
    private final VehicleService service;
    private final VehicleMaintenanceService maintenanceService;

    @Override
    public List<VehicleMaintenanceResponse> getMaintenanceByCarId(String licensePlate, String brandModel, String year, String status) {
       String statusStr = vehicleStatusConverterUtils.getVehicleStatus(status);
        VehicleBo vehicleBo = service.findByLicensePlateAndBrandModelAndYearAndStatus(licensePlate,brandModel,year,statusStr);
        List<VehicleMaintenanceBo> boList = maintenanceService.getMaintenanceByCarId(vehicleBo.getUuid());
        return XkBeanUtils.copyListProperties(boList ,VehicleMaintenanceResponse::new);
    }
}
