package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsPermissionBO;

/**
 * 📌 `UpmsPermissionService` - 使用者領域服務
 * 
 * - **負責處理 Permission 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
public interface UpmsPermissionService {

	// ============= 🟢【C】Create（創建）================
	/**
	 * 📌 創建權限（回傳 `Optional<T>` 以避免 `null`）
	 * 
	 * @param permission 權限物件
	 * @return 儲存後的權限資訊
	 */
	UpmsPermissionBO save(UpmsPermissionBO permission);

}
