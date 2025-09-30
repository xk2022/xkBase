package com.xk.car.application.usecase;

import java.util.UUID;

/**
 * 📌 VehicleStatusLogDeleteUseCase
 * -  車輛狀態管理 刪除用例
 *
 * @author hank Created on 2025/09/01
 */
public interface VehicleStatusLogDeleteUseCase {

    /***
     *
     * @param uuid
     */
    void delete(UUID uuid);
}
