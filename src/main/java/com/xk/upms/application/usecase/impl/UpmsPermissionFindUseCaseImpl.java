package com.xk.upms.application.usecase.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.service.UpmsPermissionService;

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
	
	private final UpmsPermissionService upmsPermissionService;

	@Override
	public List<UpmsPermissionResponseDTO> getAllPermission() {
		log.info("📌 查詢所有權限（條件查詢 + 分頁）: {}");
		Sort sort = Sort.by(Sort.Order.asc("id"), Sort.Order.asc("name"));
		List<UpmsPermissionBO> permissions = upmsPermissionService.findAll(sort);
		
		return XkBeanUtils.copyListProperties(permissions, UpmsPermissionResponseDTO::new);
	}

	@Override
	public UpmsPermissionResponseDTO findById(Long permissionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
