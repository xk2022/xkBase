package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleStatusLogsRequest;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import jakarta.validation.Valid;
import org.apache.ibatis.javassist.NotFoundException;

/**
 * 📌 VehicleStatusLogCreateUseCase
 * -  車輛狀態管理 創建用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleStatusLogCreateUseCase {

    /**
     * 創建車輛狀態管理
     * @param vehicleRequest
     * @return
     * @throws NotFoundException
     */
    VehicleStatusLogsResponse create(@Valid VehicleStatusLogsRequest vehicleRequest) throws NotFoundException;
}
