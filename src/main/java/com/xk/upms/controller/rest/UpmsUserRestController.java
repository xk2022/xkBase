package com.xk.upms.controller.rest;

import com.xk.common.base.BaseResult;
import com.xk.upms.model.bo.UpmsUserCreateReq;
import com.xk.upms.model.bo.UpmsUserFindReq;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.usecase.user.UpmsUserCreateUseCase;
import com.xk.upms.usecase.user.UpmsUserDeleteUseCase;
import com.xk.upms.usecase.user.UpmsUserFindUseCase;
import com.xk.upms.usecase.user.UpmsUserUpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/upms/users")
@Tag(name = "UpmsUser Management", description = "提供 UpmsUser 的管理功能，包括新增、查詢、更新和刪除。")
public class UpmsUserRestController {

	@Autowired
	private UpmsUserFindUseCase upmsUserFindUseCase;

	@Autowired
	private UpmsUserCreateUseCase upmsUserCreateUseCase;

	@Autowired
	private UpmsUserUpdateUseCase upmsUserUpdateUseCase;

	@Autowired
	private UpmsUserDeleteUseCase upmsUserDeleteUseCase;

	@Operation(summary = "取得所有用戶", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping
	public BaseResult<List<UpmsUserResp>> getAllUsers(
			UpmsUserFindReq request) {
		List<UpmsUserResp> users = upmsUserFindUseCase.getList(request);
		return BaseResult.success(users, "成功獲取用戶列表");
	}

	@Operation(summary = "根據ID取得用戶", description = "根據提供的用戶ID返回對應的用戶資料。")
	@GetMapping("/{id}")
	public BaseResult<UpmsUserResp> getUserById(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable Long id) {
		try {
			UpmsUserResp user = upmsUserFindUseCase.getOneById(id);
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
	public BaseResult<UpmsUserResp> createUser(
			@RequestBody UpmsUserCreateReq request) {
		try {
			UpmsUserResp createdUser = upmsUserCreateUseCase.create(request);
			return BaseResult.success(createdUser, "用戶創建成功");
		} catch (Exception e) {
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "更新用戶資料", description = "根據提供的ID更新用戶的詳細資料。")
	@PutMapping("/{id}")
	public BaseResult<UpmsUserResp> updateUser(
			@Parameter(description = "需要更新的用戶ID", required = true) @PathVariable Long id,
			@RequestBody UpmsUserCreateReq request) {
		try {
			UpmsUserResp updatedUser = upmsUserUpdateUseCase.update(id, request);
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
