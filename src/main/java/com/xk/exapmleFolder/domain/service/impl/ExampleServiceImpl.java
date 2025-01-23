package com.xk.exapmleFolder.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.dao.repository.ExampleRepository;
import com.xk.exapmleFolder.domain.model.example.ExampleBO;
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
    @SuppressWarnings("unused")
	@Override
    @Transactional
    public ExampleBO save(ExampleBO userBO) {
    	ExampleBO reslutBo = new ExampleBO();
    	log.info("📌 儲存使用者: {}", userBO.getUsername());
        if (userBO == null) {
            throw new IllegalArgumentException("使用者不能為 null");
        }
        ExamplePO userPO = XkBeanUtils.copyProperties(userBO, ExamplePO::new);
        ExamplePO savedPO = userRepository.save(userPO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ExampleBO> findById(Long userId) {
        log.info("📌 查詢使用者 ID: {}", userId);
        return userRepository.findById(userId)
                .map(examplePO -> new ExampleBO(
                		examplePO.getUsername(),
                		examplePO.getEmail(), // ✅ 直接使用 EmailVO
                        null
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ExampleBO> findByUsername(String username) {
        log.info("📌 查詢使用者，username: {}", username);
        return userRepository.findByUsername(username)
                .map(examplePO -> new ExampleBO(
                		examplePO.getUsername(),
                		examplePO.getEmail(), // ✅ 直接使用 EmailVO
                        null
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExampleBO> findAll(Pageable pageable) {
        log.info("📌 查詢所有使用者 (分頁)");
        return userRepository.findAll(pageable)
                .map(examplePO -> new ExampleBO(
                		examplePO.getUsername(),
                		examplePO.getEmail(), // ✅ 直接使用 EmailVO
                        null
                ));
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public List<ExampleBO> findAll() {
	    return XkBeanUtils.copyListProperties(userRepository.findAll(), ExampleBO::new);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public ExampleBO update(Long userId, ExampleBO updateData) {
    	ExampleBO reslutBo = new ExampleBO();
    	log.info("📌 儲存使用者: {}", updateData.getUsername());
        ExamplePO userPO = XkBeanUtils.copyProperties(updateData, ExamplePO::new);
        userPO.setId(userId);
        ExamplePO savedPO = userRepository.save(userPO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
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
    public Page<ExampleBO> findAll(ExampleBO request, Pageable pageable) {
        log.info("📌 查詢所有使用者 (支援條件過濾 + 分頁)");

        Example<ExamplePO> example = buildExample(request);
        
        return userRepository.findAll(example, pageable)
                .map(examplePO -> new ExampleBO(
                        examplePO.getUsername(),
                        examplePO.getEmail(), // ✅ EmailVO 直接傳遞
                        null
                ));
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ExampleBO> findAll(Example<ExamplePO> example) {
	    log.info("📌 查詢所有使用者 (支援條件過濾)");
	    return XkBeanUtils.copyListProperties(userRepository.findAll(example), ExampleBO::new);
	}
	
	private Example<ExamplePO> buildExample(ExampleBO request) {
	    ExampleMatcher matcher = ExampleMatcher.matching()
//	            .withIgnorePaths("email") // ✅ 忽略 `EmailVO`，避免 JPA 解析錯誤
	            .withIgnoreNullValues()
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
	            .withIgnoreCase();

	    return Example.of(XkBeanUtils.copyProperties(request, ExamplePO::new), matcher);
	}

}
