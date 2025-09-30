package com.xk.car.controller;


import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import com.xk.car.application.usecase.VehiclePartsUsageCreateUseCase;
import com.xk.car.application.usecase.VehiclePartsUsageDeleteUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

/**
 * 📌 `VehiclePartsUsageController` - 負責管理 耗損與維修項目紀錄 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/08/13.
 */
@Tag(name = "VehiclePartsUsage Management", description = "車輛耗損與維修紀錄")
@RestController
@RequestMapping("/api/car/vehiclepartsusage")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehiclePartsUsageController {

    private final VehiclePartsUsageCreateUseCase vehiclePartsUsageCreateUseCase;
    private final VehiclePartsUsageDeleteUseCase vehiclePartsUsageDeleteUseCase;


    @Operation(summary = "新增耗損與維修項目紀錄 ")
    @PostMapping("/save")
    public BaseResult<VehiclePartsUsageResponse> create(
            @RequestBody @Valid VehiclePartsUsageRequest request,
            @AuthenticationPrincipal JwtUserDTO userDTO
    ) throws ParseException {
        VehiclePartsUsageResponse result = vehiclePartsUsageCreateUseCase.create(request);
        return BaseResult.success(result , "新增耗損與維修項目紀錄成功");
    }

    @Operation(summary = "刪除耗損與維修項目紀錄 ")
    @DeleteMapping("/{uuid}")
    public BaseResult<Void> delete (@PathVariable UUID uuid){
        log.info("刪除耗損與維修項目紀錄 uuid={}",uuid);
        vehiclePartsUsageDeleteUseCase.delete(uuid);
        return BaseResult.success(null , "刪除耗損與維修項目紀錄成功");

    }


}
