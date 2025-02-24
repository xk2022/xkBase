package com.xk.upms.domain.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xk.upms.domain.dao.repository.UpmsActionRepository;
import com.xk.upms.domain.model.bo.UpmsActionBO;
import com.xk.upms.domain.service.UpmsActionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsActionServiceImpl` - 操作動作領域服務的具體實作
 * 
 * - **提供基本的 CRUD 操作** - **支援條件查詢** - **確保與 `Repository` 交互的邏輯**
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsActionServiceImpl implements UpmsActionService {

	private final UpmsActionRepository upmsActionRepository;

	@Override
	public Optional<UpmsActionBO> findById(Long actionId) {
		log.info("📌 查詢動作 ID: {}", actionId);

		return upmsActionRepository.findById(actionId)
				.map(action -> new UpmsActionBO(action.getId(), action.getActionName(), action.getPermissionId(),
						action.getUrl(), action.getOrders(), action.getMethod(), action.getActive(),
						action.getIsDeleted(), action.getDeleteUser(), action.getDeleteTime()));
	}

}
