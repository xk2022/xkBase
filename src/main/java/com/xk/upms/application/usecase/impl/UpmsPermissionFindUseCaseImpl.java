package com.xk.upms.application.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsPermissionFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責權限查詢**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionFindUseCaseImpl implements UpmsPermissionFindUseCase {

	@Override
	public List<UpmsPermissionResponseDTO> getAllPermission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpmsPermissionResponseDTO findById(Long permissionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
