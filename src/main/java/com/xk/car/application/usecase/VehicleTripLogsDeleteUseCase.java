package com.xk.car.application.usecase;

import java.util.UUID;

/**
 * 📌 VehicleTripLogsDeleteUseCase
 * -  里程與耗損紀錄刪除用例
 *
 * @author hank Created on 2025/08/15
 * */
public interface VehicleTripLogsDeleteUseCase {

    /**
     * 刪除里程與耗損紀錄
     * @param uuid
     */
    void delete(UUID uuid);
}
