package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.po.UpmsAction;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import com.xk.upms.domain.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

	private final AdmSystemService admSystemService;

	private final UpmsRoleService upmsRoleService;

	private final UpmsPermissionService upmsPermissionService;

	private final UpmsRolePermissionService upmsRolePermissionService;

	private final UpmsRolePermissionActionService upmsRolePermissionActionService;

	private final UpmsActionService upmsActionService;

	@Override
	@Transactional
	public void update(UUID systemUuid, UUID roleUuid, UpmsPermissionUpdateDTO request) {
		log.info("📌 更新系統UUID: {}, 角色UUID: {}", systemUuid, roleUuid);

		AdmSystemBO admSystemBO = admSystemService.findByUuid(systemUuid)
				.orElseThrow(() -> new EntityNotFoundException("系統不存在: " + systemUuid));
		UpmsRoleBO upmsRoleBO = upmsRoleService.findByUuid(roleUuid)
				.orElseThrow(() -> new EntityNotFoundException("角色不存在: " + roleUuid));
		// 取得權限清單
		List<UpmsPermission> upmsPermissions = upmsPermissionService.findAll();
		Map<UUID, UpmsPermission> upmsPermissionMap = upmsPermissions
				.stream()
				.collect(Collectors.toMap(
						UpmsPermission::getUuid,
						up -> up
				));
		// 取得動作清單
		List<UpmsAction> upmsActions = upmsActionService.findAll();
		Map<UUID, UpmsAction> upmsActionMap = upmsActions
				.stream()
				.collect(Collectors.toMap(
						UpmsAction::getUuid,
						ua -> ua
				));
		//角色原有權限id
		List<UpmsRolePermission> existingRolePermissions = upmsRolePermissionService.findAll(systemUuid, roleUuid);
		List<UUID> existingPermissionUuids = existingRolePermissions.stream().map(UpmsRolePermission::getPermissionUuid).collect(Collectors.toList());
		//角色原有控制權限動作
		List<UpmsRolePermissionAction> existingRolePermissionActions = upmsRolePermissionActionService.findAllIn(roleUuid, existingPermissionUuids);
		//刪除角色原有權限清單
		upmsRolePermissionService.deleteAll(existingRolePermissions);
		//刪除所有權限動作
		upmsRolePermissionActionService.deleteAll(existingRolePermissionActions);
		//重新加入 角色權限 及動作
		List<UpmsRolePermission> rolePermissions = new ArrayList<>();
		List<UpmsRolePermissionAction> rolePermissionActions = new ArrayList<>();
		for(UpmsPermissionUpdateDTO.Permission permission : request.permissions()){
			setPermission(
					permission,
					systemUuid,
					roleUuid,
					upmsPermissionMap,
					upmsActionMap,
					rolePermissions,
					rolePermissionActions
			);
			for(UpmsPermissionUpdateDTO.Permission subPermission : permission.permissions()){
				setPermission(
						subPermission,
						systemUuid,
						roleUuid,
						upmsPermissionMap,
						upmsActionMap,
						rolePermissions,
						rolePermissionActions
				);
			}
		}
		upmsRolePermissionService.saveAll(rolePermissions);
		upmsRolePermissionActionService.saveAll(rolePermissionActions);
	}

	private void setPermission(
			UpmsPermissionUpdateDTO.Permission permission,
			UUID systemUuid,
			UUID roleUuid,
			Map<UUID, UpmsPermission> permissionMap,
			Map<UUID, UpmsAction> actionMap,
			List<UpmsRolePermission> newPermissions,
			List<UpmsRolePermissionAction> newActions) {
		UpmsPermission checkPermission = permissionMap.get(permission.uuid());
		if (null == checkPermission) {
			return;
		}
		UpmsRolePermission newPermission = new UpmsRolePermission();
		newPermission.setPermissionUuid(checkPermission.getUuid());
		newPermission.setRoleUuid(roleUuid);
		newPermission.setSystemUuid(systemUuid);
		newPermission.setUpdatedBy(""); // 更新人員
		newPermission.setActive(permission.active());
		newPermissions.add(newPermission);

		if (null == permission.actions()) {
			return;
		}

		for (UpmsPermissionUpdateDTO.Action action : permission.actions()) {
			UpmsAction checkAction = actionMap.get(action.uuid());
			if (null == checkAction) {
				continue;
			}
			UpmsRolePermissionAction newAction = new UpmsRolePermissionAction();
			newAction.setRoleUuid(roleUuid);
			newAction.setPermissionUuid(checkPermission.getUuid());
			newAction.setActionUuid(checkAction.getUuid());
			newAction.setUpdatedBy(""); // 更新人員
			newAction.setActive(action.active());
			newActions.add(newAction);
		}

	}

}
