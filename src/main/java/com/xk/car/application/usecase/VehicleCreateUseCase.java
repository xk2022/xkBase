package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.model.VehicleRequest;
import jakarta.validation.Valid;


/**
 * 📌 VehicleCreateUseCase
 * - 車輛資訊創建用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleCreateUseCase {


    /**
     * 📌 創建新車輛
     *
     * @param createDTO 創建系統的請求 DTO
     * - **確保系統名稱唯一**（若已有相同名稱的系統，可能拋出異常)
     * - **將資料儲存至資料庫，並回傳創建成功的系統資訊**
     * @return 創建成功的 `VehicleResponse`
     */
    VehicleResponse create(@Valid VehicleRequest createDTO);
}
