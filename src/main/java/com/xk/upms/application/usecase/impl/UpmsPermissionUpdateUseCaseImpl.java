package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsPermissionUpdateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責更新權限**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionUpdateUseCaseImpl  implements UpmsPermissionUpdateUseCase {
	
	@Override
	public UpmsPermissionResponseDTO update(Long id, UpmsUserUpdateDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

}
