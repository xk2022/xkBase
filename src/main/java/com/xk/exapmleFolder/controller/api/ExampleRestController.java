package com.xk.exapmleFolder.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleCreateUseCase;
import com.xk.exapmleFolder.application.usecase.ExampleDeleteUseCase;
import com.xk.exapmleFolder.application.usecase.ExampleFindUseCase;
import com.xk.exapmleFolder.application.usecase.ExampleUpdateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UserRestController` - 負責管理 **使用者 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@RestController
@RequestMapping("/api/example/users")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Tag(name = "Example Module Users Management第二版，現在適用", description = "提供 ExampleUser 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class ExampleRestController {

	private final ExampleCreateUseCase userCreateUseCase;
	private final ExampleFindUseCase userFindUseCase;
	private final ExampleUpdateUseCase userUpdateUseCase;
	private final ExampleDeleteUseCase userDeleteUseCase;

	/**
	 * 📌 1️⃣ [POST] 創建使用者
	 */
    @Operation(summary = "新增用戶", description = "創建新的 User。")
	@PostMapping
	public BaseResult<ExampleResponseDTO> createUser(@Valid @RequestBody ExampleRequestDTO request) {
		log.info("📌 創建新使用者: {}", request.getUsername());
		return BaseResult.success(userCreateUseCase.create(request), "用戶創建成功");
	}

	/**
	 * 📌 2️⃣ [GET] 取得單筆使用者資訊 (依據 ID)
	 */
	@Operation(summary = "根據ID取得用戶", description = "根據提供的用戶ID返回對應的用戶資料。")
	@GetMapping("/{id}")
	public BaseResult<ExampleResponseDTO> getUserById(
			@Parameter(description = "用戶的唯一識別碼", required = true) @PathVariable Long id) {
		log.info("📌 查詢使用者 ID: {}", id);
		ExampleResponseDTO response = userFindUseCase.getOneById(id);
		return response != null
                ? BaseResult.success(response, "成功獲取用戶資料")
                : BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的用戶", null);
    }

	/**
	 * 📌 3️⃣ [GET] 取得所有使用者 (支援條件查詢 + 分頁)
	 */
    @Operation(summary = "取得所有用戶", description = "返回所有 User（支援分頁）。")
	@GetMapping
	public BaseResult<Page<ExampleResponseDTO>> getAllUsers(ExampleRequestDTO request, Pageable pageable) {
		log.info("📌 查詢所有使用者 (支援條件查詢 + 分頁): {}", request);
		return BaseResult.success(userFindUseCase.getList(request, pageable), "成功獲取用戶列表");
    }

	/**
	 * 📌 4️⃣ [PUT] 更新使用者資訊
	 */
	@Operation(summary = "更新用戶資料", description = "根據提供的ID更新用戶的詳細資料。")
	@PutMapping("/{id}")
	public BaseResult<ExampleResponseDTO> updateUser(
			@Parameter(description = "需要更新的用戶ID", required = true) @PathVariable Long id,
			@RequestBody ExampleRequestDTO request) {
		ExampleResponseDTO response = userUpdateUseCase.update(id, request);
		return response != null
                ? BaseResult.success(response, "用戶更新成功")
                : BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的用戶", null);
	}

	/**
	 * 📌 5️⃣ [DELETE] 刪除使用者
	 */
	@Operation(summary = "刪除用戶", description = "根據提供的用戶ID刪除對應的用戶。")
	@DeleteMapping("/{id}")
	public BaseResult<Void> deleteUser(@Parameter(description = "需要刪除的用戶ID", required = true) @PathVariable Long id) {
		log.info("📌 刪除使用者 ID: {}", id);
        return userDeleteUseCase.delete(id) 
                ? BaseResult.success(null, "用戶刪除成功")
                : BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的用戶", null);
	}
	
}
