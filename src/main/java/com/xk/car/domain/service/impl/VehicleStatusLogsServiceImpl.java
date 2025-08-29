package com.xk.car.domain.service.impl;

import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.car.domain.service.VehicleStatusLogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 📌 `VehicleServiceImpl` - `VehicleService` 的具體實作
 * <p>
 * - 獲取最新系統設定 - 根據 UUID 查找車輛基本資訊  (`@Transactional`)
 *
 * @author hank Created on 2025/08/29.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleStatusLogsServiceImpl implements VehicleStatusLogsService {



    @Override
    public VehicleStatusLogsBo create(VehicleStatusLogsCmd cmd) {
        return null;
    }
}
