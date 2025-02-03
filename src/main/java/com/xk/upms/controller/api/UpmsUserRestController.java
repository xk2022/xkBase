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
import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
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
@RequestMapping("/api/upms/users")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Tag(name = "UpmsUser Management", description = "提供 UpmsUser 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsUserRestController {

	private UpmsUserFindUseCase upmsUserFindUseCase;
	private UpmsUserCreateUseCase upmsUserCreateUseCase;
	private UpmsUserUpdateUseCase upmsUserUpdateUseCase;
	private UpmsUserDeleteUseCase upmsUserDeleteUseCase;

	@Operation(summary = "取得所有用戶", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping
	public BaseResult<List<UpmsUserResponseDTO>> getAllUsers(UpmsUserRequestDTO request) {
		List<UpmsUserResponseDTO> users = upmsUserFindUseCase.getList(request);
		return BaseResult.success(users, "成功獲取用戶列表");
	}

	@Operation(summary = "根據ID取得用戶", description = "根據提供的用戶ID返回對應的用戶資料。")
	@GetMapping("/{id}")
	public BaseResult<UpmsUserResponseDTO> getUserById(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable Long id) {
		try {
			UpmsUserResponseDTO user = upmsUserFindUseCase.getOneById(id);
			if (user != null) {
				return BaseResult.success(user, "成功獲取用戶資料");
			} else {
				return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的用戶", null);
			}
		} catch (Exception e) {
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "新增用戶", description = "創建一個新的 UpmsUser。")
	@PostMapping
	public BaseResult<UpmsUserResponseDTO> createUser(@RequestBody UpmsUserCreateDTO request) {
		try {
			UpmsUserResponseDTO createdUser = upmsUserCreateUseCase.create(request);
			return BaseResult.success(createdUser, "用戶創建成功");
		} catch (Exception e) {
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "更新用戶資料", description = "根據提供的ID更新用戶的詳細資料。")
	@PutMapping("/{id}")
	public BaseResult<UpmsUserResponseDTO> updateUser(
			@Parameter(description = "需要更新的用戶ID", required = true) @PathVariable Long id,
			@RequestBody UpmsUserCreateDTO request) {
		try {
			UpmsUserResponseDTO updatedUser = upmsUserUpdateUseCase.update(id, request);
			if (updatedUser != null) {
				return BaseResult.success(updatedUser, "用戶更新成功");
			} else {
				return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的用戶", null);
			}
		} catch (Exception e) {
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "刪除用戶", description = "根據提供的用戶ID刪除對應的用戶。")
	@DeleteMapping("/{id}")
	public BaseResult<Void> deleteUser(
			@Parameter(description = "需要刪除的用戶ID", required = true) @PathVariable Long id) {
		try {
			upmsUserDeleteUseCase.delete(id);
			return BaseResult.success(null, "用戶刪除成功");
		} catch (Exception e) {
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

}
