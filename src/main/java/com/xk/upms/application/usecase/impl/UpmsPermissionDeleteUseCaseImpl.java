package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.upms.application.usecase.UpmsPermissionDeleteUseCase;
import com.xk.upms.domain.service.UpmsPermissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsPermissionDeleteUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責刪除權限**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionDeleteUseCaseImpl implements UpmsPermissionDeleteUseCase {
	
	private final UpmsPermissionService upmsPermissionService;
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		
	}

}
