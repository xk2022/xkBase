package com.xk.upms.domain.service;

import java.util.Optional;

import com.xk.upms.domain.model.po.UpmsRole;

/**
 * 📌 `UpmsRoleService` - 使用者權限領域服務
 * 
 * - **負責處理 UserRole 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/04.
 * @author hank Updated on 2025/02/04 something note here.
 */
public interface UpmsRoleService {
	
	

	
	/**
	 * 查詢使用者角色
	 * @param userId 使用者ID
	 * @author hank
	 * @return Optional
	 */
	Optional<UpmsRole> findById(String code);
	
}
