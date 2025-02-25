package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleFindUseCase;
import com.xk.exapmleFolder.domain.model.example.ExampleBO;
import com.xk.exapmleFolder.domain.service.ExampleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UserFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責使用者查詢**
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleFindUseCaseImpl implements ExampleFindUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ExampleResponseDTO getOneById(Long id) {
        log.info("📌 查詢使用者 ID: {}", id);
        // 🔥 查詢使用者，並進行身份驗證
        ExampleBO userBO = userService.findById(id)
        		.orElseThrow(() -> new EntityNotFoundException("使用者不存在: " + id));
        
        if (userBO.isAdmin()) {
            log.info("✅ 使用者 {} 是管理員", userBO.getUsername());
        } else {
            log.info("🔹 使用者 {} 是一般使用者", userBO.getUsername());
        }
        return XkBeanUtils.copyProperties(userBO, ExampleResponseDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExampleResponseDTO findByUsername(String username) {
        log.info("📌 查詢使用者 Username: {}", username);
        ExampleBO user = userService.findByUsername(username)
        		.orElseThrow(() -> new RuntimeException("使用者不存在username: " + username));
        
        return XkBeanUtils.copyProperties(user, ExampleResponseDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExampleResponseDTO> getList(ExampleRequestDTO request, Pageable pageable) {
        log.info("📌 查詢所有使用者（條件查詢 + 分頁）: {}", request);

        Page<ExampleBO> users = userService.findAll(
        		XkBeanUtils.copyProperties(request, ExampleBO::new), pageable);
        return users.map(user -> XkBeanUtils.copyProperties(user, ExampleResponseDTO::new));
    }

}
