package com.xk.car.application.usecase.impl;


import com.xk.car.application.usecase.VehiclePartsUsageDeleteUseCase;
import com.xk.car.domain.service.VehiclePartsUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `VehiclePartsUsageDeleteUseCaseImpl` - 负责車輛耗損系统管理的刪除逻辑
 *
 *
 * @author hank  Created on 2025/08/16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiclePartsUsageDeleteUseCaseImpl  implements VehiclePartsUsageDeleteUseCase {

    private final VehiclePartsUsageService service;

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除車輛性能監控與維修提醒紀錄 uuid={}", uuid);
        service.delete(uuid);
    }
}
