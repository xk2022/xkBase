package com.xk.car.application.usecase;

import java.util.UUID;

/**
 * 📌 VehiclePairingsDeleteUseCase
 * - 車頭與板車管理 刪除用例
 *
 * @author hank Created on  2025/09/01
 */
public interface VehiclePairingsDeleteUseCase {

    /**
     * 刪除耗損與維修項目紀錄
     * @param uuid
     */
    void delete(UUID uuid);
}
