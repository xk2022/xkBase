package com.xk.upms.domain.service.impl;

import com.xk.adm.domain.dao.repository.AdmSystemRepository;
import com.xk.adm.domain.model.po.AdmSystemPO;
import com.xk.common.util.dto.JwtUserDTO;
import com.xk.common.util.dto.PermissionDTO;
import com.xk.common.util.dto.SystemDTO;
import com.xk.upms.domain.dao.repository.*;
import com.xk.upms.domain.model.po.*;
import com.xk.upms.domain.service.UpmsUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UpmsUserDetailsServiceImpl implements UpmsUserDetailsService {

	private final AdmSystemRepository admSystemRepository;

	private final UpmsRoleRepository upmsRoleRepository;

	private final UpmsRoleSystemRepository upmsRoleSystemRepository;

	private final UpmsPermissionRepository upmsPermissionRepository;

	private final UpmsRolePermissionRepository upmsRolePermissionRepository;

	private final UpmsRolePermissionActionRepository upmsRolePermissionActionRepository;

	private final UpmsActionRepository upmsActionRepository;

	@Override
	public JwtUserDTO extract(UpmsUser upmsUser) {
		// 取得角色
		Optional<UpmsRole> upmsRole = upmsRoleRepository.findUserRoleByUserId(upmsUser.getId());
		if(upmsRole.isPresent()){
			return null;
		}
		// 取得角色系統清單
		List<UpmsRoleSystem> upmsRoleSystems = upmsRoleSystemRepository.findByIsDeletedFalseAndRoleIdOrderByIdAsc(upmsRole.get().getId());
		if(null == upmsRoleSystems || upmsRoleSystems.isEmpty()){
			return null;
		}
		// 取得系統清單
		List<UUID> systemUuids = upmsRoleSystems.stream().map(UpmsRoleSystem::getSystemUuid).collect(Collectors.toList());
		List<AdmSystemPO> admSystemPOS = admSystemRepository.findByDeletedFalseAndEnabledTrueAndUuidIn(systemUuids);
		List<SystemDTO> systemDTOS = admSystemPOS.stream().map(system -> new SystemDTO(system.getUuid(), system.getName())).collect(Collectors.toList());
		// 取得角色權限清單
		List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionRepository.findByIsDeletedFalseAndSystemUuidAndRoleId(
				upmsRoleSystems.get(0).getSystemUuid(),
				upmsRole.get().getId()
		);
		// 取得角色權限動作清單
		List<Long> permissionIds = upmsRolePermissions.stream().map(UpmsRolePermission::getPermissionId).collect(Collectors.toList());
		List<UpmsRolePermissionAction> upmsRolePermissionActions = upmsRolePermissionActionRepository.findByIsDeletedFalseAndRoleIdAndPermissionIdIn(
				upmsRole.get().getId(),
				permissionIds
		);
		List<Long> actionIds = upmsRolePermissionActions.stream().map(UpmsRolePermissionAction::getActionId).collect(Collectors.toList());
		// 取得權限清單
		List<UpmsPermission> upmsPermissions = upmsPermissionRepository.findByIsDeletedFalseAndIdInOrderByOrdersAsc(permissionIds);
		// 取得動作清單
		List<UpmsAction> upmsActions = upmsActionRepository.findByIsDeletedFalseAndIdInOrderByOrdersAsc(actionIds);
		List<PermissionDTO> permissionDTOS = convert(upmsPermissions, upmsActions, upmsRolePermissions, upmsRolePermissionActions);
		JwtUserDTO jwtUserDTO = new JwtUserDTO();
		jwtUserDTO.setUserId(upmsUser.getId());
		jwtUserDTO.setUserName(upmsUser.getUsername());
		jwtUserDTO.setPassword(upmsUser.getPassword());
		jwtUserDTO.setEnable(upmsUser.getEnabled());
		jwtUserDTO.setLock(upmsUser.getLocked());
		jwtUserDTO.setRoleId(upmsRole.get().getId());
		jwtUserDTO.setPermissions(permissionDTOS);
		jwtUserDTO.setSystems(systemDTOS);
		return jwtUserDTO;
	}

	private List<PermissionDTO> convert(
			List<UpmsPermission> upmsPermissions,
			List<UpmsAction> upmsActions,
			List<UpmsRolePermission> upmsRolePermissions,
			List<UpmsRolePermissionAction> upmsRolePermissionActions) {

		List<PermissionDTO> permissionDTOS = new ArrayList<>();

		// 父權限
		List<UpmsPermission> parents = upmsPermissions.stream()
				.filter(p -> p.getPid() == null)
				.collect(Collectors.toList());

		// 子權限Map
		Map<Long, List<UpmsPermission>> childPermissionMap = upmsPermissions.stream()
				.filter(p -> p.getPid() != null)
				.collect(Collectors.groupingBy(UpmsPermission::getPid));

		// 權限 active map（permissionId -> active）
		Map<Long, Boolean> permissionActiveMap = upmsRolePermissions.stream()
				.collect(Collectors.toMap(
						UpmsRolePermission::getPermissionId,
						UpmsRolePermission::getActive
				));

		// 動作 active map（permissionId -> Map<actionId, active>）
		Map<Long, Map<Long, Boolean>> actionActiveMap = upmsRolePermissionActions.stream()
				.collect(Collectors.groupingBy(
						UpmsRolePermissionAction::getPermissionId,
						Collectors.toMap(
								UpmsRolePermissionAction::getActionId,
								UpmsRolePermissionAction::getActive
						)
				));

		// 轉換父權限及子權限
		for (UpmsPermission parent : parents) {
			List<PermissionDTO> childPermissions = new ArrayList<>();
			List<UpmsPermission> children = childPermissionMap.getOrDefault(parent.getId(), List.of());
			for (UpmsPermission child : children) {
				List<PermissionDTO.Action> actions = new ArrayList<>();
				Map<Long, Boolean> childActionMap = actionActiveMap.getOrDefault(child.getId(), Map.of());
				for (UpmsAction action : upmsActions) {
					PermissionDTO.Action dtoAction = new PermissionDTO.Action();
					dtoAction.setId(action.getId());
					dtoAction.setName(action.getName());
					dtoAction.setActive(childActionMap.getOrDefault(action.getId(), false));
					actions.add(dtoAction);
				}
				PermissionDTO childDTO = new PermissionDTO();
				childDTO.setId(child.getId());
				childDTO.setName(child.getName());
				childDTO.setActive(permissionActiveMap.getOrDefault(child.getId(), false));
				childDTO.setActions(actions);
				childPermissions.add(childDTO);
			}
			PermissionDTO parentDTO = new PermissionDTO();
			parentDTO.setId(parent.getId());
			parentDTO.setName(parent.getName());
			parentDTO.setActive(permissionActiveMap.getOrDefault(parent.getId(), false));
			parentDTO.setPermissionDTOs(childPermissions);
			permissionDTOS.add(parentDTO);
		}
		return permissionDTOS;
	}

}
