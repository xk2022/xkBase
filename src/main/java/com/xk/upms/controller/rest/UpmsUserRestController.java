package com.xk.upms.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.xk.upms.model.bo.UpmsUserSaveReq;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.service.UpmsUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/upms/users")
@Tag(name = "UpmsUser Management", description = "提供 UpmsUser 的管理功能，包括新增、查詢、更新和刪除。")
public class UpmsUserRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserRestController.class);

	@Autowired
	private UpmsUserService upmsUserService;

	@Operation(summary = "取得所有用戶", description = "返回系統中所有 UpmsUser 的列表。")
	@GetMapping
	public BaseResult<List<UpmsUserResp>> getAllUsers() {
		LOGGER.debug("開始查詢所有用戶");
		List<UpmsUserResp> users = upmsUserService.getList(null);
		LOGGER.info("查詢完成，用戶數量: {}", users.size());
		return BaseResult.success(users, "成功獲取用戶列表");
	}

	@Operation(summary = "根據ID取得用戶", description = "根據提供的用戶ID返回對應的用戶資料。")
	@GetMapping("/{id}")
	public BaseResult<UpmsUserResp> getUserById(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable Long id) {
		LOGGER.debug("開始查詢用戶，ID: {}", id);
		try {
			UpmsUserResp user = upmsUserService.getOneById(id);
			if (user != null) {
				LOGGER.info("用戶查詢成功，ID: {}", id);
				return BaseResult.success(user, "成功獲取用戶資料");
			} else {
				LOGGER.warn("未找到對應用戶，ID: {}", id);
				return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的用戶", null);
			}
		} catch (Exception e) {
			LOGGER.error("Error fetching user by ID: {}", id, e);
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "新增用戶", description = "創建一個新的 UpmsUser。")
	@PostMapping
	public BaseResult<UpmsUserResp> createUser(@RequestBody UpmsUserSaveReq resources) {
		LOGGER.debug("開始新增用戶: {}", resources);
		try {
			UpmsUserResp createdUser = upmsUserService.create(resources);
			LOGGER.info("用戶新增成功，ID: {}", createdUser.getId());
			return BaseResult.success(createdUser, "用戶創建成功");
		} catch (Exception e) {
			LOGGER.error("Error creating user: {}", resources, e);
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "更新用戶資料", description = "根據提供的ID更新用戶的詳細資料。")
	@PutMapping("/{id}")
	public BaseResult<UpmsUserResp> updateUser(
			@Parameter(description = "需要更新的用戶ID", required = true) @PathVariable Long id,
			@RequestBody UpmsUserSaveReq resources) {
		LOGGER.debug("開始更新用戶資料，ID: {}", id);
		try {
			UpmsUserResp updatedUser = upmsUserService.update(id, resources);
			if (updatedUser != null) {
				LOGGER.info("用戶更新成功，ID: {}", id);
				return BaseResult.success(updatedUser, "用戶更新成功");
			} else {
				LOGGER.warn("未找到需要更新的用戶，ID: {}", id);
				return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的用戶", null);
			}
		} catch (Exception e) {
			LOGGER.error("Error updating user with ID: {}", id, e);
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

	@Operation(summary = "刪除用戶", description = "根據提供的用戶ID刪除對應的用戶。")
	@DeleteMapping("/{id}")
	public BaseResult<Void> deleteUser(@Parameter(description = "需要刪除的用戶ID", required = true) @PathVariable Long id) {
		LOGGER.info("開始刪除用戶，ID: {}", id);
		try {
			boolean deleted = upmsUserService.deleteUser(id);
			if (deleted) {
				LOGGER.info("用戶刪除成功，ID: {}", id);
				return BaseResult.success(null, "用戶刪除成功");
			} else {
				LOGGER.warn("未找到需要刪除的用戶，ID: {}", id);
				return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要刪除的用戶", null);
			}
		} catch (Exception e) {
			LOGGER.error("Error deleting user with ID: {}", id, e);
			return BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "伺服器內部錯誤", e.getMessage());
		}
	}

}
