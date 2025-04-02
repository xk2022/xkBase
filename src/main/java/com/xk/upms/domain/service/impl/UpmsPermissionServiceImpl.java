package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsPermissionRepository;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.service.UpmsPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

	@Override
	public void createAll(List<UpmsPermission> upmsPermissions) {
		if (null == upmsPermissions || upmsPermissions.isEmpty()) {
			return;
		}
		for(UpmsPermission upmsPermission : upmsPermissions){
			upmsPermission.setIsDeleted(false);
			upmsPermission.setStatus(true);
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
	public List<UpmsPermission> findAll(UUID systemUuid, Long roleId) {
		return null;
	}

}
