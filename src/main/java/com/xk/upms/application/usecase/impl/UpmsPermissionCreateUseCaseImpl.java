package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsPermissionCreateDTO;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.usecase.UpmsPermissionCreateUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.service.UpmsPermissionService;

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

	private final UpmsPermissionService upmsPermissionService;

	@Override
	public UpmsPermissionResponseDTO create(UpmsPermissionCreateDTO request) {
		log.info("📌 開始創建新權限: {}", request.permissions());
		// ✅ 轉換 DTO -> BO
		UpmsPermissionBO permissionBO = XkBeanUtils.copyProperties(request, UpmsPermissionBO::new);
		// ✅ 儲存到 DB
		UpmsPermissionBO savedPermission = upmsPermissionService.save(permissionBO);
		// ✅ 轉換 PO -> DTO 回傳

		return XkBeanUtils.copyProperties(savedPermission, UpmsPermissionResponseDTO::new);
	}

}
