package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 📌 `VehicleStatusLogsService`
 * <p>
 * - 獲取最新系統設定 - 車輛狀態基本資訊  (`@Transactional`)
 *
 * @author hank Created on 2025/08/29
 */
public interface VehicleStatusLogsService {

    VehicleStatusLogsBo create(VehicleStatusLogsCmd cmd);
}
