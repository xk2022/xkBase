package com.xk.car.application.usecase.impl;

import com.xk.car.application.usecase.VehicleStatusLogDeleteUseCase;
import com.xk.car.domain.service.VehicleStatusLogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `VehicleStatusLogDeleteUseCaseImpl` - 负责車輛狀態系统管理的刪除逻辑
 *
 *
 * @author hank  Created on 2025/09/01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleStatusLogDeleteUseCaseImpl implements VehicleStatusLogDeleteUseCase {
    private final VehicleStatusLogsService service;

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除車輛狀態資訊 uuid={}",uuid);
        service.delete(uuid);
    }
}
