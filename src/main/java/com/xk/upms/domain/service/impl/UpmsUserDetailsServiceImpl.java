package com.xk.upms.domain.service.impl;

import com.xk.common.util.dto.JwtUserDTO;
import com.xk.upms.domain.dao.repository.*;
import com.xk.upms.domain.model.po.UpmsRole;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.model.po.UpmsUser;
import com.xk.upms.domain.service.UpmsUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpmsUserDetailsServiceImpl implements UpmsUserDetailsService {

	private final UpmsRoleRepository upmsRoleRepository;

	private final UpmsRolePermissionRepository upmsRolePermissionRepository;

	private final UpmsRolePermissionActionRepository upmsRolePermissionActionRepository;

	@Override
	public JwtUserDTO extract(UpmsUser upmsUser) {
		// 取得角色
		Optional<UpmsRole> upmsRole = upmsRoleRepository.findUserRoleByUserId(upmsUser.getId());
		JwtUserDTO jwtUserDTO = new JwtUserDTO();
		jwtUserDTO.setUserId(upmsUser.getId());
		jwtUserDTO.setUserName(upmsUser.getUsername());
		jwtUserDTO.setPassword(upmsUser.getPassword());
		jwtUserDTO.setEnable(upmsUser.getEnabled());
		jwtUserDTO.setLock(upmsUser.getLocked());
		jwtUserDTO.setRoleId(upmsRole.isPresent() ? upmsRole.get().getId() : null);
		return jwtUserDTO;
	}

}
