package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.DemoRequestDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;

/**
 * 📌 OrderCreateUseCase.java（應用層 Use Case - 創建訂單）
 * 
 * - **接收 `DemoRequestDTO`**
 * - **轉換為 `DemoAggregate`**
 * - **檢查訂單金額**
 * - **執行業務邏輯**
 * - **發送 `DemoPlacedEvent`**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 */
public interface DemoCreateUseCase {

    /**
     * 📌 創建訂單（核心業務流程）
     * 
     * @param request 定安請求 DTO
     * @return 回應 DTO
     */
    DemoResponseDTO createOrder(DemoRequestDTO request);

}
