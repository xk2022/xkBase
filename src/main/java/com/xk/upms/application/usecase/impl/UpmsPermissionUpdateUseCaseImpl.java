package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
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
	public void update(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request) {
		log.info("📌 更新系統ID: {}, 角色ID: {}", systemUuid, roleId);

		AdmSystemBO admSystemBO = admSystemService.findById(systemUuid)
				.orElseThrow(() -> new EntityNotFoundException("系統不存在: " + systemUuid));
		UpmsRoleBO upmsRoleBO = upmsRoleService.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException("角色不存在: " + roleId));
		// 取得權限清單
		List<UpmsPermission> upmsPermissions = upmsPermissionService.findAll();
		Map<Long, UpmsPermission> upmsPermissionMap = upmsPermissions
				.stream()
				.collect(Collectors.toMap(
						UpmsPermission::getId,
						up -> up
				));
		// 取得動作清單
		List<UpmsAction> upmsActions = upmsActionService.findAll();
		Map<Long, UpmsAction> upmsActionMap = upmsActions
				.stream()
				.collect(Collectors.toMap(
						UpmsAction::getId,
						ua -> ua
				));
		//角色原有權限id
		List<UpmsRolePermission> existingRolePermissions = upmsRolePermissionService.findAll(systemUuid, roleId);
		List<Long> existingPermissionIds = existingRolePermissions.stream().map(UpmsRolePermission::getPermissionId).collect(Collectors.toList());
		//角色原有控制權限動作
		List<UpmsRolePermissionAction> existingRolePermissionActions = upmsRolePermissionActionService.findAllIn(roleId, existingPermissionIds);
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
					roleId,
					upmsPermissionMap,
					upmsActionMap,
					rolePermissions,
					rolePermissionActions
			);
			for(UpmsPermissionUpdateDTO.Permission subPermission : permission.permissions()){
				setPermission(
						subPermission,
						systemUuid,
						roleId,
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
			Long roleId,
			Map<Long, UpmsPermission> permissionMap,
			Map<Long, UpmsAction> actionMap,
			List<UpmsRolePermission> newPermissions,
			List<UpmsRolePermissionAction> newActions) {
		UpmsPermission checkPermission = permissionMap.get(permission.id());
		if (null == checkPermission) {
			return;
		}
		UpmsRolePermission newPermission = new UpmsRolePermission();
		newPermission.setPermissionId(checkPermission.getId());
		newPermission.setRoleId(roleId);
		newPermission.setSystemUuid(systemUuid);
		newPermission.setUpdatedBy(""); // 更新人員
		newPermission.setActive(permission.active());
		newPermissions.add(newPermission);

		if (null == permission.actions()) {
			return;
		}

		for (UpmsPermissionUpdateDTO.Action action : permission.actions()) {
			UpmsAction foundAction = actionMap.get(action.id());
			if (foundAction == null) {
				continue;
			}
			UpmsRolePermissionAction newAction = new UpmsRolePermissionAction();
			newAction.setRoleId(roleId);
			newAction.setPermissionId(checkPermission.getId());
			newAction.setActionId(foundAction.getId());
			newAction.setUpdatedBy(""); // 更新人員
			newAction.setActive(action.active());
			newActions.add(newAction);
		}

	}

}
