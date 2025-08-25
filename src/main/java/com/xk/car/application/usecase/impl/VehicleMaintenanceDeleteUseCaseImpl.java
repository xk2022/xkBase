package com.xk.car.application.usecase.impl;

import com.xk.car.application.usecase.VehicleMaintenanceDeleteUseCase;
import com.xk.car.domain.service.VehicleMaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `VehicleMaintenanceDeleteUseCaseImpl` - 负责車輛維修提醒系统管理的刪除逻辑
 *
 *
 * @author hank  Created on 2025/08/16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleMaintenanceDeleteUseCaseImpl implements VehicleMaintenanceDeleteUseCase {

    private final VehicleMaintenanceService service;
    @Override
    public void delete(UUID uuid) {
        log.info("刪除車輛維修提醒 uuid={}", uuid);
        service.delete(uuid);
    }
}
