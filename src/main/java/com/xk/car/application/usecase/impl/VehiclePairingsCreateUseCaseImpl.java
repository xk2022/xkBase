package com.xk.car.application.usecase.impl;

import com.xk.car.application.mapper.VehiclePairingsMapper;
import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.application.model.VehiclePairingsResponse;
import com.xk.car.application.usecase.VehiclePairingsCreateUseCase;
import com.xk.car.domain.model.bo.VehiclePairingsBo;
import com.xk.car.domain.service.VehiclePairingsService;
import com.xk.common.util.DateCoverUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehiclePairingsCreateUseCaseImpl` - 负责車頭與板車管理的创建逻辑
 *
 * - 处理 `VehiclePairingsRequest` 并转换为 `VehiclePairings`
 * - 通过 `VehiclePairingsService` 进行业务验证和存储
 * - 返回 `VehiclePairingsResponse`
 *
 * @author hank  Created on 2025/08/31
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VehiclePairingsCreateUseCaseImpl implements VehiclePairingsCreateUseCase {

    private final VehiclePairingsMapper mapper;
    private final DateCoverUtils dateCoverUtils;
    private final VehiclePairingsService service;

    @Transactional
    @Override
    public VehiclePairingsResponse create(VehiclePairingsRequest request) {
        log.info("[UseCase] {}車頭或版車資訊 request={}",
                request.getUuid() == null ? "建立" : "更新", request);

        var cmd = mapper.toCreateVehiclePairingsCmd(request);
        ZonedDateTime bindTime = dateCoverUtils.parseZdt(request.getBindTime());
        ZonedDateTime unbindTime = dateCoverUtils.parseZdt(request.getUnbindTime());
        cmd.setBindTime(bindTime);
        cmd.setUnbindTime(unbindTime);

        VehiclePairingsBo result = (request.getUuid() == null)
                ? service.create(cmd)
                : service.update(UUID.fromString(request.getUuid()), cmd);

        VehiclePairingsResponse response = mapper.toResponse(result);
        response.setBindTime(bindTime != null ? bindTime.toString() : "");
        response.setUnbindTime(unbindTime != null ? unbindTime.toString() : "");

        return response;
    }
}
