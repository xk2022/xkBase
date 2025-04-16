package com.xk.upms.application.usecase.impl;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;
import com.xk.upms.domain.service.UpmsUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;
import com.xk.upms.application.usecase.UpmsUserUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 📌 `UserUpdateUseCaseImpl` - 使用者更新 Use Case 實作
 * 
 * - **提供更新使用者資訊的業務邏輯** 
 * - **確保 `Application Layer` 與 `Domain Layer` 分離**
 * - **透過`Domain Service` 進行數據存取**
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserUpdateUseCaseImpl implements UpmsUserUpdateUseCase {

	private final UpmsUserService upmsUserService;

	private final UpmsUserRoleService upmsUserRoleService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUserResponseDTO update(UUID uuid, UpmsUserUpdateDTO request) {
		log.info("📌 更新使用者 UUID: {}", uuid);

		// ✅ 檢查使用者是否存在
		UpmsUserBO existingUserBO = upmsUserService.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException(String.format("使用者 UUID %d 不存在，更新失敗", uuid)));
		// ✅ 更新必要欄位（但不影響 ID）
		GenericUpdateService<UpmsUserBO> updateUserService = new GenericUpdateService<>();
		UpmsUserBO updatedUserEntity = updateUserService.updateEntity(existingUserBO, request);
		// ✅ 儲存變更
		UpmsUserBO savedUserEntity = upmsUserService.update(uuid, updatedUserEntity);
		// 取得使用者角色
		UpmsUserRoleBO existingUserRoleBO = upmsUserRoleService.findByUserId(uuid)
				.orElseThrow(() -> new EntityNotFoundException(String.format("使用者角色 ID %d 不存在，更新失敗", uuid)));
		existingUserRoleBO.setRoleUuid(request.roleUuid());
		// 變更使用者角色
		UpmsUserRoleBO savedUserRoleEntity = upmsUserRoleService.update(existingUserRoleBO);
		// ✅ 回傳 DTO
		return XkBeanUtils.copyProperties(savedUserEntity, UpmsUserResponseDTO::new);
	}

}
