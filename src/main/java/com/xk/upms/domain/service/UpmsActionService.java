package com.xk.upms.domain.service;

import java.util.Optional;

import com.xk.upms.domain.model.bo.UpmsActionBO;

/**
 * 📌 `UpmsActionService` - 使用者領域服務
 * 
 * - **負責處理 action 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21
 */
public interface UpmsActionService {

	// ============= 🔵【R】Read（查詢）================
	/**
	 * 📌 依據 ID 查詢單筆動作
	 * 
	 * @param actionId 使用者 ID
	 * @return 使用者物件（若存在）
	 */
	Optional<UpmsActionBO> findById(Long actionId);

}
