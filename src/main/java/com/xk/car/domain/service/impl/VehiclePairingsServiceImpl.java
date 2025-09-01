package com.xk.car.domain.service.impl;

import com.xk.car.application.mapper.VehiclePairingsMapper;
import com.xk.car.application.model.VehiclePairingsCmd;
import com.xk.car.domain.model.bo.VehiclePairingsBo;
import com.xk.car.domain.model.entity.VehiclePairingsEntity;
import com.xk.car.domain.service.VehiclePairingsService;
import com.xk.car.infrastrcture.persistence.repository.VehiclePairingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * 📌 `VehiclePairingsServiceImpl` - `VehiclePairingsService` 的具體實作
 * <p>
 * - 獲取最新系統設定 -   (`@Transactional`)
 *
 * @author hank Created on 2025/08/31.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiclePairingsServiceImpl implements VehiclePairingsService {

    private final VehiclePairingsMapper mapper;
    private final VehiclePairingsRepository repository;


    @Override
    public VehiclePairingsBo create(VehiclePairingsCmd cmd) {
        log.info("[Service] 建立 車頭與版車管理資訊 cmd={}" , cmd);
        VehiclePairingsEntity entity = mapper.toEntity(cmd);
        entity.initialize();
        var po = mapper.toPo(entity);
        var saved = repository.save(po);

        return mapper.toBo(saved);
    }

    @Override
    public VehiclePairingsBo update(UUID uuid, VehiclePairingsCmd cmd) {
        log.info("[Service] 更新車頭與版車管理資訊 uuid={}, cmd={}" ,uuid, cmd);
        var existing =repository.findById(uuid).orElseThrow(
                ()-> new IllegalArgumentException("車頭與版車管理資訊不存在" +uuid)
        );
        existing.setHeadId(cmd.getHeadId());
        existing.setTrailerId(cmd.getTrailerId());
        existing.setBindTime(cmd.getBindTime());
        existing.setUnbindTime(cmd.getUnbindTime());
        existing.setBindBy(cmd.getBindBy());
        existing.setUnbindBy(cmd.getUnbindBy());
        existing.setNote(cmd.getNote());
        existing.setUpdatedTime(ZonedDateTime.now());

        return mapper.toBo(existing);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除車頭與板車資訊 uuid={}", uuid);
        var entity = repository.findById(uuid).orElseThrow(()->new IllegalArgumentException("查無此紀錄:" + uuid));
        repository.delete(entity);
    }


}
