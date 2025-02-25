package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleCreateUseCase;
import com.xk.exapmleFolder.domain.model.example.EmailVO;
import com.xk.exapmleFolder.domain.model.example.ExampleBO;
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
@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleCreateUseCaseImpl implements ExampleCreateUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ExampleResponseDTO create(ExampleRequestDTO request) {
        log.info("📌 開始創建新使用者: {}", request.getUsername());
        // ✅ 轉換 DTO -> BO
        ExampleBO userBO = XkBeanUtils.copyProperties(request, ExampleBO::new);
        // ✅ 手動處理 EmailVO 轉換
//        userBO.setEmail(new EmailVO(request.getEmail())); // ❗手動轉換 EmailVO
        // ✅ 執行業務邏輯（如 Email 檢查）
        if (!new EmailVO(userBO.getEmail()).isValid()) {
            throw new IllegalArgumentException("無效的 Email 格式");
        }
        // ✅ 儲存到 DB
        ExampleBO savedUser = userService.save(userBO);
        // ✅ 轉換 PO -> DTO 回傳
        return XkBeanUtils.copyProperties(savedUser, ExampleResponseDTO::new);
    }
    
}
