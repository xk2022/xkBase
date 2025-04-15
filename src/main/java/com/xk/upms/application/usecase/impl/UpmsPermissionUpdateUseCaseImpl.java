package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.model.UpmsRolePermissionRequestDTO;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionBO;
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
import org.springframework.util.CollectionUtils;

import java.util.*;
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
	public UpmsPermissionResponseDTO update(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request) {
		log.info("📌 更新系統ID: {}, 角色ID: {}", systemUuid, roleId);

		UpmsPermissionResponseDTO responseDTO = new UpmsPermissionResponseDTO();
		List<UpmsPermissionResponseDTO> permissions = new ArrayList<>();
		List<UpmsPermissionResponseDTO.Action> actions = new ArrayList<>();

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
		//角色原有 控制權限動作
		List<UpmsRolePermissionAction> existingRolePermissionAction = upmsRolePermissionActionService.findAllIn(roleId,existingPermissionIds);
		//刪除角色原有權限清單
		List<UpmsRolePermission> deletedRolePermissions = upmsRolePermissionService.deleteAll(existingRolePermissions);
		//刪除所有權限動作
		List<UpmsRolePermissionAction> deletedRolePermissionActions = upmsRolePermissionActionService.deleteAll(existingRolePermissionAction);
		upmsRolePermissionService.deleteAll(deletedRolePermissions);
		upmsRolePermissionActionService.deleteAll(deletedRolePermissionActions);
		//重新加入 角色權限 及動作
		List<UpmsRolePermission> rolePermissions = new ArrayList<>();
		List<UpmsRolePermissionAction> rolePermissionActions = new ArrayList<>();
		for(UpmsPermissionUpdateDTO.Permission permission : request.permissions()){
			UpmsPermission upmsPermission = upmsPermissionMap.get(permission.id());
			if(null == upmsPermission){
				continue;
			}
			// upmsrolepermission add
			UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
			upmsRolePermission.setPermissionId(upmsPermission.getId());
			upmsRolePermission.setRoleId(roleId);
			upmsRolePermission.setSystemUuid(systemUuid);
			upmsRolePermission.setUpdatedBy("");//更新人員
			upmsRolePermission.setActive(upmsPermission.getStatus());
			rolePermissions.add(upmsRolePermission);

			//response
			UpmsPermissionResponseDTO permissionResponseDTO = new UpmsPermissionResponseDTO();
			permissionResponseDTO.setId(upmsPermission.getId());
			permissionResponseDTO.setName(upmsPermission.getName());
			permissionResponseDTO.setActive(upmsPermission.getStatus());
			permissions.add(permissionResponseDTO);

			//upmsrolepermissionaction add
			for(UpmsPermissionUpdateDTO.Action action : permission.actions()){
				UpmsAction upmsAction = upmsActionMap.get(action.id());
				if(null == upmsAction){
					continue;
				}
				UpmsRolePermissionAction upmsRolePermissionAction = new UpmsRolePermissionAction();
				upmsRolePermissionAction.setRoleId(roleId);
				upmsRolePermissionAction.setPermissionId(upmsPermission.getId());
				upmsRolePermissionAction.setActionId(upmsAction.getId());
				upmsRolePermissionAction.setUpdatedBy("");//更新人員
				upmsRolePermissionAction.setActive(upmsAction.getActive());
				rolePermissionActions.add(upmsRolePermissionAction);
				//response
				UpmsPermissionResponseDTO.Action actionDTO = new UpmsPermissionResponseDTO.Action();
				actionDTO.setId(upmsAction.getId());
				actionDTO.setName(upmsAction.getName());
				actionDTO.setActive(upmsAction.getActive());
				actions.add(actionDTO);
			}
		}
		upmsRolePermissionService.saveAll(rolePermissions);
		upmsRolePermissionActionService.saveAll(rolePermissionActions);
		responseDTO.setPermissions(permissions);
		responseDTO.setActions(actions);
		return responseDTO;
	}

}
