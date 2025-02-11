package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.usecase.UpmsPermissionCreateUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsPermissionCreateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責新增權限**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionCreateUseCaseImpl implements UpmsPermissionCreateUseCase {

	@Override
	public UpmsPermissionResponseDTO create(UpmsUserCreateDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

}
