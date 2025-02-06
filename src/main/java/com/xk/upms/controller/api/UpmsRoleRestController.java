package com.xk.upms.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsUserRestController` - 負責管理 **角色 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author hank Created on 2025/02/04.
 * @author hank Updated on 2025/01/01 something note here.
 */
@RestController
@RequestMapping("/api/upms/roles")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Tag(name = "UpmsRole Management", description = "提供 UpmsRole 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsRoleRestController {

//	private final UpmsRoleFindUseCase upmsRoleFindUseCase;

//	private final UpmsRoleCreateUseCase upmsRoleCreateUseCase;

//	private final UpmsRoleUpdateUseCase upmsRoleUpdateUseCase;

//	private final UpmsRoleDeleteUseCase upmsRoleDeleteUseCase;

//	@Operation(summary = "取得所有角色", description = "返回系統中所有 UpmsRole 的列表。")
//	@GetMapping
//	public BaseResult<List<UpmsRoleResponseDTO>> getAllUsers(UpmsRoleRequestDTO request) {
//		List<UpmsRoleResponseDTO> roles = upmsRoleFindUseCase.getList(request);
//		return BaseResult.success(roles, "成功獲取角色列表");
//	}

//	@Operation(summary = "根據ID取得角色", description = "根據提供的角色ID返回對應的角色資料。")
//	@GetMapping("/{id}")
//	public BaseResult<UpmsRoleResponseDTO> getRoleById(
//			@Parameter(description = "角色的唯一識別碼", required = true) @PathVariable Long id) {
//		UpmsRoleResponseDTO role = upmsRoleFindUseCase.getOneById(id);
//		if (role != null) {
//			return BaseResult.success(role, "成功獲取角色資料");
//		}
//		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的角色", null);
//	}

//	@Operation(summary = "新增角色", description = "創建一個新的 UpmsRole。")
//	@PostMapping
//	public BaseResult<UpmsRoleResponseDTO> createRole(
//			@RequestBody UpmsRoleCreateDTO request) {
//		UpmsRoleResponseDTO createdRole = upmsRoleCreateUseCase.create(request);
//		return BaseResult.success(createdRole, "用戶創建成功");
//	}

//	@Operation(summary = "更新角色資料", description = "根據提供的ID更新角色的詳細資料。")
//	@PutMapping("/{id}")
//	public BaseResult<UpmsRoleResponseDTO> updateRole(
//			@Parameter(description = "需要更新的角色ID", required = true) @PathVariable Long id,
//			@RequestBody UpmsRoleUpdateDTO request) {
//		UpmsRoleResponseDTO updatedRole = upmsRoleUpdateUseCase.update(id, request);
//		if (updatedRole != null) {
//			return BaseResult.success(updatedRole, "角色更新成功");
//		}
//		return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到需要更新的角色", null);
//	}

//	@Operation(summary = "刪除角色", description = "根據提供的角色ID刪除對應的角色。")
//	@DeleteMapping("/{id}")
//	public BaseResult<Void> deleteRole(
//			@Parameter(description = "需要刪除的角色ID", required = true) @PathVariable Long id) {
//		upmsRoleDeleteUseCase.delete(id);
//		return BaseResult.success(null, "角色刪除成功");
//	}

}
