package com.xk.exapmleFolder.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;

/**
 * 📌 UserFindUseCase（應用層 Use Case 介面）
 * 
 * - **提供使用者查找功能**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Impl` 實作具體邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
public interface ExampleFindUseCase {

    /**
     * 📌 依據 `userId` 查詢單筆使用者
     * @param userId 使用者 ID
     * @return 使用者資訊 DTO
     */
    ExampleResponseDTO getOneById(Long userId);

    /**
     * 📌 依據 `username` 查詢單筆使用者
     * @param username 使用者名稱
     * @return 使用者資訊 DTO
     */
    ExampleResponseDTO findByUsername(String username);

    /**
     * 📌 查詢所有使用者（支援分頁）
     * @param pageable 分頁請求
     * @return 分頁使用者列表
     */
	Page<ExampleResponseDTO> getList(ExampleRequestDTO request, Pageable pageable);
    
}
