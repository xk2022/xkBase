package com.xk.upms.domain.service.impl;

import com.xk.adm.domain.dao.repository.AdmSystemRepository;
import com.xk.adm.domain.model.po.AdmSystemPO;
import com.xk.common.util.XkJwtUtil;
import com.xk.common.util.dto.JwtUserDTO;
import com.xk.upms.domain.dao.repository.*;
import com.xk.upms.domain.model.bo.UpmsUserBO;
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
	public JwtUserDTO extract(UpmsUserBO upmsUserBO) {
		// 取得角色
		Optional<UpmsRole> upmsRole = upmsRoleRepository.findUserRoleByUserUuid(upmsUserBO.getUuid());
		if(!upmsRole.isPresent()){
			return null;
		}
		// 取得角色系統清單
		List<UpmsRoleSystem> upmsRoleSystems = upmsRoleSystemRepository.findByDeletedFalseAndRoleUuidOrderByUuidAsc(upmsRole.get().getUuid());
		if(null == upmsRoleSystems || upmsRoleSystems.isEmpty()){
			return null;
		}
		// 取得系統清單
		List<UUID> systemUuids = upmsRoleSystems.stream().map(UpmsRoleSystem::getSystemUuid).collect(Collectors.toList());
		List<AdmSystemPO> admSystemPOS = admSystemRepository.findByDeletedFalseAndEnabledTrueAndUuidIn(systemUuids);
		List<JwtUserDTO.SystemDTO> systemDTOS = convertSystem(admSystemPOS, upmsRole.get());
		JwtUserDTO jwtUserDTO = new JwtUserDTO();
		jwtUserDTO.setUserUuid(upmsUserBO.getUuid());
		jwtUserDTO.setAccount(upmsUserBO.getAccount());
		jwtUserDTO.setUserName(upmsUserBO.getUsername());
		jwtUserDTO.setEmail(upmsUserBO.getEmail());
		jwtUserDTO.setCellPhone(upmsUserBO.getCellPhone());
		jwtUserDTO.setRoleUuid(upmsRole.get().getUuid());
		jwtUserDTO.setEnable(upmsUserBO.getEnabled());
		jwtUserDTO.setLock(upmsUserBO.getLocked());
		jwtUserDTO.setSystemDTOs(systemDTOS);
		String token = XkJwtUtil.generateToken(jwtUserDTO);
		jwtUserDTO.setToken(token);
		return jwtUserDTO;
	}

	private List<JwtUserDTO.SystemDTO> convertSystem(
			List<AdmSystemPO> admSystemPOS,
			UpmsRole upmsRole){
		List<JwtUserDTO.SystemDTO> systemDTOS = new ArrayList<>();
		JwtUserDTO.SystemDTO systemDTO;
		List<JwtUserDTO.SystemDTO.PermissionDTO> permissionDTOS;
		for(AdmSystemPO admSystemPO : admSystemPOS){
			// 取得角色權限清單
			List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionRepository.findByDeletedFalseAndSystemUuidAndRoleUuid(
					admSystemPO.getUuid(),
					upmsRole.getUuid()
			);
			// 取得角色權限動作清單
			List<UUID> permissionUuids = upmsRolePermissions.stream().map(UpmsRolePermission::getPermissionUuid).collect(Collectors.toList());
			List<UpmsRolePermissionAction> upmsRolePermissionActions = upmsRolePermissionActionRepository.findByDeletedFalseAndRoleUuidAndPermissionUuidIn(
					upmsRole.getUuid(),
					permissionUuids
			);
			List<UUID> actionUuids = upmsRolePermissionActions.stream().map(UpmsRolePermissionAction::getActionUuid).collect(Collectors.toList());
			// 取得權限清單
			List<UpmsPermission> upmsPermissions = upmsPermissionRepository.findByDeletedFalseAndUuidInOrderByOrdersAsc(permissionUuids);
			// 取得動作清單
			List<UpmsAction> upmsActions = upmsActionRepository.findByDeletedFalseAndUuidInOrderByOrdersAsc(actionUuids);
			permissionDTOS = convertPermission(upmsPermissions, upmsActions, upmsRolePermissions, upmsRolePermissionActions);
			systemDTO = new JwtUserDTO.SystemDTO();
			systemDTO.setSystemUuid(admSystemPO.getUuid());
			systemDTO.setName(admSystemPO.getName());
			systemDTO.setPermissionDTOS(permissionDTOS);
			systemDTOS.add(systemDTO);
		}
		return systemDTOS;
	}

	private List<JwtUserDTO.SystemDTO.PermissionDTO> convertPermission(
			List<UpmsPermission> upmsPermissions,
			List<UpmsAction> upmsActions,
			List<UpmsRolePermission> upmsRolePermissions,
			List<UpmsRolePermissionAction> upmsRolePermissionActions) {

		List<JwtUserDTO.SystemDTO.PermissionDTO> permissionDTOS = new ArrayList<>();

		// 父權限
		List<UpmsPermission> parents = upmsPermissions.stream()
				.filter(p -> p.getPUuid() == null)
				.collect(Collectors.toList());

		// 子權限Map
		Map<UUID, List<UpmsPermission>> childPermissionMap = upmsPermissions.stream()
				.filter(p -> p.getPUuid() != null)
				.collect(Collectors.groupingBy(UpmsPermission::getPUuid));

		// 權限 active map（permissionId -> active）
		Map<UUID, Boolean> permissionActiveMap = upmsRolePermissions.stream()
				.collect(Collectors.toMap(
						UpmsRolePermission::getPermissionUuid,
						UpmsRolePermission::getActive
				));

		// 動作 active map（permissionUuid -> Map<actionUuid, active>）
		Map<UUID, Map<UUID, Boolean>> actionActiveMap = upmsRolePermissionActions.stream()
				.collect(Collectors.groupingBy(
						UpmsRolePermissionAction::getPermissionUuid,
						Collectors.toMap(
								UpmsRolePermissionAction::getActionUuid,
								UpmsRolePermissionAction::getActive
						)
				));

		// 轉換父權限及子權限
		for (UpmsPermission parent : parents) {
			List<JwtUserDTO.SystemDTO.PermissionDTO> childPermissions = new ArrayList<>();
			List<UpmsPermission> children = childPermissionMap.getOrDefault(parent.getUuid(), List.of());
			for (UpmsPermission child : children) {
				List<JwtUserDTO.SystemDTO.PermissionDTO.Action> actions = new ArrayList<>();
				Map<UUID, Boolean> childActionMap = actionActiveMap.getOrDefault(child.getUuid(), Map.of());
				for (UpmsAction action : upmsActions) {
					JwtUserDTO.SystemDTO.PermissionDTO.Action dtoAction = new JwtUserDTO.SystemDTO.PermissionDTO.Action();
					dtoAction.setUuid(action.getUuid());
					dtoAction.setName(action.getName());
					dtoAction.setActive(childActionMap.getOrDefault(action.getUuid(), false));
					actions.add(dtoAction);
				}
				JwtUserDTO.SystemDTO.PermissionDTO childDTO = new JwtUserDTO.SystemDTO.PermissionDTO();
				childDTO.setUuid(child.getUuid());
				childDTO.setName(child.getName());
				childDTO.setActive(permissionActiveMap.getOrDefault(child.getUuid(), false));
				childDTO.setActions(actions);
				childPermissions.add(childDTO);
			}
			JwtUserDTO.SystemDTO.PermissionDTO parentDTO = new JwtUserDTO.SystemDTO.PermissionDTO();
			parentDTO.setUuid(parent.getUuid());
			parentDTO.setName(parent.getName());
			parentDTO.setActive(permissionActiveMap.getOrDefault(parent.getUuid(), false));
			parentDTO.setPermissionDTOs(childPermissions);
			permissionDTOS.add(parentDTO);
		}
		return permissionDTOS;
	}

}
