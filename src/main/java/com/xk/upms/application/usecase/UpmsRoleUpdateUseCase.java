package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.model.UpmsRoleUpdateDTO;

import java.util.UUID;

/**
 * 📌 `UpmsRoleUpdateUseCase` - 角色更新應用層 Use Case 介面
 * 
 * - **提供更新角色資料的功能**
 * 
 * @author hank Created on 2025/02/08.
 * @author hank Updated on 2025/02/08 something note here.
 */
public interface UpmsRoleUpdateUseCase {

	/**
	 * 📌 更新角色資訊
	 * 
	 * @param id      角色 ID
	 * @param request 更新請求 DTO
	 * @return 更新後的 `UpmsRoleResponseDTO`
	 */
	UpmsRoleResponseDTO update(UUID roleUuid, UpmsRoleUpdateDTO request);

}
