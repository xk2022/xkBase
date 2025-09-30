package com.xk.car.controller;


import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.application.model.VehicleStatusLogsRequest;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.application.usecase.VehicleStatusLogCreateUseCase;
import com.xk.car.application.usecase.VehicleStatusLogDeleteUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    private final VehicleStatusLogDeleteUseCase vehicleStatusLogDeleteUseCase;



    @Operation(summary = "新增車輛狀態管理" ,description = "新增車輛狀態管理")
    @PostMapping("/save")
    public BaseResult<VehicleStatusLogsResponse> create(
            @RequestBody @Valid VehicleStatusLogsRequest request,
            @AuthenticationPrincipal JwtUserDTO userDTO
    ) throws NotFoundException {
        VehicleStatusLogsResponse result =vehicleStatusLogCreateUseCase.create(request);
        return BaseResult.success(result, "車輛狀態管理新增成功");
    }

    @Operation(summary = "刪除車輛狀態管理" ,description = "刪除車輛狀態管理")
    @DeleteMapping("/{uuid}")
    public BaseResult<Void> delete (@PathVariable UUID uuid){
        log.info("刪除車輛狀態管理 uuid={}",uuid);
        vehicleStatusLogDeleteUseCase.delete(uuid);
        return BaseResult.success(null , "刪除車輛狀態管理成功");
    }

}
