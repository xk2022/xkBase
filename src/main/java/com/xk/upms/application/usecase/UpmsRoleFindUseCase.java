package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsRoleResponseDTO;

/**
 * 📌 UpmsUserFindUseCase（應用層 Use Case 介面）
 * 
 * - **提供使用者查找功能**
 * 
 * @author hank Created on 2025/02/06.
 * @author hank Updated on 2025/02/06 something note here.
 */
public interface UpmsRoleFindUseCase {

	UpmsRoleResponseDTO findById(Long id);
		
}
