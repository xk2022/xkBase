package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;

import java.util.UUID;

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
	 * @param uuid    使用者 UUID
	 * @param request 更新請求 DTO
	 * @return 更新後的 `UpmsUserResponseDTO`
	 */
	UpmsUserResponseDTO update(UUID uuid, UpmsUserUpdateDTO request);

}
