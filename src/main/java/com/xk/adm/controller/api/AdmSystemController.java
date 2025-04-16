package com.xk.adm.controller.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.dto.AdmSystemCreateDTO;
import com.xk.adm.application.dto.AdmSystemRequest;
import com.xk.adm.application.dto.AdmSystemResponse;
import com.xk.adm.application.usecase.AdmSystemCreateUseCase;
import com.xk.adm.application.usecase.AdmSystemDeleteUseCase;
import com.xk.adm.application.usecase.AdmSystemReadUseCase;
import com.xk.adm.application.usecase.AdmSystemUpdateUseCase;
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

	private final AdmSystemCreateUseCase createUseCase;
	private final AdmSystemReadUseCase readUseCase;
	private final AdmSystemUpdateUseCase updateUseCase;
	private final AdmSystemDeleteUseCase deleteUseCase;

	@Operation(summary = "獲取所有系統資訊", description = "查詢所有系統類型")
	@GetMapping
	public BaseResult<List<AdmSystemResponse>> getAllSystems() {
		List<AdmSystemResponse> systems = readUseCase.getList();
		return BaseResult.success(systems, "成功獲取系統列表");
	}

	@Operation(summary = "根據類型獲取系統資訊", description = "查詢特定類型的系統")
	@GetMapping("/{uuid}")
	public BaseResult<AdmSystemResponse> getSystemByUuid(
			@Parameter(description = "系統的唯一ID", required = true) @PathVariable UUID uuid) {
		log.info(" [API] 查詢特定系統 - UUID: {}", uuid);
		AdmSystemResponse system = readUseCase.getSystemByUuid(uuid);

	    return (system != null) 
	        ? BaseResult.success(system, "成功獲取系統資料") 
	        : BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的系統", null);
	}

	@Operation(summary = "新增系統資訊", description = "新增一條系統記錄")
	@PostMapping
	public BaseResult<AdmSystemResponse> createSystem(
			@RequestBody @Validated AdmSystemCreateDTO request) {
		log.info(" [API] 創建系統 - Code: {}", request.getCode());

	    AdmSystemResponse result = createUseCase.create(request);
	    return BaseResult.success(result, "系統創建成功");
	}

	@Operation(summary = "更新系統資訊", description = "更新現有的系統記錄")
	@PutMapping("/{uuid}")
	public BaseResult<AdmSystemResponse> updateSystem(
			@PathVariable UUID uuid, @RequestBody @Validated @NotNull AdmSystemRequest request) {
		log.info("📌 [API] 更新系統 - UUID: {}", uuid);

	    AdmSystemResponse updatedSystem = updateUseCase.update(uuid, request);
	    return BaseResult.success(updatedSystem, "系統更新成功");
	}

	@Operation(summary = "刪除系統資訊", description = "根據 ID 刪除系統")
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> deleteSystem(
	        @Parameter(description = "需要刪除的系統 ID", required = true) @PathVariable UUID uuid) {
		log.info(" [API] 刪除系統 - UUID: {}", uuid);

	    deleteUseCase.delete(uuid); // 傳遞 UUID 進入 UseCase

	    return ResponseEntity.noContent().build(); // 204 No Content
	}
}