package com.xk.car.controller;


import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.application.usecase.VehicleStatusLogCreateUseCase;
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
 * 📌 `VehicleStatusLogsController` - 負責管理 車輛狀態管理 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/08/13.
 */
@Tag(name = "Vehicle Management", description = "車頭與板車管理")
@RestController
@RequestMapping("/api/car/vehiclestatuslogs")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehicleStatusLogsController {

    private final VehicleStatusLogCreateUseCase vehicleStatusLogCreateUseCase;



    @Operation(summary = "新增車輛狀態管理" ,description = "新增車輛狀態管理")
    @PostMapping("/save")
    public BaseResult<VehicleStatusLogsResponse> createOrUpdateVehicle(
            @RequestBody @Valid VehicleRequest vehicleRequest
    ){
        VehicleStatusLogsResponse result =vehicleStatusLogCreateUseCase.create(vehicleRequest);
        return BaseResult.success(result, "車輛狀態管理新增成功");
    }


}
