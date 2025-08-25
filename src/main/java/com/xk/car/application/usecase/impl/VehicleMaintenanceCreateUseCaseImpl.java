package com.xk.car.application.usecase.impl;


import com.xk.car.application.mapper.VehicleMaintenanceMapper;
import com.xk.car.application.model.VehicleMaintenanceRequest;
import com.xk.car.application.model.VehicleMaintenanceResponse;
import com.xk.car.application.usecase.VehicleMaintenanceCreateUseCase;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import com.xk.car.domain.service.VehicleMaintenanceService;
import com.xk.car.domain.service.VehicleService;
import com.xk.common.util.DateCoverUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;


/**
 * 📌 `VehicleMaintenanceCreateUseCaseImpl` - 负责車輛維修管理的创建逻辑
 *
 * - 处理 `VehicleMaintenanceRequest` 并转换为 `VehicleMaintenance`
 * - 通过 `VehicleMaintenanceService` 进行业务验证和存储
 * - 返回 `VehicleMaintenanceResponse`
 *
 * @author hank  Created on 2025/08/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleMaintenanceCreateUseCaseImpl implements VehicleMaintenanceCreateUseCase {

    private final VehicleMaintenanceMapper mapper;
    private final VehicleMaintenanceService service;
    private final DateCoverUtils dateCoverUtils;

    @Override
    public VehicleMaintenanceResponse create(VehicleMaintenanceRequest createDTO) throws ParseException {
        VehicleMaintenanceBo result;
        if(createDTO.getUuid() == null){
            log.info("[UseCase] 建立車輛維修資訊request={}", createDTO);
            MaintenanceTypeEnum maintenanceType = MaintenanceTypeEnum.fromString(createDTO.getMaintenanceType());
            ReminderTypeEnum reminderTypeEnum = ReminderTypeEnum.fromString(createDTO.getReminderType());
            Date maintenanceDate =dateCoverUtils.StringCoverToDate(createDTO.getMaintenanceDate());
            Date nextDueDate  = dateCoverUtils.StringCoverToDate(createDTO.getNextDueDate());
            var cmd = mapper.toCreateVehicleMaintenanceCmd(createDTO);
            cmd.setMaintenanceType(maintenanceType);
            cmd.setReminderType(reminderTypeEnum);
            cmd.setMaintenanceDate(maintenanceDate);
            cmd.setNextDueDate(nextDueDate);

            result = service.create(cmd);


        }else{
            log.info("[UseCase] 更新車輛維修資訊request={}", createDTO);
            var cmd = mapper.toCreateVehicleMaintenanceCmd(createDTO);
            result = service.update(createDTO.getUuid(),cmd);

        }
        return mapper.toResponseDto(result);
    }
}
