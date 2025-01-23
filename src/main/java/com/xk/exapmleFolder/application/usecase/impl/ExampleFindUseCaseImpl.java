package com.xk.exapmleFolder.application.usecase.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.ExampleRequestDTO;
import com.xk.exapmleFolder.application.model.ExampleResponseDTO;
import com.xk.exapmleFolder.application.usecase.ExampleFindUseCase;
import com.xk.exapmleFolder.domain.model.example.ExamplePO;
import com.xk.exapmleFolder.domain.service.ExampleService;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleFindUseCaseImpl implements ExampleFindUseCase {

    private final ExampleService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ExampleResponseDTO getOneById(Long id) {
        log.info("📌 查詢使用者 ID: {}", id);
        ExamplePO upmsUser = userService.findById(id)
        		.orElseThrow(() -> new RuntimeException("使用者不存在: " + id));
        
        return XkBeanUtils.copyProperties(upmsUser, ExampleResponseDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExampleResponseDTO findByUsername(String username) {
        log.info("📌 查詢使用者 Username: {}", username);
        ExamplePO user = userService.findByUsername(username)
        		.orElseThrow(() -> new RuntimeException("使用者不存在username: " + username));
        
        return XkBeanUtils.copyProperties(user, ExampleResponseDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExampleResponseDTO> getList(ExampleRequestDTO request, Pageable pageable) {
        log.info("📌 查詢所有使用者（條件查詢 + 分頁）: {}", request);

        Example<ExamplePO> example = Example.of(XkBeanUtils.copyProperties(request, ExamplePO::new),
                ExampleMatcher.matching()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreCase());

        Page<ExamplePO> users = userService.findAll(example, pageable);
        return users.map(user -> XkBeanUtils.copyProperties(user, ExampleResponseDTO::new));
    }

}
