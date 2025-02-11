package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;

/**
 * 📌 `UpmsUserUpdateUseCase` - 使用者更新應用層 Use Case 介面
 * 
 * - **提供更新使用者資料的功能**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserUpdateUseCase {

	/**
	 * 📌 更新使用者資訊
	 * 
	 * @param id      使用者 ID
	 * @param request 更新請求 DTO
	 * @return 更新後的 `UpmsUserResponseDTO`
	 */
	UpmsUserResponseDTO update(Long id, UpmsUserUpdateDTO request);

}
