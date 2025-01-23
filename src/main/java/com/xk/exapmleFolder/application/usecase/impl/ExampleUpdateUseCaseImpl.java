package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleUpdateUseCase;
import com.xk.exapmleFolder.domain.model.example.ExampleBO;
import com.xk.exapmleFolder.domain.service.ExampleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UserUpdateUseCaseImpl` - 使用者更新 Use Case 實作
 * 
 * - **提供更新使用者資訊的業務邏輯**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過 `Domain Service` 進行數據存取**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleUpdateUseCaseImpl implements ExampleUpdateUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ExampleResponseDTO update(Long userId, ExampleRequestDTO request) {
        log.info("📌 更新使用者 ID: {}", userId);

        // ✅ 檢查使用者是否存在
        ExampleBO existingUserBO = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                    String.format("使用者 ID %d 不存在，更新失敗", userId)));
//        log.warn("⚠️ 使用者 ID: {} 不存在，更新失敗", userId);

        // ✅ 更新必要欄位（但不影響 ID）
        GenericUpdateService<ExampleBO> updateService = new GenericUpdateService<>();
        ExampleBO updatedEntity = updateService.updateEntity(existingUserBO, request);
        // ✅ 儲存變更
        ExampleBO savedEntity = userService.update(userId, updatedEntity);
        
//        log.info("✅ 使用者更新成功，ID: {}", savedEntity.getId());
        // ✅ 回傳 DTO
        return XkBeanUtils.copyProperties(savedEntity, ExampleResponseDTO::new);
    }
    
}
