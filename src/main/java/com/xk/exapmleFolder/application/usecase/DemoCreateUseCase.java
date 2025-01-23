package com.xk.exapmleFolder.application.usecase;

import org.springframework.stereotype.Service;

import com.xk.exapmleFolder.application.mapper.DemoMapper;
import com.xk.exapmleFolder.application.model.DemoRequestDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;
import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.exapmleFolder.domain.service.DemoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 DemoCreateUseCase.java（應用層 Use Case - 創建訂單）
 * 
 * - **接收 `DemoRequestDTO`**
 * - **轉換為 `DemoAggregate`**
 * - **檢查訂單金額**
 * - **執行業務邏輯**
 * - **發送 `DemoPlacedEvent`**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DemoCreateUseCase {

	private final DemoService demoService;

    /**
     * 📌 創建訂單（核心業務流程）
     */
    @Transactional
    public DemoResponseDTO createOrder(DemoRequestDTO request) {
        log.info("📌 開始創建訂單，客戶 ID：{}", request.getCustomerId());

        // ✅ 轉換 DTO → Domain
        DemoAggregate order = DemoAggregate.builder()
                .customerId(request.getCustomerId())
                .status(OrderStatusEnum.PENDING)
                .build();

        // ✅ 確保 items 與 order 關聯
        order.setItems(DemoMapper.toDomainList(request.getItems(), order));

        // ✅ 使用 `DemoService` 驗證訂單
        if (!demoService.validateOrder(order)) {
            throw new IllegalStateException("⚠️ 訂單不符合規則！");
        }

        // ✅ 存入 DB
        demoService.save(order);

        return DemoMapper.toResponseDTO(order);
    }

}
