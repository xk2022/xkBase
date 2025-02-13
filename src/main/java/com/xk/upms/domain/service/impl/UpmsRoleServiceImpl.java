package com.xk.upms.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsUserRoleRepository;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.po.UpmsRole;
import com.xk.upms.domain.service.UpmsRoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UserServiceImpl` - 角色領域服務的具體實作
 * 
 * - **提供基本的 CRUD 操作** 
 * - **支援條件查詢** 
 * - **確保與 `Repository` 交互的邏輯**
 * 
 * @author hank Created on 2025/02/07.
 * @author hank Updated on 2025/02/07 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleServiceImpl implements UpmsRoleService {

	private final UpmsUserRoleRepository upmsRoleRepository;

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public UpmsRoleBO save(UpmsRoleBO role) {
		UpmsRoleBO resultBo = new UpmsRoleBO();
		log.info("📌 儲存使用者角色: {}", role.getCode());

		if (role == null) {
			throw new IllegalArgumentException("角色不能為 null");
		}

		UpmsRole rolePO = XkBeanUtils.copyProperties(role, UpmsRole::new);
		UpmsRole saveRolePO = upmsRoleRepository.save(rolePO);
		XkBeanUtils.copyPropertiesAutoConvert(saveRolePO, resultBo);
		return resultBo;
	}

	@Override
	public Optional<UpmsRoleBO> findById(Long roleId) {
		log.info("📌 查詢角色 ID: {}", roleId);
		return upmsRoleRepository.findById(roleId)
				.map(role -> new UpmsRoleBO(
						role.getCode(), 
						role.getTitle(), 
						role.getDescription(), 
						role.getOrders()
				));
	}

	@Override
	public UpmsRoleBO update(Long id, UpmsRoleBO updatedEntity) {
		UpmsRoleBO roleBO = new UpmsRoleBO();
		log.info("📌 儲存角色: {}", roleBO.getCode());
		UpmsRole rolePO = XkBeanUtils.copyProperties(updatedEntity, UpmsRole::new);
		rolePO.setId(id);
		UpmsRole savedPO = upmsRoleRepository.save(rolePO);
		XkBeanUtils.copyPropertiesAutoConvert(savedPO, roleBO);
		return roleBO;
	}

	@Override
	public List<UpmsRoleBO> findAll() {
		List<UpmsRole> rolePOList = upmsRoleRepository.findAll();
		return XkBeanUtils.copyListProperties(rolePOList, UpmsRoleBO::new);
	}

	@Override
	public boolean delete(Long roleId) {
		log.info("📌 嘗試刪除角色 ID: {}", roleId);

		return upmsRoleRepository.findById(roleId).map(role -> {
			upmsRoleRepository.delete(role);
			log.info("✅ 角色 ID: {} 已刪除", roleId);
			return true;
		}).orElse(false);
	}

}
