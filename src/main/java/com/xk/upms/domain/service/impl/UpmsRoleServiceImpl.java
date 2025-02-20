package com.xk.upms.domain.service.impl;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.xk.upms.domain.model.po.UpmsUserRole;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsRoleRepository;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.bo.UpmsRoleInitBO;
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
 * @author yuan Updated on 2025/02/14 saveAllRoles().
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleServiceImpl implements UpmsRoleService {

	private final UpmsRoleRepository upmsRoleRepository;

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public UpmsRoleBO save(UpmsRoleBO upmsRoleBO) {
		UpmsRoleBO resultBo = new UpmsRoleBO();
		log.info("📌 儲存使用者角色: {}", upmsRoleBO.getCode());
		if (upmsRoleBO == null) {
			throw new IllegalArgumentException("角色不能為 null");
		}
		// 檢核名稱是否重複
		upmsRoleRepository.findByIsdeletedFalseAndCode(upmsRoleBO.getCode()).ifPresent(role -> {
			throw new IllegalArgumentException("角色名稱重複");
		});
		UpmsRole rolePO = XkBeanUtils.copyProperties(upmsRoleBO, UpmsRole::new);
		UpmsRole saveRolePO = upmsRoleRepository.save(rolePO);
		XkBeanUtils.copyPropertiesAutoConvert(saveRolePO, resultBo);
		return resultBo;
	}

    /**
     * {@inheritDoc}
     */
	@Override
    @Transactional
	public List<UpmsRoleBO> saveAllRoles(List<UpmsRoleInitBO> boList) {
		if (boList == null || boList.isEmpty()) {
            log.warn("⚠️ 空的角色列表，不進行任何儲存操作");
            return Collections.emptyList();
        }
        
        List<UpmsRole> roles = XkBeanUtils.copyListProperties(boList, UpmsRole::new);
        List<UpmsRole> saveRoles = upmsRoleRepository.saveAll(roles);
        return XkBeanUtils.copyListProperties(saveRoles, UpmsRoleBO::new);		
	}

	@Override
	public Optional<UpmsRoleBO> findById(Long roleId) {
		log.info("📌 查詢角色 ID: {}", roleId);
		return upmsRoleRepository.findById(roleId)
				.map(role -> new UpmsRoleBO(
						role.getId(),
						role.getCode(), 
						role.getTitle(), 
						role.getDescription(), 
						role.getOrders()
				));
	}

	@Override
	public UpmsRoleBO update(Long id, UpmsRoleBO upmsRoleBO) {
		UpmsRoleBO roleBO = new UpmsRoleBO();
		log.info("📌 儲存角色: {}", roleBO.getCode());
		// 檢核名稱是否重複
		upmsRoleRepository.findByIsdeletedFalseAndCode(upmsRoleBO.getCode()).ifPresent(role -> {
			if(!role.getId().equals(upmsRoleBO.getId())){
				throw new IllegalArgumentException("角色名稱重複");
			}
		});
		UpmsRole rolePO = XkBeanUtils.copyProperties(upmsRoleBO, UpmsRole::new);
		rolePO.setId(id);
		UpmsRole savedPO = upmsRoleRepository.save(rolePO);
		XkBeanUtils.copyPropertiesAutoConvert(savedPO, roleBO);
		return roleBO;
	}

	@Override
	public List<UpmsRoleBO> findAll(String keyword) {
		List<UpmsRole> rolePOList = upmsRoleRepository.findAllLike(keyword);
		return XkBeanUtils.copyListProperties(rolePOList, UpmsRoleBO::new);
	}

	@Override
	public boolean delete(Long roleId) {
		log.info("📌 嘗試刪除角色 ID: {}", roleId);
		return upmsRoleRepository.findById(roleId).map(userRole -> {
			userRole.setIsdeleted(true);
			userRole.setDeletetime(ZonedDateTime.now());
			upmsRoleRepository.save(userRole);
			return true;
		}).orElse(false);
	}

}
