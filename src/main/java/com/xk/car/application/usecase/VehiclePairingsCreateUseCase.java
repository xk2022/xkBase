package com.xk.car.application.usecase;

import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.application.model.VehiclePairingsResponse;
import jakarta.validation.Valid;

public interface VehiclePairingsCreateUseCase {

    /**
     * 創建車頭或版車 資訊
     * @param request
     * @return 創建成功的 VehiclePairingsResponse
     */
    VehiclePairingsResponse create(@Valid VehiclePairingsRequest request);
}
