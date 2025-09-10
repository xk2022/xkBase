package com.xk.car.domain.service.impl;

import com.xk.car.application.converter.VehiclePartsUsageConverter;
import com.xk.car.application.model.VehiclePartsUsageCmd;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;
import com.xk.car.domain.model.entity.VehiclePartsUsageEntity;
import com.xk.car.domain.service.VehiclePartsUsageService;
import com.xk.car.infrastrcture.persistence.repository.VehiclePartsUsageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehiclePartsUsageServiceImpl` - `VehiclePartsUsageService` 的具體實作
 * <p>
 * - 獲取最新系統設定 - 根據 UUID 查找車輛性能維修  (`@Transactional`)
 *
 * @author hank Created on 2025/08/27.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiclePartsUsageServiceImpl implements VehiclePartsUsageService {

    private final VehiclePartsUsageConverter converter;
    private final VehiclePartsUsageRepository repository;


    @Override
    public VehiclePartsUsageBo create(VehiclePartsUsageCmd cmd) {
        log.info("建立車輛耗損與維修項目紀錄 cmd={}" ,cmd);
        VehiclePartsUsageEntity entity =converter.toEntity(cmd);
        entity.initialize();
        var po = converter.toPo(entity);
        var saved = repository.save(po);

        return converter.toBo(saved);
    }

    @Override
    public VehiclePartsUsageBo update(UUID uuid, VehiclePartsUsageCmd cmd) {
        log.info("更新車輛耗損與維修項目紀錄 cmd={}" ,cmd);
        var existing = repository.findById(uuid).orElseThrow(()-> new IllegalArgumentException("查無此紀錄" + uuid));

        existing.setCarId(cmd.getCarId());
        existing.setPartName(cmd.getPartName());
        existing.setMileage(cmd.getMileage());
        existing.setDescription(cmd.getDescription());
        existing.setUpdatedBy(cmd.getUpdatedBy());
        existing.setUpdatedTime(ZonedDateTime.now());
        existing.setVehicleType(cmd.getVehicleType());
        var saved = repository.save(existing);
        return converter.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除車輛資訊 uuid={}", uuid);
        var entity = repository.findById(uuid).orElseThrow(()->new IllegalArgumentException("查無此紀錄:" + uuid));
        repository.delete(entity);

    }
}
