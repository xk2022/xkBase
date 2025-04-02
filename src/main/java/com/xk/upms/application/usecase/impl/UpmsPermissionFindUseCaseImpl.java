package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.usecase.UpmsPermissionFindUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.po.UpmsAction;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.model.po.UpmsPermissionAction;
import com.xk.upms.domain.service.UpmsActionService;
import com.xk.upms.domain.service.UpmsPermissionActionService;
import com.xk.upms.domain.service.UpmsPermissionService;
import com.xk.upms.domain.service.UpmsRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

	private final UpmsActionService upmsActionService;

	private final UpmsPermissionActionService upmsPermissionActionService;

	@Override
	public List<UpmsPermissionResponseDTO> findAll(UUID systemUuid, Long roleId) {
		List<UpmsPermission> upmsPermissions = upmsPermissionService.findAll(systemUuid, roleId);
		List<Long> permissionIds = upmsPermissions.stream().map(UpmsPermission::getId).collect(Collectors.toList());
		List<UpmsPermissionAction> upmsPermissionActions = upmsPermissionActionService.findAllIn(permissionIds);
		List<Long> actionIds = upmsPermissionActions.stream().map(UpmsPermissionAction::getActionId).collect(Collectors.toList());
		List<UpmsAction> upmsActions = upmsActionService.findAllIn(actionIds);
		return convert(upmsPermissions, upmsPermissionActions, upmsActions);
	}

	private List<UpmsPermissionResponseDTO> convert(
		List<UpmsPermission> upmsPermissions,
		List<UpmsPermissionAction> upmsPermissionActions,
		List<UpmsAction> upmsActions){
		List<UpmsPermissionResponseDTO> response = new ArrayList<>();
		if(null == upmsPermissions || upmsPermissions.isEmpty() || null == upmsPermissionActions || upmsPermissionActions.isEmpty()){
			return response;
		}
		List<UpmsPermission> parentUpmsPermission = upmsPermissions.stream().filter(upmsPermission -> null == upmsPermission.getPid()).collect(Collectors.toList());
		List<UpmsPermissionResponseDTO.Permission> permissions;
		List<UpmsPermissionResponseDTO.Permission> subPermissions;
		UpmsPermissionResponseDTO.Permission permission;
		UpmsPermissionResponseDTO.Permission subPermission;
		List<UpmsPermissionResponseDTO.Permission.Action> actions;
		for(UpmsPermission upmsPermission : parentUpmsPermission){
			permissions = new ArrayList<>();
			for(UpmsPermission subUpmsPermission : upmsPermissions){
				if(!subUpmsPermission.getPid().equals(upmsPermission.getId())){
					continue;
				}
				subPermissions = new ArrayList<>();
				for(UpmsPermissionAction upmsPermissionAction : upmsPermissionActions){
					if(!upmsPermission.getId().equals(upmsPermissionAction.getPermissionId())){
						continue;
					}
					actions = findAllActions(
							upmsActions
									.stream()
									.filter(upmsAction -> upmsAction.getId().equals(upmsPermissionAction.getActionId()))
									.collect(Collectors.toList()));
					subPermission = new UpmsPermissionResponseDTO.Permission();
					subPermission.setId(subUpmsPermission.getId());
					subPermission.setName(subUpmsPermission.getName());
					subPermission.setActions(actions);
					subPermission.setStatus(subUpmsPermission.getStatus());
					subPermissions.add(subPermission);
				}
			}
			permission = new UpmsPermissionResponseDTO.Permission();
			permission.setId(upmsPermission.getId());
			permission.setName(upmsPermission.getName());
			permission.setActions(null);
			permission.setStatus(upmsPermission.getStatus());
			permissions.add(permission);
		}
		return response;
	}

	private List<UpmsPermissionResponseDTO.Permission.Action> findAllActions(List<UpmsAction> upmsActions){
		List<UpmsPermissionResponseDTO.Permission.Action> actions = new ArrayList<>();
		if(null == upmsActions || upmsActions.isEmpty()){
			return actions;
		}
		UpmsPermissionResponseDTO.Permission.Action action;
		for(UpmsAction upmsAction : upmsActions){
			action = new UpmsPermissionResponseDTO.Permission.Action();
			action.setId(upmsAction.getId());
			action.setName(upmsAction.getName());
			action.setActive(upmsAction.getActive());
			actions.add(action);
		}
		return actions;
	}

}
