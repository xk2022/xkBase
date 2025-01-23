package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleCreateUseCase;
import com.xk.exapmleFolder.domain.model.example.ExamplePO;
import com.xk.exapmleFolder.domain.service.ExampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UserCreateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者創建邏輯**
 * - **調用 `Domain` 層的業務邏輯**
 * - **確保數據完整性**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleCreateUseCaseImpl implements ExampleCreateUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ExampleResponseDTO create(ExampleRequestDTO request) {
        log.info("📌 開始創建新使用者: {}", request.getUsername());
        // ✅ 轉換 DTO -> Domain
        ExamplePO user = XkBeanUtils.copyProperties(request, ExamplePO::new);
        // ✅ 儲存到 DB
        ExamplePO savedUser = userService.save(user);
        // ✅ 轉換回 DTO
        return XkBeanUtils.copyProperties(savedUser, ExampleResponseDTO::new);
    }
    
}
