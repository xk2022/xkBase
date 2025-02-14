package com.xk.upms.application.usecase.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsRoleCreateDTO;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsRoleCreateUseCase;
import com.xk.upms.domain.model.bo.UpmsRoleBO;
import com.xk.upms.domain.model.bo.UpmsRoleInitBO;
import com.xk.upms.domain.service.UpmsRoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsRoleCreateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者角色創建邏輯**
 * 
 * @author hank Created on 2025/02/07.
 * @author yuan Updated on 2025/02/14 createSampleRoles().
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRoleCreateUseCaseImpl implements UpmsRoleCreateUseCase {

	private final UpmsRoleService upmsRoleService;

	@Override
	public UpmsRoleResponseDTO create(UpmsRoleCreateDTO request) {
		log.info("📌 開始創建新角色: {}", request.code());
		// ✅ 轉換 DTO -> BO
		UpmsRoleBO roleBO = XkBeanUtils.copyProperties(request, UpmsRoleBO::new);
		// ✅ 儲存到 DB
		UpmsRoleBO savedRole = upmsRoleService.save(roleBO);
		// ✅ 轉換 PO -> DTO 回傳
		return XkBeanUtils.copyProperties(savedRole, UpmsRoleResponseDTO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UpmsRoleInitBO> createSampleRoles() {
		List<UpmsRoleInitBO> roles = new ArrayList<>();
		roles.add(new UpmsRoleInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "admin", "系統管理員", "系統管理員", (long) 1));
		roles.add(new UpmsRoleInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "sys", "管理員", "管理員", (long) 2));
		roles.add(new UpmsRoleInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "boss", "老闆", "老闆", (long) 3));
		roles.add(new UpmsRoleInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "accounter", "會計", "會計", (long) 4));
		roles.add(new UpmsRoleInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "driver", "司機", "司機", (long) 5));
	    
		upmsRoleService.saveAllRoles(roles);
		return roles;
	}

}
