package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.service.UpmsPermissionService;

import jakarta.persistence.EntityNotFoundException;
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
public class UpmsPermissionUpdateUseCaseImpl implements UpmsPermissionUpdateUseCase {

	private final UpmsPermissionService upmsPermissionService;

	@Override
	public UpmsPermissionResponseDTO update(Long permissionId, UpmsPermissionUpdateDTO request) {
		log.info("📌 更新權限 ID: {}", permissionId);
		UpmsPermissionBO upmsPermissionBo = upmsPermissionService.findById(permissionId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("權限 ID %d 不存在，更新失敗 ", permissionId)));
		// ✅ 更新必要欄位（但不影響 ID）
		GenericUpdateService<UpmsPermissionBO> updateService = new GenericUpdateService<>();
		UpmsPermissionBO updateEntity = updateService.updateEntity(upmsPermissionBo, request);
		// ✅ 儲存變更
		UpmsPermissionBO savedEntity = upmsPermissionService.update(permissionId, updateEntity);
		// ✅ 回傳 DTO
		return XkBeanUtils.copyProperties(savedEntity, UpmsPermissionResponseDTO::new);
	}

}
