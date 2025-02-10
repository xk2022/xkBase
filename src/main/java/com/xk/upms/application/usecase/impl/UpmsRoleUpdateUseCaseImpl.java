package com.xk.upms.application.usecase.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.model.UpmsRoleUpdateDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsRoleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsRoleUpdateUseCaseImpl` - 角色更新 Use Case 實作
 * 
 * - **提供更新使用者資訊的業務邏輯** - **確保 `Application Layer` 與 `Domain Layer` 分離** - **透過
 * `Domain Service` 進行數據存取**
 * 
 * @author hank Created on 2025/02/08.
 * @author hank Updated on 2025/02/08 something note here.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleUpdateUseCaseImpl implements UpmsRoleUpdateUseCase {

	private final UpmsRoleService upmsRoleService;

	@Override
	public UpmsRoleResponseDTO update(Long id, UpmsRoleUpdateDTO request) {
		log.info("📌 更新使用者角色 ID: {}", id);
		UpmsRoleBO upmsRoleBO = upmsRoleService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("角色ID %d 不存在，更新失敗", id)));

		// ✅ 更新必要欄位（但不影響 ID）
		GenericUpdateService<UpmsRoleBO> updateService = new GenericUpdateService<>();
		UpmsRoleBO updatedEntity = updateService.updateEntity(upmsRoleBO, request);
		// ✅ 儲存變更
		UpmsRoleBO savedEntity = upmsRoleService.update(id, updatedEntity);
		// ✅ 回傳 DTO
		return XkBeanUtils.copyProperties(savedEntity, UpmsRoleResponseDTO::new);

	}

}
