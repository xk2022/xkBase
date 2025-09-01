package com.xk.car.controller;



import com.xk.car.application.model.VehicleTripLogsRequest;
import com.xk.car.application.model.VehicleTripLogsResponse;
import com.xk.car.application.usecase.VehicleTripLogsCreateUseCase;
import com.xk.car.application.usecase.VehicleTripLogsDeleteUseCase;
import com.xk.common.base.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

/**
 * 📌 `VehicleTripLogsController` - 負責管理 里程紀錄 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/09/01.
 */
@Tag(name = "VehicleTripLogs Management", description = "里程紀錄")
@RestController
@RequestMapping("/api/car/vehicletriplogs")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehicleTripLogsController {

    private final VehicleTripLogsCreateUseCase vehicleTripLogsCreateUseCase;
    private final VehicleTripLogsDeleteUseCase vehicleTripLogsDeleteUseCase;

    @Operation(summary = "新增里程紀錄管理" ,description = "新增里程紀錄管理")
    @PostMapping("/save")
    public BaseResult<VehicleTripLogsResponse> create(
            @RequestBody @Valid VehicleTripLogsRequest request
    ) throws ParseException {
        VehicleTripLogsResponse result =vehicleTripLogsCreateUseCase.create(request);
        return BaseResult.success(result, "車輛里程紀錄新增成功");
    }

    @Operation(summary = "刪除車輛里程紀錄管理" ,description = "刪除車輛里程紀錄管理")
    @DeleteMapping("/{uuid}")
    public BaseResult<Void> delete (@PathVariable UUID uuid){
        log.info("刪除車輛里程紀錄管理 uuid={}",uuid);
        vehicleTripLogsDeleteUseCase.delete(uuid);
        return BaseResult.success(null , "刪除車輛里程紀錄提醒成功");
    }
}
