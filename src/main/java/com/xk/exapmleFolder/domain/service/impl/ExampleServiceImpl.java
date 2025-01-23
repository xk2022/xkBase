package com.xk.exapmleFolder.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.exapmleFolder.domain.dao.repository.ExampleRepository;
import com.xk.exapmleFolder.domain.model.example.ExamplePO;
import com.xk.exapmleFolder.domain.service.ExampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UserServiceImpl` - 使用者領域服務的具體實作
 * 
 * - **提供基本的 CRUD 操作**
 * - **支援條件查詢**
 * - **確保與 `Repository` 交互的邏輯**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository userRepository;

    /**
     * {@inheritDoc}
     * 📌 @inheritDoc 的適用範圍
     * 適用於 interface（介面）到 class（類別）的實作
     * 適用於 abstract class（抽象類別）到 concrete class（具體類別）
     * 適用於 superclass（父類別）到 subclass（子類別）
     * 
     * ✅ `save()` 應該直接回傳 `ExamplePO`
     * ✅ `findById()` 使用 `Optional`，確保呼叫端處理缺少的值
     */
    @Override
    @Transactional
    public ExamplePO save(ExamplePO user) {
        if (user == null) {
            throw new IllegalArgumentException("使用者不能為 null");
        }
        return userRepository.save(user);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ExamplePO> findById(Long userId) {
        log.info("📌 查詢使用者 ID: {}", userId);
        return userRepository.findById(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ExamplePO> findByUsername(String username) {
        log.info("📌 查詢使用者，username: {}", username);
        return userRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExamplePO> findAll(Pageable pageable) {
        log.info("📌 查詢所有使用者 (分頁)");
        return userRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public List<ExamplePO> findAll() {
	    return userRepository.findAll();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Optional<ExamplePO> update(Long userId, ExamplePO updateData) {
	    return userRepository.findById(userId)
	            .map(existingUser -> {
	                // ✅ 更新欄位（避免覆蓋 NULL）
	                if (updateData.getUsername() != null) {
	                    existingUser.setUsername(updateData.getUsername());
	                }
	                if (updateData.getEmail() != null) {
	                    existingUser.setEmail(updateData.getEmail());
	                }

	                // ✅ 儲存變更
	                return userRepository.save(existingUser);
	            });
	}

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean delete(Long userId) {
        log.info("📌 嘗試刪除使用者 ID: {}", userId);
        return userRepository.findById(userId)
                .map(user -> {
                	userRepository.delete(user);
                    log.info("✅ 使用者 ID: {} 已刪除", userId);
                    return true;
                }).orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExamplePO> findAll(Example<ExamplePO> example, Pageable pageable) {
        log.info("📌 查詢所有使用者 (支援條件過濾 + 分頁)");
        return userRepository.findAll(example, pageable);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ExamplePO> findAll(Example<ExamplePO> example) {
	    log.info("📌 查詢所有使用者 (支援條件過濾)");
	    return userRepository.findAll(example);
	}
    
}
