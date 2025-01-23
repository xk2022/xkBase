package com.xk.exapmleFolder.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xk.exapmleFolder.application.mapper.DemoMapper;
import com.xk.exapmleFolder.application.model.DemoRequestDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;
import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;
import com.xk.exapmleFolder.domain.service.DemoService;
import com.xk.exapmleFolder.exception.DemoNotFoundException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 DemoUpdateUseCase - 訂單更新業務邏輯
 * 
 * - ✅ 查找訂單，確保存在
 * - ✅ **檢查業務邏輯**（不能修改已完成訂單）
 * - ✅ **更新 `items`**
 * - ✅ **儲存變更**
 * - ✅ **回傳 `DemoResponseDTO`**
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Slf4j
@Service
public class DemoUpdateUseCase {

    @Autowired
    private DemoService demoService;

    @Transactional // ✅ 需要寫入變更
    public DemoResponseDTO updateOrder(Long orderId, DemoRequestDTO request) {
        log.info("📌 嘗試更新訂單，ID: {}", orderId);

        // ✅ 查找訂單，若不存在則拋出異常
        DemoAggregate order = demoService.findById(orderId)
                .orElseThrow(() -> new DemoNotFoundException(orderId));

        // ✅ 檢查業務邏輯
        validateUpdatable(order);

        // ✅ 更新訂單內容
        order.updateOrder(request.getItems());

        // ✅ 保存變更
        demoService.save(order);
        log.info("✅ 訂單更新成功，ID: {}", order.getId());

        // ✅ 直接回傳 DTO（減少 Optional 包裝）
        return DemoMapper.toResponseDTO(order);
    }

    /**
     * 📌 檢查訂單是否可以更新
     */
    private void validateUpdatable(DemoAggregate order) {
        if (order.isCompleted()) {
            log.warn("⚠️ 訂單 ID: {} 已完成，無法修改", order.getId());
            throw new IllegalStateException("訂單已完成，無法修改");
        }
    }
    
}
