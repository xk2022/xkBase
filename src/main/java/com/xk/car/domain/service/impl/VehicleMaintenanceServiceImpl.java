package com.xk.car.domain.service.impl;


import com.xk.car.application.converter.VehicleMaintenanceConverter;
import com.xk.car.application.model.VehicleMaintenanceCreateCmd;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.model.entity.VehicleMaintenanceEntity;
import com.xk.car.domain.service.VehicleMaintenanceService;
import com.xk.car.infrastrcture.persistence.model.po.VehicleMaintenancePo;
import com.xk.car.infrastrcture.persistence.repository.VehicleMaintenanceRepository;
import com.xk.common.util.XkBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
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

    private final VehicleMaintenanceConverter converter;
    private final VehicleMaintenanceRepository repository;


    @Override
    public VehicleMaintenanceBo create(VehicleMaintenanceCreateCmd cmd) {
        log.info("[Service] 建立車輛維修資訊 cmd={}", cmd);
        VehicleMaintenanceEntity entity = converter.toEntity(cmd);
        entity.initialize();
        ;
        var po = converter.toPo(entity);
        var saved = repository.save(po);

        return converter.toBo(saved);
    }

    @Override
    public VehicleMaintenanceBo update(UUID uuid, VehicleMaintenanceCreateCmd cmd) {
        log.info("[Service] 更新車輛維修資訊 uuid={}, cmd={}", uuid, cmd);
        var exsiting = repository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("查無此紀錄" + uuid)
        );
        exsiting.updateFrom(cmd);
        var saved = repository.save(exsiting);
        return converter.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("刪除車輛維修提醒資訊");
        var entity = repository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("此車輛維修資訊不存在" + uuid));
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleMaintenanceBo> getMaintenanceByCarId(UUID uuid) {
        List<VehicleMaintenancePo> pos = repository.getMaintenanceByCarId(uuid);
        return XkBeanUtils.copyListProperties(pos, VehicleMaintenanceBo::new);

    }
}
