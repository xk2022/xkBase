package com.xk.car.domain.service.impl;

import com.xk.car.application.converter.VehicleStatusLogsConverter;
import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.car.domain.model.entity.VehicleStatusLogsEntity;
import com.xk.car.domain.service.VehicleStatusLogsService;
import com.xk.car.infrastrcture.persistence.repository.VehicleStatusLogsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehicleServiceImpl` - `VehicleService` 的具體實作
 * <p>
 * - 獲取最新系統設定 - 根據 UUID 查找車輛狀態資訊  (`@Transactional`)
 *
 * @author hank Created on 2025/08/29.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleStatusLogsServiceImpl implements VehicleStatusLogsService {
    private final VehicleStatusLogsConverter converter;
    private final VehicleStatusLogsRepository repository;


    @Override
    public VehicleStatusLogsBo create(VehicleStatusLogsCmd cmd) {
        log.info("[Service] 建立車輛狀態資訊 cmd={}", cmd);
        VehicleStatusLogsEntity entity = converter.toEntity(cmd);
        entity.initialize();
        var po = converter.toPo(entity);
        var saved = repository.save(po);
        return converter.toBo(saved);
    }

    @Override
    public VehicleStatusLogsBo update(UUID uuid, VehicleStatusLogsCmd cmd) {
        log.info("[Service] 更新車輛狀態資訊 cmd={}", cmd);
        var existing = repository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("查無車輛狀態資訊 " + uuid)
        );
        existing.updateForm(cmd);
        var saved = repository.save(existing);
        return converter.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除車輛狀態資訊 uuid={}", uuid);
        var entity = repository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("查無車輛狀態資訊" + uuid)
        );
        repository.delete(entity);
    }

}
