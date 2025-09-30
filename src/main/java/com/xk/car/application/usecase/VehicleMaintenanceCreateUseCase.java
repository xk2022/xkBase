package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleMaintenanceRequest;
import com.xk.car.application.model.VehicleMaintenanceResponse;
import jakarta.validation.Valid;
import java.text.ParseException;

/**
 * 📌 VehicleMaintenanceCreateUseCase
 * - 車輛性能監控與維修提醒資訊創建用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleMaintenanceCreateUseCase {

    /**
     * 📌 創建
     *
     * @param createDTO 創建系統的請求 DTO
     * - **確保系統名稱唯一**（若已有相同名稱的系統，可能拋出異常）
     * - **將資料儲存至資料庫，並回傳創建成功的系統資訊**
     * @return 創建成功的 `VehicleMaintenanceResponse`
     */
    VehicleMaintenanceResponse create(@Valid VehicleMaintenanceRequest createDTO) throws ParseException;
}
