package com.xk.car.controller;


import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.application.model.VehiclePairingsResponse;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.usecase.VehiclePairingsCreateUseCase;
import com.xk.common.base.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 📌 `VehiclePairingsController` - 負責管理 車頭與板車管理 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/08/13.
 */
@Tag(name = "VehiclePairings Management", description = "車頭與板車管理")
@RestController
@RequestMapping("/api/car/vehiclepairings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehiclePairingsController {

    private final VehiclePairingsCreateUseCase vehiclePairingsCreateUseCase;


    @Operation(summary = "新增或更新車頭或版車資訊" ,description = "新增或更新車頭或版車資訊")
    @PostMapping("/save")
    public BaseResult<VehiclePairingsResponse> createOrUpdateVehiclePairings(
            @RequestBody @Valid VehiclePairingsRequest request
    ){
        VehiclePairingsResponse result = vehiclePairingsCreateUseCase.create(request);
        return BaseResult.success(result ,"新增車頭與板車資訊完成");
    }

}
