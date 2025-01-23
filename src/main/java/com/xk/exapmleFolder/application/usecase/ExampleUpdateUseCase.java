package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;

/**
 * 📌 `UserUpdateUseCase` - 使用者更新應用層 Use Case 介面
 * 
 * - **提供更新使用者資料的功能**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
public interface ExampleUpdateUseCase {

    /**
     * 📌 更新使用者資訊
     * @param userId 使用者 ID
     * @param request 更新請求 DTO
     * @return 更新後的 `ExampleResponseDTO`
     */
	ExampleResponseDTO update(Long userId, ExampleRequestDTO request);
	
}
