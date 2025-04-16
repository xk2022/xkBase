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
	public List<UpmsPermissionResponseDTO> findAll(UUID systemUuid, Long roleId) {
		// 取得預設的權限清單
		List<UpmsPermission> defaultUpmsPermissions = upmsPermissionService.findAll();
		List<Long> defaultUpmsPermissionIds = defaultUpmsPermissions.stream().map(UpmsPermission::getId).collect(Collectors.toList());
		List<UpmsRolePermissionAction> defaultUpmsRolePermissionActions = upmsRolePermissionActionService.findAllIn(null, defaultUpmsPermissionIds);
		List<UpmsAction> defaultUpmsActions = upmsActionService.findAll();

		// 該角色的權限清單
		List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionService.findAll(systemUuid, roleId);
		List<Long> upmsPermissionIds = upmsRolePermissions.stream().map(UpmsRolePermission::getPermissionId).collect(Collectors.toList());
		List<UpmsRolePermissionAction> upmsRolePermissionActions = upmsRolePermissionActionService.findAllIn(roleId, upmsPermissionIds);
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

		// rolePermission 取得啟用的 permissionId
		Set<Long> enabledPermissionIds = upmsRolePermissions.stream()
				.filter(UpmsRolePermission::getActive)
				.map(UpmsRolePermission::getPermissionId)
				.collect(Collectors.toSet());

		// permissionAction 取得啟用的 permissionId-actionId 組合
		Set<String> enabledPermissionActionKeys = upmsRolePermissionActions.stream()
				.filter(UpmsRolePermissionAction::getActive)
				.map(pa -> pa.getPermissionId() + "-" + pa.getActionId())
				.collect(Collectors.toSet());

		// permissionId -> action list 的 map
		Map<Long, List<UpmsRolePermissionAction>> defaultActionMap = defaultUpmsRolePermissionActions.stream()
				.collect(Collectors.groupingBy(UpmsRolePermissionAction::getPermissionId));

		// actionId -> action 物件的 map
		Map<Long, UpmsAction> actionIdMap = defaultUpmsActions.stream()
				.collect(Collectors.toMap(UpmsAction::getId, a -> a));

		// 權限
		List<UpmsPermission> parents = defaultUpmsPermissions.stream()
				.filter(p -> p.getPid() == null)
				.collect(Collectors.toList());

		Map<Long, List<UpmsPermission>> childPermissionMap = defaultUpmsPermissions.stream()
				.filter(p -> p.getPid() != null)
				.collect(Collectors.groupingBy(UpmsPermission::getPid));

		// 組成 DTO
		List<UpmsPermissionResponseDTO> result = new ArrayList<>();
		for (UpmsPermission parent : parents) {
			UpmsPermissionResponseDTO parentDTO = new UpmsPermissionResponseDTO();
			parentDTO.setId(parent.getId());
			parentDTO.setName(parent.getName());
			// 包含代表啟用
			parentDTO.setActive(enabledPermissionIds.contains(parent.getId()));
			List<UpmsPermission> children = childPermissionMap.getOrDefault(parent.getId(), new ArrayList<>());
			List<UpmsPermissionResponseDTO> childDTOList = new ArrayList<>();
			for (UpmsPermission child : children) {
				UpmsPermissionResponseDTO childDTO = new UpmsPermissionResponseDTO();
				childDTO.setId(child.getId());
				childDTO.setName(child.getName());
				// 包含代表啟用
				childDTO.setActive(enabledPermissionIds.contains(child.getId()));
				// 取出對應的權限動作
				List<UpmsRolePermissionAction> actions = defaultActionMap.getOrDefault(child.getId(), new ArrayList<>());
				List<UpmsPermissionResponseDTO.Action> actionDTOs = actions.stream()
						.map(pa -> {
							UpmsAction action = actionIdMap.get(pa.getActionId());
							// 根據動作是否啟用來設置
							boolean isActive = enabledPermissionActionKeys.contains(child.getId() + "-" + action.getId());
							return new UpmsPermissionResponseDTO.Action(
									action.getId(),
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
