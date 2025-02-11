package com.xk.upms.domain.service.impl;

import org.springframework.stereotype.Service;

import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.service.UpmsPermissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `PermissionServiceImpl` - 權限服務的具體實作
 * 
 * - **提供基本的 CRUD 操作** - **支援條件查詢** - **確保與 `Repository` 交互的邏輯**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionServiceImpl implements UpmsPermissionService {
	
	@Override
	public UpmsPermissionBO save(UpmsPermissionBO permission) {
		// TODO Auto-generated method stub
		return null;
	}

}
