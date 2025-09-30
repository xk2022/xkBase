package com.xk.car.application.usecase;

import com.xk.car.application.model.VehicleTripLogsRequest;
import com.xk.car.application.model.VehicleTripLogsResponse;
import jakarta.validation.Valid;

import java.text.ParseException;


/**
 * 📌 VehicleTripLogsCreateUseCase
 * -  里程與耗損紀錄 創建用例
 *
 * @author hank Created on 2025/08/15
 */
public interface VehicleTripLogsCreateUseCase {

    /**
     *  創建里程與耗損紀錄
     * @param request
     * @return VehicleTripLogsResponse
     * @throws ParseException
     */
    VehicleTripLogsResponse create(@Valid VehicleTripLogsRequest request) throws ParseException;
}
