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
}
