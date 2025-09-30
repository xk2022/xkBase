package com.xk.car.application.usecase.impl;

import com.xk.car.application.converter.VehicleStatusLogsConverter;
import com.xk.car.application.model.VehicleStatusLogsRequest;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.application.usecase.VehicleStatusLogCreateUseCase;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.car.domain.service.VehicleService;
import com.xk.car.domain.service.VehicleStatusLogsService;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.UUID;

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
    private final VehicleStatusLogsConverter converter;
    private final VehicleStatusLogsService service;
    private final VehicleService vehicleService;
    private final UpmsUserService upmsUserService;

    @Transactional
    @Override
    public VehicleStatusLogsResponse create(VehicleStatusLogsRequest request) throws NotFoundException {
        //查詢車輛資訊
        VehicleBo vehicleBo = vehicleService.findByLicensePlate(request.getLicensePlate());
        //查詢司機
        UpmsUserBO diriver = upmsUserService.findByUuid(UUID.fromString(request.getDriverId())).orElseThrow(
                ()-> new NotFoundException("查無此司機" + request.getDriverId())
        );
        log.info("[UseCase] {}車輛狀態資訊 request={} " , request.getUuid() == null?"建立":"更新" , request);
        VehicleStatusEnum status = VehicleStatusEnum.fromString(request.getStatus());
        var cmd = converter.toTransCmd(request);
        cmd.setStatus(status);
        cmd.setDriverId(String.valueOf(diriver.getUuid()));
        cmd.setOperatorId(Integer.parseInt(request.getOperatorId()));
        cmd.setCarId(String.valueOf(vehicleBo.getUuid()));
        cmd.setVehicleType(vehicleBo.getVehicleType());
        VehicleStatusLogsBo result =(request.getUuid() == null)
                    ?service.create(cmd)
                    :service.update(vehicleBo.getUuid() , cmd);
        VehicleStatusLogsResponse response =converter.toResponseDto(result);
        response.setStatus(String.valueOf(result.getStatus()));
        response.setVehicleType(String.valueOf(result.getVehicleType()));
        response.setCreatedTime(String.valueOf(ZonedDateTime.now()));
        response.setOperatorId(String.valueOf(result.getOperatorId()));
        return response;
    }
}
