package com.xk.upms.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsPermissionRepository;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.service.UpmsPermissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	
	@SuppressWarnings("unused")
	@Override
	@Transactional
	public UpmsPermissionBO save(UpmsPermissionBO permission) {
		UpmsPermissionBO resultBo = new UpmsPermissionBO();
		log.info("📌 儲存權限: {}", permission.getName());
		if (permission == null) {
			throw new IllegalArgumentException("權限不能為 null");
		}
		UpmsPermission permissionPO = XkBeanUtils.copyProperties(permission, UpmsPermission::new );
		UpmsPermission savePermissionPo = upmsPermissionRepository.save(permissionPO);
		XkBeanUtils.copyPropertiesAutoConvert(savePermissionPo, resultBo);
		return resultBo;
	}

	@Override
	public boolean delete(Long permissionId) {
		log.info("📌 嘗試刪除權限 ID: {}", permissionId);
        return upmsPermissionRepository.findById(permissionId)
                .map(permission -> {
                	upmsPermissionRepository.delete(permission);
                    log.info("✅ 權限 ID: {} 已刪除", permissionId);
                    return true;
                }).orElse(false);
	}

	@Override
	public Optional<UpmsPermissionBO> findById(Long permissionId) {
		 log.info("📌 查詢權限 ID: {}", permissionId);
	        return upmsPermissionRepository.findById(permissionId)
	                .map(permission -> new UpmsPermissionBO(
	                		permission.getSystemId(),
	                		permission.getPid(), 
	                        permission.getName(),
	                        permission.getUri(),
	                        permission.getStatus(),
	                        permission.getOrders(),
	                        permission.getIsDeleted(),
	                        permission.getDeleteUser(),
	                        permission.getDeleteTime(),
	                        permission.getChildren(),
	                        permission.getParent()
	                ));
	}

	@Override
	public UpmsPermissionBO update(Long permissionId, UpmsPermissionBO updatePermissionEntity) {
		UpmsPermissionBO permissionBO = new UpmsPermissionBO();
		log.info("📌 儲存權限: {}", updatePermissionEntity.getName());
		UpmsPermission permissionPO = XkBeanUtils.copyProperties(updatePermissionEntity, UpmsPermission::new);
		permissionPO.setId(permissionId);
		UpmsPermission savedPO = upmsPermissionRepository.save(permissionPO);
		XkBeanUtils.copyPropertiesAutoConvert(savedPO,permissionBO);
		return permissionBO;
	}

	@Override
	public List<UpmsPermissionBO> findAll(Sort sort) {
		log.info("📌 查詢所有權限 (支援條件過濾)");
		return XkBeanUtils.copyListProperties(buildTree(upmsPermissionRepository.findAll(sort),null), UpmsPermissionBO::new) ;
	}

	@Override
	public List<UpmsPermission> buildTree(List<UpmsPermission> permissions, Long pid) {
		List<UpmsPermission> tree = new ArrayList<>();
	    for (UpmsPermission permission : permissions) {
	        if ((pid == null && permission.getPid() == null) || 
	            (pid != null && pid.equals(permission.getPid()))) {
	            permission.setChildren(buildTree(permissions, permission.getId()));
	            tree.add(permission);
	        }
	    }
	    return tree ;
	}


}
