package com.xk.car.application.usecase.impl;

import com.xk.car.application.usecase.VehicleTripLogsDeleteUseCase;
import com.xk.car.domain.service.VehicleTripLogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * 📌 `VehicleTripLogsDeleteUseCaseImpl` - 负责里程紀錄的刪除逻辑
 *
 *
 * @author hank  Created on 2025/09/01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleTripLogsDeleteUseCaseImpl implements VehicleTripLogsDeleteUseCase {

    private final VehicleTripLogsService service;

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除里程紀錄" );
        service.delete(uuid);
    }
}
