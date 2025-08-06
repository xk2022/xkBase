package com.xk.tom.domain.dao.repository;

import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 OrderRepository (泛型基底介面)
 * <p>
 * - 泛型 T = Domain Entity 類型
 * - 定義通用的存取方法
 *
 * @param <T> 訂單實體 (ImportOrderEntity / ExportOrderEntity)
 * @author yuan Created on 2025/08/05.
 */
public interface OrderRepository<T> {

    /**
     * 儲存訂單
     */
    T save(T entity);

    /**
     * 根據 UUID 查詢訂單
     */
    Optional<T> findByUuid(UUID uuid);

    /**
     * 根據狀態查詢訂單
     */
    List<T> findByStatus(OrderStatus status);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);
}