package com.xk.exapmleFolder.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xk.exapmleFolder.application.model.DemoResponseDTO;
/**
 * 📌 OrderFindUseCase.java（應用層 Use Case - 查詢）
 * 
 * - 查詢單筆訂單（依據訂單 ID）
 * - 查詢某個客戶的所有訂單
 * - 查詢所有訂單（可能用於後台管理）
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 */
public interface DemoFindUseCase {

	/**
	 * 📌 依據 `orderId` 查詢單筆訂單
	 * 
	 * @param orderId
	 * @return
	 */
    DemoResponseDTO findById(Long orderId);
    
    /**
     * 📌 依據 `customerId` 查詢某個客戶的所有訂單（支援分頁）
     * 
     * @param customerId
     * @param pageable
     * @return
     */
    Page<DemoResponseDTO> findByCustomerId(String customerId, Pageable pageable);

    /**
     * 📌 查詢所有訂單（後台管理用，支援分頁）
     * 
     * @param pageable
     * @return
     */
    Page<DemoResponseDTO> findAllOrders(Pageable pageable);
    
}
