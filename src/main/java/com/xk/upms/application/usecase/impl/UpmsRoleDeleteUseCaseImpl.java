package com.xk.upms.application.usecase.impl;

import com.xk.upms.domain.model.po.UpmsRoleSystem;
import com.xk.upms.domain.service.UpmsRoleSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.upms.application.usecase.UpmsRoleDeleteUseCase;
import com.xk.upms.domain.service.UpmsRoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

/**
 * 📌 UpmsRoleDeleteUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責角色刪除**
 * 
 * @author hank Created on 2025/02/10.
 * @author hank Updated on 2025/01/10 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UpmsRoleDeleteUseCaseImpl implements UpmsRoleDeleteUseCase {

	private final UpmsRoleService upmsRoleService;

	private final UpmsRoleSystemService upmsRoleSystemService;

	@Override
	@Transactional
	public boolean delete(UUID roleUuid) {
		log.info("📌 嘗試刪除角色 UUID: {}", roleUuid);
		
		boolean deleted = upmsRoleService.delete(roleUuid);
		// 取得角色系統清單
		List<UpmsRoleSystem> upmsRoleSystems = upmsRoleSystemService.findAllByRoleUuid(roleUuid);
		// 刪除角色系統清單
		upmsRoleSystemService.deleteAll(upmsRoleSystems);
		if (deleted) {
			log.info("✅ 使用者刪除成功，ID: {}", roleUuid);
		} else {
			log.warn("⚠️ 使用者 ID: {} 不存在，刪除失敗", roleUuid);
		}
		return deleted;
	}

}
