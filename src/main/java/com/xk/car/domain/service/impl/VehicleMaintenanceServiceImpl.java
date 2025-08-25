package com.xk.car.domain.service.impl;


import com.xk.car.application.mapper.VehicleMaintenanceMapper;
import com.xk.car.application.model.VehicleMaintenanceCreateCmd;
import com.xk.car.domain.dao.repository.VehicleMaintenanceRepository;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.model.entity.VehicleMaintenanceEntity;
import com.xk.car.domain.service.VehicleMaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * 📌 `VehicleMaintenanceServiceImpl` - `VehicleMaintenanceService` 的具體實作
 * <p>
 *
 * @author hank Created on 2025/08/15.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleMaintenanceServiceImpl implements VehicleMaintenanceService {

    private final VehicleMaintenanceMapper mapper;
    private final VehicleMaintenanceRepository repository;


    @Override
    public VehicleMaintenanceBo create(VehicleMaintenanceCreateCmd cmd) {
        log.info("[Service] 建立車輛維修資訊 cmd={}", cmd);
        VehicleMaintenanceEntity entity = mapper.toEntity(cmd);
        entity.initialize();;
        var po = mapper.toPo(entity);
        var saved = repository.save(po);

        return mapper.toBo(saved);
    }

    @Override
    public VehicleMaintenanceBo update(UUID uuid, VehicleMaintenanceCreateCmd cmd) {
        log.info("[Service] 更新車輛維修資訊 uuid={}, cmd={}", uuid, cmd);
        var existing = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("維修資訊 不存在" + uuid));
        existing.setDescription(cmd.getDescription());
        existing.setCost(cmd.getCost());
        existing.setMileageAt(cmd.getMileageAt());
        existing.setMaintenanceType(cmd.getMaintenanceType());
        existing.setReminderType(cmd.getReminderType());
        existing.setNextDueDate(cmd.getNextDueDate());
        existing.setNextDueMileage(cmd.getNextDueMileage());
        existing.setMaintenanceDate(cmd.getMaintenanceDate());
        existing.setCarId(cmd.getCarId());

        var saved = repository.save(existing);
        return mapper.toBo(saved);
    }
}
