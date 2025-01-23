package com.xk.exapmleFolder.domain.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;

/**
 * 📌 `OrderService`（訂單領域服務）
 * 
 * ✅ **統一業務邏輯** 
 * ✅ **減少應用層邏輯重複** 
 * ✅ **確保訂單狀態變更符合業務規則**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
public interface DemoService {

	/** 📌 依據 `orderId` 查詢訂單 */
	Optional<DemoAggregate> findById(Long orderId);

	/** 📌 儲存（新增 / 更新）訂單 */
	void save(DemoAggregate order);

	/** 📌 更新訂單（確保變更符合業務規則） */
	DemoAggregate update(Long orderId, DemoAggregate updatedOrder);

	/** 📌 依據 `customerId` 查詢某客戶的所有訂單（分頁） */
	Page<DemoAggregate> findByCustomerId(String customerId, Pageable pageable);

	/** 📌 查詢所有訂單（分頁） */
	Page<DemoAggregate> findAll(Pageable pageable);

	/**
	 * 📌 檢查訂單是否符合業務邏輯 ✅ 確保至少有一個商品 ✅ 確保金額 > 0
	 */
	boolean validateOrder(DemoAggregate order);

	/** 📌 檢查訂單金額是否符合條件 */
	boolean validateOrderAmount(DemoAggregate order);

	/** 📌 計算訂單的總價 */
	BigDecimal calculateTotalAmount(DemoAggregate order);

}
