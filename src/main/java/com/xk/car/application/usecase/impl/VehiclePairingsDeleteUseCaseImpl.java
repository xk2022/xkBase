package com.xk.car.application.usecase.impl;

import com.xk.car.application.usecase.VehiclePairingsDeleteUseCase;
import com.xk.car.domain.service.VehiclePairingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `VehiclePairingsDeleteUseCaseImpl` - 负责車頭版車系统管理的刪除逻辑
 *
 *
 * @author hank  Created on 2025/09/01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiclePairingsDeleteUseCaseImpl implements VehiclePairingsDeleteUseCase {

    private final VehiclePairingsService service;

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除車頭版車資訊 uuid={}", uuid);
        service.delete(uuid);
    }
}
