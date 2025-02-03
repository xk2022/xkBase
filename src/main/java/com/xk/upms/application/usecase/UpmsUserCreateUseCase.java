package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;

/**
 * 📌 UpmsUserCreateUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者創建邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserCreateUseCase {

	/**
	 * 📌 創建新使用者
	 * 
	 * @param request 使用者請求 DTO
	 * @return 回應 DTO（包含使用者 ID、名稱、Email）
	 */
	UpmsUserResponseDTO create(UpmsUserCreateDTO request);

}
