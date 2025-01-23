package com.xk.exapmleFolder.application.usecase;

/**
 * 📌 `OrderCancelUseCase` - 訂單取消應用層 Use Case 介面
 * 
 * - **提供取消訂單的功能**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23
 */
public interface DemoCancelUseCase {

    /**
     * 📌 取消訂單
     * 
     * @param orderId 訂單 ID
     * @return 是否成功取消
     */
    boolean cancelOrder(Long orderId);
    
}
