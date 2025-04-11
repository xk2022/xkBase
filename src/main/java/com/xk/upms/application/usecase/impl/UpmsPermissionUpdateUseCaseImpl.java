package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.model.UpmsRolePermissionRequestDTO;
import com.xk.upms.application.usecase.UpmsPermissionUpdateUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionBO;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.service.UpmsPermissionService;
import com.xk.upms.domain.service.UpmsRolePermissionActionService;
import com.xk.upms.domain.service.UpmsRolePermissionService;
import com.xk.upms.domain.service.UpmsRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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



	@Override
	public UpmsPermissionResponseDTO update(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request) {
		log.info("📌 更新系統ID: {}, 角色ID: {}", systemUuid, roleId);
		AdmSystemBO admSystemBO = admSystemService.findById(systemUuid).orElseThrow(() -> new EntityNotFoundException("系統不存在: " + systemUuid));
		UpmsRoleBO upmsRoleBO = upmsRoleService.findById(roleId).orElseThrow(() -> new EntityNotFoundException("角色不存在: " + roleId));
		UpmsPermissionResponseDTO responseDTO = new UpmsPermissionResponseDTO();
		for(UpmsPermissionUpdateDTO.Permission upmsPermission :request.permissions()){
			//查詢角色原有的權限
			List<UpmsRolePermission> rolePermissions = upmsRolePermissionService.findAll(systemUuid ,roleId);
			boolean isExit = rolePermissions.stream().anyMatch(rolePermission -> rolePermission.getPermissionId().equals(upmsPermission.id()));
			if (!isExit){
				//當不存在 加入
				Optional<UpmsPermissionBO> permissionBO =  upmsPermissionService.findById( systemUuid, upmsPermission.id());
				if (permissionBO.isPresent()){
					UpmsRolePermissionBO rolePermissionBO =new UpmsRolePermissionBO();
					rolePermissionBO.setSystemId(systemUuid);
					rolePermissionBO.setRoleId(roleId);
					rolePermissionBO.setPermissionId(permissionBO.get().getPermissionId());
					rolePermissionBO.setActive(permissionBO.get().getStatus());
					rolePermissionBO.setDeleteUser(permissionBO.get().getDeleteUser());
					rolePermissionBO.setIsDeleted(permissionBO.get().getIsDeleted());
					rolePermissionBO.setDeleteTime(permissionBO.get().getDeleteTime());
					UpmsRolePermissionBO result = upmsRolePermissionService.save(rolePermissionBO);

					//action
					UpmsRolePermissionActionBO upmsRolePermissionActionBO = new UpmsRolePermissionActionBO();
					upmsRolePermissionActionBO.setRoleId(roleId);
					upmsRolePermissionActionBO.setPermissionId(permissionBO.get().getPermissionId());
					upmsRolePermissionActionBO.setActive(permissionBO.get().getStatus());
					upmsRolePermissionActionBO.setIsDeleted(permissionBO.get().getIsDeleted());
					upmsRolePermissionActionBO.setDeleteUser(permissionBO.get().getDeleteUser());
					upmsRolePermissionActionBO.setDeleteTime(permissionBO.get().getDeleteTime());
					upmsRolePermissionActionService.save(upmsRolePermissionActionBO);
				}else {
					throw new EntityNotFoundException("查無此權限");
				}



			}


		}
		return responseDTO;
	}

	// 取得權限清單
	private List<Long> getPermissionIds(List<UpmsPermissionUpdateDTO.Permission> permissions){
		List<Long> permissionIds = new ArrayList<>();
		for(UpmsPermissionUpdateDTO.Permission permission : permissions){
			permissionIds.add(permission.id());
		}
		return permissionIds;
	}

}
