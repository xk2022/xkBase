package com.xk.exapmleFolder.application.usecase;

import org.springframework.stereotype.Service;

import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;
import com.xk.exapmleFolder.domain.service.DemoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 OrderCancelUseCase - 訂單取消業務邏輯（應用層）
 *
 * - 負責調用 `DemoService` 執行取消邏輯
 * - 確保業務邏輯完整性，避免錯誤操作
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DemoCancelUseCase {

    private final DemoService demoService;

    /**
     * 📌 取消訂單
     * 
     * @param orderId 訂單 ID
     * @return 是否成功取消
     */
    @Transactional
    public boolean cancelOrder(Long orderId) {
        log.info("📌 嘗試取消訂單，ID: {}", orderId);

        // ✅ 取得訂單，若不存在則拋出異常
        DemoAggregate order = demoService.findById(orderId)
                .orElseThrow(() -> {
                    log.warn("⚠️ 訂單 ID: {} 不存在，無法取消", orderId);
                    return new IllegalArgumentException("訂單不存在");
                });

        // ✅ 確保訂單尚未完成
        if (order.isCompleted()) {
            log.warn("⚠️ 訂單 ID: {} 已完成，無法取消", orderId);
            throw new IllegalStateException("訂單已完成，無法取消");
        }

        // ✅ 執行取消操作
        order.cancel();
        demoService.save(order);

        log.info("✅ 訂單取消成功，ID: {}", orderId);
        return true;
    }
    
}