package com.xk.exapmleFolder.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xk.exapmleFolder.application.model.DemoItemDTO;
import com.xk.exapmleFolder.domain.dao.repository.DemoJpaRepository;
import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;
import com.xk.exapmleFolder.domain.service.DemoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `OrderServiceImpl`（訂單領域服務 - 實作）
 * 
 * ✅ 統一業務邏輯，減少應用層重複邏輯
 * ✅ `validateOrder()` 確保訂單符合業務規則
 * ✅ `calculateTotalAmount()` 確保訂單總價計算
 * ✅ `update()` 方法確保狀態變更一致
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoJpaRepository orderRepository;

    @Override
    public Optional<DemoAggregate> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void save(DemoAggregate order) {
        orderRepository.save(order);
    }
    
    @Override
    public DemoAggregate update(Long orderId, DemoAggregate updatedOrder) {
        return findById(orderId)
            .map(existingOrder -> {
                if (existingOrder.isCompleted()) {
                    throw new IllegalStateException("訂單已完成，無法修改");
                }

                // ✅ 將 `List<DemoPO>` 轉換為 `List<DemoItemDTO>`
                List<DemoItemDTO> updatedItems = updatedOrder.getItems().stream()
                    .map(po -> new DemoItemDTO(po.getProductId(), po.getQuantity(), po.getPrice()))
                    .collect(Collectors.toList());

                // ✅ 呼叫 `updateOrder(List<DemoItemDTO>)` 更新訂單
                existingOrder.updateOrder(updatedItems);
                
                return orderRepository.save(existingOrder);
            })
            .orElseThrow(() -> new IllegalArgumentException("訂單不存在: " + orderId));
    }

    @Override
    public Page<DemoAggregate> findByCustomerId(String customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public Page<DemoAggregate> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public boolean validateOrder(DemoAggregate order) {
        return !order.getItems().isEmpty() && validateOrderAmount(order);
    }

    @Override
    public boolean validateOrderAmount(DemoAggregate order) {
        return calculateTotalAmount(order).compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public BigDecimal calculateTotalAmount(DemoAggregate order) {
        return order.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}
