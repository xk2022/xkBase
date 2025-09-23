package com.xk.car.controller;


import com.xk.car.application.model.VehicleMaintenanceRequest;
import com.xk.car.application.model.VehicleMaintenanceResponse;
import com.xk.car.application.usecase.VehicleMaintenanceCreateUseCase;
import com.xk.car.application.usecase.VehicleMaintenanceDeleteUseCase;
import com.xk.car.application.usecase.VehicleMaintenanceQueryUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

/**
 * 📌 `VehicleMaintenanceController` - 負責管理 車輛性能監控與維修提醒 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/08/13.
 */
@Tag(name = "VehicleMaintenance Management", description = "車頭與板車管理")
@RestController
@RequestMapping("/api/car/vehiclemaintenance")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehicleMaintenanceController {

    private final VehicleMaintenanceCreateUseCase vehicleMaintenanceCreateUseCase;
    private final VehicleMaintenanceDeleteUseCase vehicleMaintenanceDeleteUseCase;
    private final VehicleMaintenanceQueryUseCase vehicleMaintenanceQueryUseCase;

    @PostMapping("/save")
    public BaseResult<VehicleMaintenanceResponse> create(
            @RequestBody @Valid VehicleMaintenanceRequest request,
            @AuthenticationPrincipal JwtUserDTO userDTO
    ) throws ParseException {
        VehicleMaintenanceResponse response = vehicleMaintenanceCreateUseCase.create(request);
        return  BaseResult.success(response , "新增成功");
    }


    @DeleteMapping("/{uuid}")
    public BaseResult<Void> delete(@PathVariable UUID uuid){
        log.info("[API] 刪除車輛維修提醒 uuid ={}" , uuid );
        vehicleMaintenanceDeleteUseCase.delete(uuid);
        return BaseResult.success(null,"刪除車輛維修提醒成功");

    }

    @GetMapping("/getMaintenanceByCarId")
    public BaseResult<List<VehicleMaintenanceResponse>> getMaintenanceByCarId (
        @RequestParam(name="licensePlate" ,required = true)String  licensePlate,
        @RequestParam(name="brandModel" ,required = false)String  brandModel,
        @RequestParam(name="year" ,required = false)String  year,
        @RequestParam(name="status" ,required = false)String  status

    ){
        log.info("[API] 查詢車輛維修提醒紀錄");
        List<VehicleMaintenanceResponse> responseList = vehicleMaintenanceQueryUseCase.getMaintenanceByCarId(licensePlate,brandModel,year,status);
        return BaseResult.success(responseList,"查詢車輛維修提醒成功");
    }



}
