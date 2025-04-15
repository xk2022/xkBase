package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsActionRepository;
import com.xk.upms.domain.dao.repository.UpmsPermissionRepository;
import com.xk.upms.domain.model.bo.UpmsActionBO;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.po.UpmsAction;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.service.UpmsPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 📌 `PermissionServiceImpl` - 權限服務的具體實作
 * 
 * - **提供基本的 CRUD 操作** - **支援條件查詢** - **確保與 `Repository` 交互的邏輯**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionServiceImpl implements UpmsPermissionService {
	
	private final UpmsPermissionRepository upmsPermissionRepository;
	private final UpmsActionRepository upmsActionRepository;

	@Override
	public void createAll(List<UpmsPermission> upmsPermissions) {
		if (null == upmsPermissions || upmsPermissions.isEmpty()) {
			return;
		}
		for(UpmsPermission upmsPermission : upmsPermissions){
			upmsPermission.setIsDeleted(false);
		}
		upmsPermissionRepository.saveAll(upmsPermissions);
	}

	@Override
	public void deleteAll(List<UpmsPermission> upmsPermissions) {
		if (null == upmsPermissions || upmsPermissions.isEmpty()) {
			return;
		}
		for(UpmsPermission upmsPermission : upmsPermissions){
			upmsPermission.setIsDeleted(true);
		}
		upmsPermissionRepository.saveAll(upmsPermissions);
	}

	@Override
	public List<UpmsPermission> findAll() {
		return upmsPermissionRepository.findByIsDeletedFalseOrderByOrdersAsc();
	}

	@Override
	public Optional<UpmsPermissionBO> findById(UUID systemId,Long permissionid) {
		List<UpmsAction> actionPoS = upmsActionRepository.findByIsDeletedFalseOrderByOrdersAsc();
		List<UpmsActionBO> actionBOS = actionPoS.stream().map(ua ->{
			UpmsActionBO bo = new UpmsActionBO();
			bo.setId(ua.getId());
			bo.setActionName(ua.getName());
			bo.setPermissionId(permissionid);
			bo.setOrders(ua.getOrders());
			bo.setMethod(ua.getMethod());
			bo.setActive(ua.getActive());
			bo.setIsDeleted(ua.getIsDeleted());
			bo.setDeleteUser(ua.getDeleteUser());
			bo.setDeleteTime(ua.getDeleteTime());
			return bo;
		}).collect(Collectors.toList());

		return upmsPermissionRepository.findById(permissionid).map(up -> new UpmsPermissionBO(
				up.getId(),
				systemId,
				up.getPid(),
				up.getName(),
				up.getUri(),
				up.getStatus(),
				up.getOrders(),
				up.getIsDeleted(),
				up.getDeleteUser(),
				up.getDeleteTime(),
				actionBOS
		));
	}

	@Override
	public UpmsPermission findById(Long permissionId) {
		return upmsPermissionRepository.findById(permissionId).orElse(null);
	}

}
