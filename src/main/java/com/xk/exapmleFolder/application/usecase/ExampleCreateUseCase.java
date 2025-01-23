package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;

/**
 * 📌 UserCreateUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者創建邏輯**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
public interface ExampleCreateUseCase {

    /**
     * 📌 創建新使用者
     * 
     * @param request 使用者請求 DTO
     * @return 回應 DTO（包含使用者 ID、名稱、Email）
     */
    ExampleResponseDTO create(ExampleRequestDTO request);
    
}
