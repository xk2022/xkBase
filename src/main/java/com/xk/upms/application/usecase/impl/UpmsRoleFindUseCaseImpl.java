package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleFindUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class UpmsRoleFindUseCaseImpl implements UpmsRoleFindUseCase {
	
	
	@Override
	public UpmsRoleResponseDTO findById(Long id) {
		
		return null;
	}


	

}
