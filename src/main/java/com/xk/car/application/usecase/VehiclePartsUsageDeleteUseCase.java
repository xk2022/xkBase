package com.xk.car.application.usecase;

import java.util.UUID;

/**
 * 📌 VehiclePairingsDeleteUseCase
 * - 耗損與維修項目紀錄 刪除用例
 *
 * @author hank Created on  2025/08/29
 */
public interface VehiclePartsUsageDeleteUseCase {

    /**
     * 刪除耗損與維修項目紀錄
     * @param uuid
     */
    void delete(UUID uuid);
}
