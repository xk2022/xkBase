package com.xk.adm.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.model.AdmSystemDTO;
import com.xk.adm.application.usecase.AdmSystemFindUseCase;
import com.xk.adm.application.usecase.AdmSystemManageUseCase;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemController` - 負責管理 **系統資訊 API**
 * 
 * - 提供 `CRUD` 操作 
 * - 支援分頁查詢 
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/25.
 */
@Tag(name = "System Management", description = "系統管理")
@RestController
@RequestMapping("/api/adm/system")
@RequiredArgsConstructor
@Slf4j
public class AdmSystemController {

	private final AdmSystemFindUseCase admSystemFindUseCase;
	private final AdmSystemManageUseCase admSystemManageUseCase;

	@Operation(summary = "獲取所有系統資訊", description = "查詢所有系統類型")
	@GetMapping("/all")
	public BaseResult<List<AdmSystemDTO>> getAllSystems() {
		List<AdmSystemDTO> systems = admSystemFindUseCase.getList();
		return BaseResult.success(systems, "成功獲取系統列表");
	}

	@Operation(summary = "根據類型獲取系統資訊", description = "查詢特定類型的系統")
	@GetMapping("/{code}")
	public BaseResult<AdmSystemDTO> getSystemByCode(
			@Parameter(description = "系統的唯一代號", required = true) @PathVariable String code) {
		AdmSystemDTO system = admSystemFindUseCase.getSystemByCode(code);
		if (system != null) {
			return BaseResult.success(system, "成功獲取系統資料");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的系統", null);
	}

	@Operation(summary = "新增系統資訊", description = "新增一條系統記錄")
	@PostMapping("/create")
	public BaseResult<AdmSystemDTO> createSystem(
			@RequestBody @Validated @NotNull AdmSystemDTO request) {
		AdmSystemDTO createdUser = admSystemManageUseCase.create(request);
		return BaseResult.success(createdUser, "系統創建成功");
	}

	@Operation(summary = "更新系統資訊", description = "更新現有的系統記錄")
	@PutMapping("/update")
	public BaseResult<AdmSystemDTO> updateSystem(
			@RequestBody @Validated @NotNull AdmSystemDTO request) {
		AdmSystemDTO updatedSystem = admSystemManageUseCase.update(request);
		if (updatedSystem != null) {
			return BaseResult.success(updatedSystem, "系統更新成功");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的系統", null);
	}

	@Operation(summary = "刪除系統資訊", description = "根據 ID 刪除系統")
	@DeleteMapping("/delete/{id}")
	public BaseResult<Boolean> deleteSystem(
			@Parameter(description = "需要刪除的用戶ID", required = true) @PathVariable @NotNull String id) {
		return BaseResult.success(admSystemManageUseCase.delete(id), "成功");
	}

}