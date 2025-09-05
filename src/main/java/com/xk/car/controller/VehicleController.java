package com.xk.car.controller;


import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.usecase.VehicleCreateUseCase;
import com.xk.car.application.usecase.VehicleDeleteUseCase;
import com.xk.car.application.usecase.VehicleQueryUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


/**
 * 📌 `VehicleController` - 負責管理 車輛系統資訊 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/08/13.
 */
@Tag(name = "Vehicle Management", description = "車頭與板車管理")
@RestController
@RequestMapping("/api/car/vehicle")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehicleController {

    private final VehicleCreateUseCase vehicleCreateUseCase;
    private final VehicleDeleteUseCase vehicleDeleteUseCase;
    private final VehicleQueryUseCase vehicleQueryUseCase;


    @Operation(summary = "新增或更新車輛資訊", description = "新增或更新一台車輛記錄")
    @PostMapping("/save")
    public BaseResult<VehicleResponse> create(
            @RequestBody @Valid VehicleRequest vehicleRequest,
            @AuthenticationPrincipal JwtUserDTO userDTO
    ){
        VehicleResponse result =vehicleCreateUseCase.create(vehicleRequest);
        return BaseResult.success(result, "車輛新增成功");
    }

    @Operation(summary = "刪除一台車輛資訊", description = "刪除一台車輛資訊")
    @DeleteMapping("/{uuid}")
    public BaseResult<Void> delete (@PathVariable UUID uuid){
        log.info("[API] 刪除車輛資訊 uuid={}", uuid);
        vehicleDeleteUseCase.delete(uuid);
        return BaseResult.success(null,"刪除車輛成功");
    }

    @Operation(summary = "查詢車輛資訊" ,description = "查詢車輛資訊")
    @PostMapping("/getVehicleByStatusAndLicensePlate")
    public BaseResult<VehicleResponse> getVehicleByStatusAndLicensePlate(
            @RequestBody @Valid VehicleRequest vehicleRequest){
        log.info("[API] 查詢車輛資訊");
        VehicleResponse response = vehicleQueryUseCase.getVehicleByStatusAndLicensePlate(vehicleRequest);
        return BaseResult.success(response , "查詢成功");
    }

    @Operation(summary = "依照查詢結果 匯出成報表" ,description = "依照查詢結果 匯出成報表")
    @PostMapping("/writeExcelReport")
    public void writeExcelReport(@RequestBody Map<String ,Object> params , HttpServletRequest request, HttpServletResponse response){
        log.info("[API] 匯出車輛資訊報表");


    }

}
