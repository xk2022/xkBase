package com.xk.car.application.usecase;

import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import jakarta.validation.Valid;
import java.text.ParseException;

/**
 * 📌 VehiclePartsUsageCreateUseCase
 * -  耗損與維修項目紀錄 創建用例
 *
 * @author hank Created on 2025/08/26
 */
public interface VehiclePartsUsageCreateUseCase {

    /**
     * 創建耗損與維修項目紀錄
     * @param request
     * @return VehiclePartsUsageResponse
     * @throws ParseException
     */
    VehiclePartsUsageResponse create(@Valid VehiclePartsUsageRequest request) throws ParseException;
}
