package com.xk.upms.application.usecase.impl;

import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.model.UpmsRoleUpdateDTO;
import com.xk.upms.application.usecase.UpmsRoleUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.po.UpmsRoleSystem;
import com.xk.upms.domain.service.UpmsRoleService;
import com.xk.upms.domain.service.UpmsRoleSystemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 📌 `UpmsRoleUpdateUseCaseImpl` - 角色更新 Use Case 實作
 * 
 * - **提供更新使用者資訊的業務邏輯** 
 * - **確保 `Application Layer` 與 `Domain Layer` 分離** 
 * - **透過`Domain Service` 進行數據存取**
 * 
 * @author hank Created on 2025/02/08.
 * @author hank Updated on 2025/02/08 something note here.
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UpmsRoleUpdateUseCaseImpl implements UpmsRoleUpdateUseCase {

	private final UpmsRoleService upmsRoleService;

	private final UpmsRoleSystemService upmsRoleSystemService;

	@Override
	public UpmsRoleResponseDTO update(UUID roleUuid, UpmsRoleUpdateDTO request) {
		log.info("📌 更新使用者角色 UUID: {}", roleUuid);
		
		UpmsRoleBO upmsRoleBO = upmsRoleService.findByUuid(roleUuid)
				.orElseThrow(() -> new EntityNotFoundException(String.format("角色UUID %d 不存在，更新失敗", roleUuid)));
		List<UpmsRoleSystem> oldUpmsRoleSystems = upmsRoleSystemService.findAllByRoleUuid(roleUuid);
		// ✅ 更新必要欄位（但不影響 ID）
		GenericUpdateService<UpmsRoleBO> updateService = new GenericUpdateService<>();
		UpmsRoleBO updatedEntity = updateService.updateEntity(upmsRoleBO, request);
		// ✅ 儲存變更
		UpmsRoleBO savedEntity = upmsRoleService.update(roleUuid, updatedEntity);
		// 轉換角色系統清單
		List<UpmsRoleSystem> newUpmsRoleSystems = convert(request.systemUuids(), roleUuid);
		// 刪除舊的角色系統清單
		upmsRoleSystemService.deleteAll(oldUpmsRoleSystems);
		// 新增新的角色系統清單
		upmsRoleSystemService.createAll(newUpmsRoleSystems);
		// ✅ 回傳 DTO
		return XkBeanUtils.copyProperties(savedEntity, UpmsRoleResponseDTO::new);
	}

	private List<UpmsRoleSystem> convert(List<UUID> systemUuids, UUID roleUuid){
		List<UpmsRoleSystem> upmsRoleSystems = new ArrayList<>();
		UpmsRoleSystem upmsRoleSystem;
		for(UUID systemUuid : systemUuids){
			upmsRoleSystem = new UpmsRoleSystem();
			upmsRoleSystem.setRoleUuid(roleUuid);
			upmsRoleSystem.setSystemUuid(systemUuid);
			upmsRoleSystems.add(upmsRoleSystem);
		}
		return upmsRoleSystems;
	}

}
