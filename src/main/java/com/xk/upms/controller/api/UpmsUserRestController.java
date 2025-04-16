package com.xk.upms.controller.api;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
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

import com.xk.common.base.BaseResult;
import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserFindRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;
import com.xk.upms.application.usecase.UpmsUserCreateUseCase;
import com.xk.upms.application.usecase.UpmsUserDeleteUseCase;
import com.xk.upms.application.usecase.UpmsUserFindUseCase;
import com.xk.upms.application.usecase.UpmsUserUpdateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsUserRestController` - 負責管理 **使用者 API**
 * 
 * - 提供 `CRUD` 操作 
 * - 支援分頁查詢 
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@RestController
@RequestMapping("/api/upms/user")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "UpmsUser Management", description = "提供 UpmsUser 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsUserRestController {

	private final UpmsUserFindUseCase upmsUserFindUseCase;

	private final UpmsUserCreateUseCase upmsUserCreateUseCase;

	private final UpmsUserUpdateUseCase upmsUserUpdateUseCase;

	private final UpmsUserDeleteUseCase upmsUserDeleteUseCase;

	@Operation(summary = "取得所有用戶", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping
	public BaseResult<List<UpmsUserResponseDTO>> getAllUsers(UpmsUserFindRequestDTO request) {
		List<UpmsUserResponseDTO> users = upmsUserFindUseCase.getList(request);
		return BaseResult.success(users, "成功獲取用戶列表");
	}

	@Operation(summary = "根據ID取得用戶", description = "根據提供的用戶ID返回對應的用戶資料。")
	@GetMapping("/{uuid}")
	public BaseResult<UpmsUserResponseDTO> getUserByUuid(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable UUID uuid) {
		UpmsUserResponseDTO user = upmsUserFindUseCase.getByUuid(uuid);
		if (user != null) {
			return BaseResult.success(user, "成功獲取用戶資料");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的用戶", null);
	}

	@Operation(summary = "新增用戶", description = "創建一個新的 UpmsUser。")
	@PostMapping
	public BaseResult<UpmsUserResponseDTO> createUser(
			@RequestBody @Validated @NotNull UpmsUserCreateDTO request) {
		UpmsUserResponseDTO createdUser = upmsUserCreateUseCase.create(request);
		return BaseResult.success(createdUser, "用戶創建成功");
	}

	@Operation(summary = "更新用戶資料", description = "根據提供的ID更新用戶的詳細資料。")
	@PutMapping("/{uuid}")
	public BaseResult<UpmsUserResponseDTO> updateUser(
			@Parameter(description = "需要更新的用戶uuid", required = true) @PathVariable @NotNull UUID uuid,
			@RequestBody @Validated @NotNull UpmsUserUpdateDTO request) {
		UpmsUserResponseDTO updatedUser = upmsUserUpdateUseCase.update(uuid, request);
		if (updatedUser != null) {
			return BaseResult.success(updatedUser, "用戶更新成功");
		}
		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的用戶", null);
	}

	@Operation(summary = "刪除用戶", description = "根據提供的用戶ID刪除對應的用戶。")
	@DeleteMapping("/{uuid}")
	public BaseResult<Void> deleteUser(
			@Parameter(description = "需要刪除的用戶uuid", required = true) @PathVariable @NotNull UUID uuid) {
		upmsUserDeleteUseCase.delete(uuid);
		return BaseResult.success(null, "用戶刪除成功");
	}

}
