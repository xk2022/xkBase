package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;

/**
 * 📌 VehicleQueryUseCase
 * - 車輛資訊查詢用例
 *
 * @author hank Created on 2025/09/10
 */
public interface VehicleQueryUseCase {

    /**
     * 取得車輛資訊
     * @param vehicleRequest
     * @return VehicleResponse
     */
    VehicleResponse getVehicleByStatusAndLicensePlate(VehicleRequest vehicleRequest);
}
