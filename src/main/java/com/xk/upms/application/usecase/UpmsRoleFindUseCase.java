package com.xk.upms.application.usecase;

import java.util.List;

import com.xk.upms.application.model.UpmsRoleResponseDTO;

/**
 * 📌 UpmsRoleFindUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者創建邏輯**
 * 
 * @author hank Created on 2025/02/06.
 * @author hank Updated on 2025/02/06 something note here.
 */
public interface UpmsRoleFindUseCase {

	UpmsRoleResponseDTO findById(Long Id);

	List<UpmsRoleResponseDTO> findAll();
	
}
