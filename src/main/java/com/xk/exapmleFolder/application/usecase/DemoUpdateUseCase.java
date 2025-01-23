package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.DemoRequestDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;

/**
 * 📌 `OrderUpdateUseCase` - 訂單更新應用層 Use Case 介面
 * 
 * - **提供更新訂單資料的功能**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 */
public interface DemoUpdateUseCase {

    /**
     * 📌 更新使用者資訊
     * @param orderId ID
     * @param request 更新請求 DTO
     * @return 更新後的 `DemoResponseDTO`
     */
    DemoResponseDTO updateOrder(Long orderId, DemoRequestDTO request);
	
}
