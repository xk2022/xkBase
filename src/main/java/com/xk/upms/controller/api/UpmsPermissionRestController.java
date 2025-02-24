package com.xk.upms.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.common.base.BaseResult;
import com.xk.upms.application.model.UpmsPermissionCreateDTO;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionCreateUseCase;
import com.xk.upms.application.usecase.UpmsPermissionDeleteUseCase;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Tag(name = "UpmsPermission Management", description = "提供 UpmsPermission 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsPermissionRestController {

	private final UpmsPermissionFindUseCase upmsPermissionFindUseCase;

	private final UpmsPermissionCreateUseCase upmsPermissionCreateUseCase;

	private final UpmsPermissionUpdateUseCase upmsPermissionUpdateUseCase;

	private final UpmsPermissionDeleteUseCase upmsPermissionDeleteUseCase;

	@Operation(summary = "取得所有權限", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping
	public BaseResult<List<UpmsPermissionResponseDTO>> getAllPermission() {
		List<UpmsPermissionResponseDTO> users = upmsPermissionFindUseCase.getAllPermission();
		return BaseResult.success(users, "成功獲取權限列表");
	}

	@Operation(summary = "根據ID取得權限", description = "根據提供的權限ID返回對應的權限資料。")
	@GetMapping("/{id}")
	public BaseResult<UpmsPermissionResponseDTO> getUserById(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable Long id) {
		UpmsPermissionResponseDTO permission = upmsPermissionFindUseCase.findById(id);
		if (permission != null) {
			return BaseResult.success(permission, "成功獲取權限資料");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的權限", null);
	}

	@Operation(summary = "新增權限", description = "創建一個新的 UpmsPermission。")
	@PostMapping
	public BaseResult<UpmsPermissionResponseDTO> createUser(@RequestBody UpmsPermissionCreateDTO request) {
		UpmsPermissionResponseDTO createdPermission = upmsPermissionCreateUseCase.create(request);
		return BaseResult.success(createdPermission, "用戶創建成功");
	}

	@Operation(summary = "更新權限資料", description = "根據提供的ID更新權限的詳細資料。")
	@PutMapping("/{id}")
	public BaseResult<UpmsPermissionResponseDTO> updateUser(
			@Parameter(description = "需要更新的權限ID", required = true) @PathVariable Long id,
			@RequestBody UpmsPermissionUpdateDTO request) {
		UpmsPermissionResponseDTO updatedPermission = upmsPermissionUpdateUseCase.update(id, request);
		if (updatedPermission != null) {
			return BaseResult.success(updatedPermission, "權限更新成功");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的權限", null);
	}

	@Operation(summary = "刪除權限", description = "根據提供的用戶ID刪除對應的權限。")
	@DeleteMapping("/{id}")
	public BaseResult<Void> deleteUser(@Parameter(description = "需要刪除的權限ID", required = true) @PathVariable Long id) {
		upmsPermissionDeleteUseCase.delete(id);
		return BaseResult.success(null, "權限刪除成功");
	}

}
