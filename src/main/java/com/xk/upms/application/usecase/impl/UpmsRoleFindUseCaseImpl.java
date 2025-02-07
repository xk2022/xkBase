package com.xk.upms.application.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleFindUseCase;
import com.xk.upms.domain.dao.repository.UpmsUserRoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsRoleFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者角色查詢邏輯**
 * 
 * @author hank Created on 2025/02/07.
 * @author hank Updated on 2025/02/07 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleFindUseCaseImpl implements UpmsRoleFindUseCase {
	
	private final UpmsUserRoleRepository upmsUserRoleRepository;
	
	@Override
	public Optional<UpmsRoleResponseDTO> findById(Long Id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<UpmsRoleResponseDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
