package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsRoleBO;

/**
 * 📌 `UpmsRoleService` - 角色領域服務
 * 
 * - **負責處理 User 領域內的商業邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsRoleService {
	
	UpmsRoleBO save(UpmsRoleBO role);

}
