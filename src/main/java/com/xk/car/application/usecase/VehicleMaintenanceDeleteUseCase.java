package com.xk.car.application.usecase;

import java.util.UUID;


/**
 * 📌 VehicleMaintenanceDeleteUseCase
 * - 車輛性能監控與維修提醒資訊刪除用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleMaintenanceDeleteUseCase {

    /**
     * 車輛性能監控與維修提醒資訊刪除
     * @param uuid
     */
    void delete(UUID uuid);
}
