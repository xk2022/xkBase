package com.xk.car.application.usecase;

import java.util.UUID;

/**
 * 📌 VehicleDeleteUseCase
 * - 車輛資訊刪除用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleDeleteUseCase {

    /**
     * 📌 刪除新車輛
     * @param uuid
     */
    void delete(UUID uuid);
}
