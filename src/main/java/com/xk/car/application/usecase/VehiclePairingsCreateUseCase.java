package com.xk.car.application.usecase;

import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.application.model.VehiclePairingsResponse;
import jakarta.validation.Valid;

/**
 * 📌 VehiclePairingsCreateUseCase
 * - 車頭與板車管理 創建用例
 *
 * @author hank Created on 2025/08/31
 */
public interface VehiclePairingsCreateUseCase {

    /**
     * 耗損與維修項目紀錄資訊
     * @param request
     * @return 創建成功的 VehiclePairingsResponse
     */
    VehiclePairingsResponse create(@Valid VehiclePairingsRequest request);
}
