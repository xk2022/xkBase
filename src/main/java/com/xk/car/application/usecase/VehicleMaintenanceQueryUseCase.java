package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleMaintenanceResponse;
import java.util.List;

/**
 * 📌 VehicleMaintenanceQueryUseCase
 * - 車輛性能監控與維修提醒資訊查詢用例
 *
 * @author hank Created on 2025/09/23
 */
public interface VehicleMaintenanceQueryUseCase {

    /**
     * 取得車輛性能監控與維修提醒資訊
     * @param licensePlate
     * @param brandModel
     * @param year
     * @param status
     * @return
     */
    List<VehicleMaintenanceResponse> getMaintenanceByCarId(String licensePlate, String brandModel, String year, String status);
}
