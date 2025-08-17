package com.xk.car.application.usecase.impl;


import com.xk.car.application.usecase.VehicleDeleteUseCase;
import com.xk.car.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `VehicleDeleteUseCaseImpl` - 负责車輛系统管理的刪除逻辑
 *
 *
 * @author hank  Created on 2025/08/16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleDeleteUseCaseImpl implements VehicleDeleteUseCase {

    private final VehicleService service;

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除車輛資訊 uuid={}", uuid);
        service.delete(uuid);
    }
}
