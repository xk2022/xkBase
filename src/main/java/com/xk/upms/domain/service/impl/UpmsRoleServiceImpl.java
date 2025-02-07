package com.xk.upms.domain.service.impl;

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
 * @author hank  Created on 2025/02/07.
 * @author hank Updated on 2025/02/07 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleServiceImpl  implements UpmsRoleService{
	
	private final UpmsUserRoleRepository upmsRoleRepository;
	
	@SuppressWarnings("unused")
	@Override
	@Transactional
	public UpmsRoleBO save(UpmsRoleBO role) {
		UpmsRoleBO resultBo = new UpmsRoleBO();
		log.info("📌 儲存使用者角色: {}", role.getCode());
		
		if(resultBo==null) {
			throw new IllegalArgumentException("角色不能為 null");
		}
		
		UpmsRole rolePO = XkBeanUtils.copyProperties(role, UpmsRole::new);
		UpmsRole saveRolePO = upmsRoleRepository.save(rolePO);
		 XkBeanUtils.copyPropertiesAutoConvert(saveRolePO, rolePO);
		
		return resultBo;
	}

}
