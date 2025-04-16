package com.xk.upms.controller.api;

import com.xk.common.base.BaseResult;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 📌 `UpmsPermissionRestController` - 負責管理 權限 API**
 * 
 * - 提供 `CRUD` 操作 
 * - 支援分頁查詢 
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/01/01 something note here.
 */
@RestController
@RequestMapping("/api/upms/permission")
@RequiredArgsConstructor
@Tag(name = "UpmsPermission Management", description = "提供 UpmsPermission 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsPermissionRestController {

	private final UpmsPermissionFindUseCase upmsPermissionFindUseCase;

	private final UpmsPermissionUpdateUseCase upmsPermissionUpdateUseCase;

	@Operation(summary = "取得所有權限", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping("/{systemUuid}/{roleUuid}")
	public BaseResult<List<UpmsPermissionResponseDTO>> findAll(
			@PathVariable @NotNull UUID systemUuid,
			@PathVariable @NotNull UUID roleUuid) {
		List<UpmsPermissionResponseDTO> responseDTOS = upmsPermissionFindUseCase.findAll(systemUuid, roleUuid);
		return BaseResult.success(responseDTOS, "成功獲取權限列表");
	}

	@Operation(summary = "更新權限資料", description = "更新權限的詳細資料。")
	@PutMapping("/{systemUuid}/{roleUuid}")
	public BaseResult update(
			@PathVariable @NotNull UUID systemUuid,
			@PathVariable @NotNull UUID roleUuid,
			@RequestBody UpmsPermissionUpdateDTO request) {
		upmsPermissionUpdateUseCase.update(systemUuid, roleUuid, request);
		return BaseResult.success(null, "權限更新成功");
	}

}
