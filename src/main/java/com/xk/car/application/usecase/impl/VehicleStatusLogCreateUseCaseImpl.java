package com.xk.car.application.usecase.impl;

import com.xk.car.application.mapper.VehicleStatusLogsMapper;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.application.usecase.VehicleStatusLogCreateUseCase;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.car.domain.service.VehicleStatusLogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

/**
 * 📌 `VehicleStatusLogCreateUseCaseImpl` - 负责車輛狀態管理的创建逻辑
 *
 * - 处理 `VehicleStatusLogRequest` 并转换为 `VehicleStatusLog`
 * - 通过 `VehicleStatusLogService` 进行业务验证和存储
 * - 返回 `VehicleStatusLogResponse`
 *
 * @author hank  Created on 2025/08/15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleStatusLogCreateUseCaseImpl implements VehicleStatusLogCreateUseCase {
    private final VehicleStatusLogsMapper mapper;
    private final VehicleStatusLogsService service;

    @Transactional
    @Override
    public VehicleStatusLogsResponse create(VehicleRequest request) {
        VehicleStatusLogsBo result;
        VehicleStatusLogsResponse response = new VehicleStatusLogsResponse();
        if(request.getUuid() == null || StringUtil.isBlank(request.getUuid())){
            log.info("[UseCase] 建立車輛狀態資訊 request={} " , request);
            VehicleStatusEnum status = VehicleStatusEnum.fromString(request.getStatus());
            var cmd = mapper.toTransCmd(request);
            cmd.setStatus(status);

            result = service.create(cmd);
            response = mapper.toResponseDto(result);
            response.setStatus(String.valueOf(result.getStatus()));
            response.setCreatedTime(String.valueOf(ZonedDateTime.now()));
            response.setOperatorId(String.valueOf(result.getOperatorId()));
        }else {
            log.info("[UseCase] 更新車輛狀態資訊 request={} " , request);
        }


        return null;
    }
}
