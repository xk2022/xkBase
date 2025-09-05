package com.xk.car.application.usecase.impl;


import com.xk.car.application.mapper.VehicleMaintenanceMapper;
import com.xk.car.application.model.VehicleMaintenanceRequest;
import com.xk.car.application.model.VehicleMaintenanceResponse;
import com.xk.car.application.usecase.VehicleMaintenanceCreateUseCase;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.model.enums.MaintenanceTypeEnum;
import com.xk.car.domain.model.enums.ReminderTypeEnum;
import com.xk.car.domain.service.VehicleMaintenanceService;
import com.xk.car.domain.service.VehicleService;
import com.xk.common.util.DateCoverUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


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
    private final VehicleService vehicleService;

    @Transactional
    @Override
    public VehicleMaintenanceResponse create(VehicleMaintenanceRequest createDTO) throws ParseException {
        log.info("[UseCase] {}車輛維修資訊request={}", createDTO.getUuid() == null?"建立":"更新",  createDTO);
        //查詢車輛資訊
        VehicleBo vehicleBo = vehicleService.findByLicensePlate(createDTO.getLicensePlate());

        MaintenanceTypeEnum maintenanceType = MaintenanceTypeEnum.fromString(createDTO.getMaintenanceType());
        ReminderTypeEnum reminderTypeEnum = ReminderTypeEnum.fromString(createDTO.getReminderType());
        Date maintenanceDate =dateCoverUtils.StringCoverToDate(createDTO.getMaintenanceDate());
        Date nextDueDate  = dateCoverUtils.StringCoverToDate(createDTO.getNextDueDate());
        BigDecimal  mileageAt = new BigDecimal(createDTO.getMileageAt());
        var cmd = mapper.toCreateVehicleMaintenanceCmd(createDTO);
        cmd.setVehicleType(vehicleBo.getVehicleType());
        cmd.setCarId(String.valueOf(vehicleBo.getUuid()));
        cmd.setMaintenanceType(maintenanceType);
        cmd.setReminderType(reminderTypeEnum);
        cmd.setMaintenanceDate(maintenanceDate);
        cmd.setNextDueDate(nextDueDate);
        cmd.setMileageAt(mileageAt);

        VehicleMaintenanceBo result = (createDTO.getUuid() == null)
                ? service.create(cmd)
                :service.update(UUID.fromString(createDTO.getUuid()),cmd);


        VehicleMaintenanceResponse response =  mapper.toResponseDto(result);
        response.setMaintenanceType(String.valueOf(result.getMaintenanceType()));
        response.setVehicleType(String.valueOf(result.getVehicleType()));
        response.setMileageAt(String.valueOf(result.getMileageAt()));
        response.setMaintenanceDate(String.valueOf(result.getMaintenanceDate()));
        response.setNextDueDate(String.valueOf(result.getNextDueDate()));
        response.setCost(String.valueOf(result.getCost()));
        response.setReminderType(String.valueOf(result.getReminderType()));
        response.setNextDueMileage(String.valueOf(result.getNextDueMileage()));
        response.setCreatedTime(String.valueOf(ZonedDateTime.now()));

        return response;
    }
}
