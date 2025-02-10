package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsRoleCreateDTO;
import com.xk.upms.application.model.UpmsRoleResponseDTO;

/**
 * 📌 UpmsRoleCreateUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者角色創建邏輯**
 * 
 * @author hank Created on 2025/02/07.
 */
public interface UpmsRoleCreateUseCase {

	UpmsRoleResponseDTO create(UpmsRoleCreateDTO request);

}
