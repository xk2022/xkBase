package com.xk.upms.application.usecase.impl;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;
import com.xk.upms.domain.model.po.UpmsAction;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.service.UpmsActionService;
import com.xk.upms.domain.service.UpmsRolePermissionActionService;
import com.xk.upms.domain.service.UpmsPermissionService;
import com.xk.upms.domain.service.UpmsRolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

	private final UpmsRolePermissionService upmsRolePermissionService;

	private final UpmsActionService upmsActionService;

	private final UpmsRolePermissionActionService upmsRolePermissionActionService;

	@Override
	public List<UpmsPermissionResponseDTO> findAll(UUID systemUuid, UUID roleUuid) {
		// 取得預設的權限清單
		List<UpmsPermission> defaultUpmsPermissions = upmsPermissionService.findAll();
		List<UUID> defaultUpmsPermissionUuids = defaultUpmsPermissions.stream().map(UpmsPermission::getUuid).collect(Collectors.toList());
		List<UpmsRolePermissionAction> defaultUpmsRolePermissionActions = upmsRolePermissionActionService.findAllIn(null, defaultUpmsPermissionUuids);
		List<UpmsAction> defaultUpmsActions = upmsActionService.findAll();

		// 該角色的權限清單
		List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionService.findAll(systemUuid, roleUuid);
		List<UUID> upmsPermissionUuids = upmsRolePermissions.stream().map(UpmsRolePermission::getPermissionUuid).collect(Collectors.toList());
		List<UpmsRolePermissionAction> upmsRolePermissionActions = upmsRolePermissionActionService.findAllIn(roleUuid, upmsPermissionUuids);
		return convert(
				defaultUpmsPermissions,
				defaultUpmsRolePermissionActions,
				defaultUpmsActions,
				upmsRolePermissions,
				upmsRolePermissionActions
		);
	}

	// 轉換權限
	private List<UpmsPermissionResponseDTO> convert(
			List<UpmsPermission> defaultUpmsPermissions,
			List<UpmsRolePermissionAction> defaultUpmsRolePermissionActions,
			List<UpmsAction> defaultUpmsActions,
			List<UpmsRolePermission> upmsRolePermissions,
			List<UpmsRolePermissionAction> upmsRolePermissionActions) {

		// rolePermission 取得啟用的 permissionUuid
		Set<UUID> enabledPermissionUuids = upmsRolePermissions.stream()
				.filter(UpmsRolePermission::getActive)
				.map(UpmsRolePermission::getPermissionUuid)
				.collect(Collectors.toSet());

		// permissionAction 取得啟用的 permissionUuid-actionUuid 組合
		Set<String> enabledPermissionActionKeys = upmsRolePermissionActions.stream()
				.filter(UpmsRolePermissionAction::getActive)
				.map(pa -> pa.getPermissionUuid() + "-" + pa.getActionUuid())
				.collect(Collectors.toSet());

		// permissionUuid -> action list 的 map
		Map<UUID, List<UpmsRolePermissionAction>> defaultActionMap = defaultUpmsRolePermissionActions.stream()
				.collect(Collectors.groupingBy(UpmsRolePermissionAction::getPermissionUuid));

		// actionUuid -> action 物件的 map
		Map<UUID, UpmsAction> actionUuidMap = defaultUpmsActions.stream()
				.collect(Collectors.toMap(UpmsAction::getUuid, a -> a));

		// 權限
		List<UpmsPermission> parents = defaultUpmsPermissions.stream()
				.filter(p -> p.getPUuid() == null)
				.collect(Collectors.toList());

		Map<UUID, List<UpmsPermission>> childPermissionMap = defaultUpmsPermissions.stream()
				.filter(p -> p.getPUuid() != null)
				.collect(Collectors.groupingBy(UpmsPermission::getPUuid));

		// 組成 DTO
		List<UpmsPermissionResponseDTO> result = new ArrayList<>();
		for (UpmsPermission parent : parents) {
			UpmsPermissionResponseDTO parentDTO = new UpmsPermissionResponseDTO();
			parentDTO.setUuid(parent.getUuid());
			parentDTO.setName(parent.getName());
			// 包含代表啟用
			parentDTO.setActive(enabledPermissionUuids.contains(parent.getUuid()));
			List<UpmsPermission> children = childPermissionMap.getOrDefault(parent.getUuid(), new ArrayList<>());
			List<UpmsPermissionResponseDTO> childDTOList = new ArrayList<>();
			for (UpmsPermission child : children) {
				UpmsPermissionResponseDTO childDTO = new UpmsPermissionResponseDTO();
				childDTO.setUuid(child.getUuid());
				childDTO.setName(child.getName());
				// 包含代表啟用
				childDTO.setActive(enabledPermissionUuids.contains(child.getUuid()));
				// 取出對應的權限動作
				List<UpmsRolePermissionAction> actions = defaultActionMap.getOrDefault(child.getUuid(), new ArrayList<>());
				List<UpmsPermissionResponseDTO.Action> actionDTOs = actions.stream()
						.map(pa -> {
							UpmsAction action = actionUuidMap.get(pa.getActionUuid());
							// 根據動作是否啟用來設置
							boolean isActive = enabledPermissionActionKeys.contains(child.getUuid() + "-" + action.getUuid());
							return new UpmsPermissionResponseDTO.Action(
									action.getUuid(),
									action.getName(),
									isActive
							);
						}).collect(Collectors.toList());
				childDTO.setActions(actionDTOs);
				childDTOList.add(childDTO);
			}
			parentDTO.setPermissions(childDTOList);
			result.add(parentDTO);
		}
		return result;
	}

}
