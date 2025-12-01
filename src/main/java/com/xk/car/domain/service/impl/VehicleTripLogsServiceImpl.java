package com.xk.car.domain.service.impl;

import com.xk.car.application.converter.VehicleTripLogsConverter;
import com.xk.car.application.model.VehicleTripLogsCmd;
import com.xk.car.domain.model.bo.VehicleTripLogsBo;
import com.xk.car.domain.model.entity.VehicleTripLogsEntity;
import com.xk.car.domain.service.VehicleTripLogsService;
import com.xk.car.infrastrcture.persistence.repository.VehicleTripLogsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `VehicleTripLogsServiceImpl` - `VehicleTripLogsService` 的具體實作
 * <p>
 * - 獲取最新系統設定 - 里程紀錄  (`@Transactional`)
 *
 * @author hank Created on 2025/09/01.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleTripLogsServiceImpl implements VehicleTripLogsService {
    private final VehicleTripLogsConverter converter;
    private final VehicleTripLogsRepository repository;

    @Override
    public VehicleTripLogsBo create(VehicleTripLogsCmd cmd) {
        log.info("建立車輛耗損與維修項目紀錄 cmd={}" ,cmd);
        VehicleTripLogsEntity entity =converter.toEntity(cmd);
        entity.initialize();
        var po = converter.toPo(entity);
        var saved = repository.save(po);

        return converter.toBo(saved);
    }

    @Override
    public VehicleTripLogsBo update(UUID uuid, VehicleTripLogsCmd cmd) {
        log.info("更新車輛耗損與維修項目紀錄 cmd={}" ,cmd);
        var existing = repository.findById(uuid).orElseThrow(()-> new IllegalArgumentException("查無此紀錄" + uuid));
        existing.updateForm(cmd);
        var saved = repository.save(existing);
        return converter.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除里程紀錄資訊 uuid={}", uuid);
        var entity = repository.findById(uuid).orElseThrow(()->new IllegalArgumentException("查無此紀錄:" + uuid));
        repository.delete(entity);
    }

}
