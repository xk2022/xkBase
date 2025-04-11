package com.xk.upms.application.usecase.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.xk.upms.application.model.UpmsRoleFindDTO;
import com.xk.upms.domain.model.po.UpmsRoleSystem;
import com.xk.upms.domain.service.UpmsRoleSystemService;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleFindUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.service.UpmsRoleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsRoleFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者角色查詢邏輯**
 * 
 * @author hank Created on 2025/02/07.
 * @author hank Updated on 2025/02/07 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleFindUseCaseImpl implements UpmsRoleFindUseCase {

	private final UpmsRoleService upmsRoleService;

	private final UpmsRoleSystemService upmsRoleSystemService;

	@Override
	public UpmsRoleResponseDTO findById(Long Id) {
		UpmsRoleBO roleBO = upmsRoleService.findById(Id).orElseThrow(() -> new EntityNotFoundException("角色不存在: " + Id));
		List<UpmsRoleSystem> upmsRoleSystems = upmsRoleSystemService.findAllByRoleId(roleBO.getId());
		UpmsRoleResponseDTO upmsRoleResponseDTO = XkBeanUtils.copyProperties(roleBO, UpmsRoleResponseDTO::new);
		List<UUID> systemUuids = upmsRoleSystems.stream().map(UpmsRoleSystem::getSystemUuid).collect(Collectors.toList());
		upmsRoleResponseDTO.setSystemUuids(systemUuids);
		return upmsRoleResponseDTO;
	}

	@Override
	public List<UpmsRoleResponseDTO> findAll(UpmsRoleFindDTO request) {
		log.info("📌 查詢所有角色 ");

		List<UpmsRoleBO> roleBOList = upmsRoleService.findAll(request.keyword());
		List<UpmsRoleSystem> upmsRoleSystems = upmsRoleSystemService.findAll();
		List<UpmsRoleResponseDTO> responseDTOS = XkBeanUtils.copyListProperties(roleBOList, UpmsRoleResponseDTO::new);
		List<UUID> systemUuids;
		for(UpmsRoleResponseDTO upmsRoleResponseDTO : responseDTOS){
			systemUuids = upmsRoleSystems
					.stream()
					.filter(urs -> urs.getRoleId().equals(upmsRoleResponseDTO.getId()))
					.map(UpmsRoleSystem::getSystemUuid)
					.collect(Collectors.toList());
			upmsRoleResponseDTO.setSystemUuids(systemUuids);
		}
		return responseDTOS;
	}

}
