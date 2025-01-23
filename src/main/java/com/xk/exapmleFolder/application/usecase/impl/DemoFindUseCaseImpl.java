package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.exapmleFolder.application.mapper.DemoMapper;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;
import com.xk.exapmleFolder.application.usecase.DemoFindUseCase;
import com.xk.exapmleFolder.domain.service.DemoService;
import com.xk.exapmleFolder.exception.DemoNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 OrderFindUseCase.java（應用層 Use Case - 查詢）
 * 
 * - 查詢單筆訂單（依據訂單 ID）
 * - 查詢某個客戶的所有訂單
 * - 查詢所有訂單（可能用於後台管理）
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/23 make interface with impl.
 */
@Slf4j
@Service
@Transactional(readOnly = true) // ✅ 避免不必要的事務鎖
@RequiredArgsConstructor
public class DemoFindUseCaseImpl implements DemoFindUseCase {

	private DemoService demoService;

    /**
     * 📌 依據 `orderId` 查詢單筆訂單
     */
	@Cacheable(value = "orders", key = "#orderId") // ✅ 使用 Cache 優化頻繁查詢
    public DemoResponseDTO findById(Long orderId) {
        log.info("📌 查詢訂單，ID: {}", orderId);
        
        return demoService.findById(orderId)
                .map(DemoMapper::toResponseDTO)
                .orElseThrow(() -> {
                    log.warn("⚠️ 訂單 ID: {} 不存在", orderId);
                    return new DemoNotFoundException(orderId);
                });
    }

    /**
     * 📌 依據 `customerId` 查詢某個客戶的所有訂單（支援分頁）
     */
	@Cacheable(value = "customerOrders", key = "#customerId") // ✅ 使用 Cache 優化頻繁查詢
    public Page<DemoResponseDTO> findByCustomerId(String customerId, Pageable pageable) {
        log.info("📌 查詢客戶的所有訂單，客戶 ID：{}", customerId);

        Page<DemoResponseDTO> orders = demoService.findByCustomerId(customerId, pageable)
                .map(DemoMapper::toResponseDTO);

        if (orders.isEmpty()) {
            log.warn("⚠️ 客戶 ID: {} 沒有任何訂單", customerId);
        }
        return orders;
    }


    /**
     * 📌 查詢所有訂單（後台管理用，支援分頁）
     */
	@Cacheable(value = "allOrders", key = "#pageable.pageNumber") // ✅ 使用 Cache 優化頻繁查詢
    public Page<DemoResponseDTO> findAllOrders(Pageable pageable) {
        log.info("📌 查詢所有訂單（分頁）");
        return demoService.findAll(pageable)
                .map(DemoMapper::toResponseDTO);
    }
    
}
