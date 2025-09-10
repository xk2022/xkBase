package com.xk.car.domain.service.impl;


import com.xk.car.application.converter.VehicleConverter;
import com.xk.car.application.model.VehicleCreateCmd;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.entity.VehicleEntity;
import com.xk.car.domain.service.VehicleService;
import com.xk.car.infrastrcture.persistence.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehicleServiceImpl` - `VehicleService` 的具體實作
 * <p>
 * - 獲取最新系統設定 - 根據 UUID 查找車輛基本資訊  (`@Transactional`)
 *
 * @author hank Created on 2025/08/15.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl  implements VehicleService {

    private final VehicleConverter converter;
    private final VehicleRepository repository;
    @Override
    public VehicleBo create(VehicleCreateCmd cmd) {
        log.info("[Service] 建立車輛資訊 cmd={}", cmd);
        VehicleEntity entity = converter.toEntity(cmd);
        entity.initialize();
        var po = converter.toPo(entity);
        var saved = repository.save(po);
        return converter.toBo(saved);
    }

    @Override
    public VehicleBo update(UUID  uuid ,VehicleCreateCmd  cmd) {
        log.info("[Service] 更新車輛資訊 uuid={}, cmd={}", uuid, cmd);
        var existing = repository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("車輛不存在: " + uuid));

        existing.setLicensePlate(cmd.getLicensePlate());
        existing.setBrandModel(cmd.getBrandModel());
        existing.setMileage(cmd.getMileage());
        existing.setYear(cmd.getYear());
        existing.setStatus(cmd.getStatus());
        existing.setVehicleType(cmd.getVehicleType());
        existing.setUpdatedTime(ZonedDateTime.now());
        var saved =repository.save(existing);
        return converter.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除車輛資訊 uuid={}", uuid);
        var entity = repository.findById(uuid).orElseThrow(()->new IllegalArgumentException("此車輛資訊不存在:" + uuid));
        repository.delete(entity);
    }

    @Override
    public VehicleBo getVehicleByStatusAndLicensePlate(VehicleCreateCmd vehicleCreateCmd) {
        log.info("[Service] 查詢車輛資訊");
        var entity = repository.getVehicleByStatusAndLicensePlate(vehicleCreateCmd.getStatus(),vehicleCreateCmd.getLicensePlate());
        return converter.toBo(entity);
    }

    @Override
    public VehicleBo findByLicensePlate(String licensePlate) {
        log.info("[Service] 車牌號碼查詢車輛資訊");
        var entity = repository.findByLicensePlate(licensePlate).orElseThrow(()-> new IllegalArgumentException("查無此車輛 請先新增車輛資訊" +licensePlate));
        return converter.toBo(entity);
    }
}
