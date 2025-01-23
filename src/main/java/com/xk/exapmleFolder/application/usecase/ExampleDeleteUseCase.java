package com.xk.exapmleFolder.application.usecase;

/**
 * 📌 `UserDeleteUseCase` - 使用者刪除應用層 Use Case 介面
 * 
 * - **提供刪除使用者的功能**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
public interface ExampleDeleteUseCase {

    /**
     * 📌 刪除使用者
     * 
     * @param userId 使用者 ID
     * @return 是否成功刪除
     */
    boolean delete(Long userId);

}
