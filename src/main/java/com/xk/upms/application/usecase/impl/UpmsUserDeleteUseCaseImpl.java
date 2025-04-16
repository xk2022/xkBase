package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.upms.application.usecase.UpmsUserDeleteUseCase;
import com.xk.upms.domain.service.UpmsUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 📌 `UpmsUserDeleteUseCaseImpl` - 使用者刪除 Use Case 實作
 * 
 * - **提供刪除使用者的業務邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserDeleteUseCaseImpl implements UpmsUserDeleteUseCase {

	private final UpmsUserService upmsUserService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public boolean delete(UUID userUuid) {
		log.info("📌 嘗試刪除使用者 UUID: {}", userUuid);
		boolean deleted = upmsUserService.delete(userUuid);
		if (deleted) {
			log.info("✅ 使用者刪除成功，UUID: {}", userUuid);
		} else {
			log.warn("⚠️ 使用者 UUID: {} 不存在，刪除失敗", userUuid);
		}
		return deleted;
	}

}
