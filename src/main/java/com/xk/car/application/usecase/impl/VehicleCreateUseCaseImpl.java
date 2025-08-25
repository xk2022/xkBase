package com.xk.car.application.usecase.impl;


import com.xk.car.application.mapper.VehicleMapper;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.usecase.VehicleCreateUseCase;

import com.xk.car.domain.model.bo.VihicleBo;
import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.car.domain.service.VehicleService;
import com.xk.common.util.DateCoverUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

/**
 * 📌 `VehicleCreateUseCaseImpl` - 负责車輛系统管理的创建逻辑
 *
 * - 处理 `VehicleRequest` 并转换为 `Vehicle`
 * - 通过 `VehicleService` 进行业务验证和存储
 * - 返回 `VehicleResponse`
 *
 * @author hank  Created on 2025/08/15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleCreateUseCaseImpl implements VehicleCreateUseCase {

    private final VehicleMapper mapper;
    private final VehicleService service;

    @Transactional
    @Override
    public VehicleResponse create(VehicleRequest createDTO) {
        VihicleBo result;
        if (createDTO.getUuid() == null) {
            log.info("[UseCase] 建立車輛資訊 request={}", createDTO);
            VehicleEnum vehicleType =VehicleEnum.fromString(createDTO.getVehicleType());
            VehicleStatusEnum status = VehicleStatusEnum.fromString(createDTO.getStatus());
            var cmd = mapper.toCreateVehicleCmd(createDTO);
            cmd.setVehicleType(vehicleType);
            cmd.setStatus(status);

            result = service.create(cmd);
        } else {
            log.info("[UseCase] 更新車輛資訊 uuid={}, request={}", createDTO.getUuid(), createDTO);
            var cmd = mapper.toUpdateCmd(createDTO);
            result = service.update(createDTO.getUuid(), cmd);
        }
        return mapper.toResponseDto(result);
    }

}
