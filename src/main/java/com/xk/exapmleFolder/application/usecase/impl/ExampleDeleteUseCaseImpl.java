package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.exapmleFolder.application.usecase.ExampleDeleteUseCase;
import com.xk.exapmleFolder.domain.service.ExampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UserDeleteUseCaseImpl` - 使用者刪除 Use Case 實作
 * 
 * - **提供刪除使用者的業務邏輯**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Domain Service` 進行數據存取**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleDeleteUseCaseImpl implements ExampleDeleteUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean delete(Long userId) {
        log.info("📌 嘗試刪除使用者 ID: {}", userId);

        boolean deleted = userService.delete(userId);

        if (deleted) {
            log.info("✅ 使用者刪除成功，ID: {}", userId);
        } else {
            log.warn("⚠️ 使用者 ID: {} 不存在，刪除失敗", userId);
        }
        return deleted;
    }
    
}
