package com.xk.upms.application.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleFindUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.service.UpmsRoleService;

import jakarta.persistence.EntityNotFoundException;
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

	private final UpmsRoleService upmsRoleService;

	@Override
	public UpmsRoleResponseDTO findById(Long Id) {
		UpmsRoleBO roleBO = upmsRoleService.findById(Id).orElseThrow(() -> new EntityNotFoundException("角色不存在: " + Id));
		return XkBeanUtils.copyProperties(roleBO,UpmsRoleResponseDTO::new);
	}

	@Override
	public List<UpmsRoleResponseDTO> findAll() {
		log.info("📌 查詢所有角色 ");
		List<UpmsRoleBO> roleBOList = upmsRoleService.findAll();
		return XkBeanUtils.copyListProperties(roleBOList ,UpmsRoleResponseDTO::new);
	}

}
