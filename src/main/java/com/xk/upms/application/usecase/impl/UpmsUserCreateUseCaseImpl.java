package com.xk.upms.application.usecase.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;
import com.xk.upms.domain.service.UpmsUserRoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsUserCreateUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.bo.UpmsUserInitBO;
import com.xk.upms.domain.service.UpmsUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UserCreateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者創建邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserCreateUseCaseImpl implements UpmsUserCreateUseCase {

	private final UpmsUserService upmsUserService;

	private final UpmsUserRoleService upmsUserRoleService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpmsUserResponseDTO create(UpmsUserCreateDTO request) {
		log.info("📌 開始創建新使用者: {}", request.username());
		// ✅ 轉換 DTO -> BO
		UpmsUserBO userBO = XkBeanUtils.copyProperties(request, UpmsUserBO::new);
		// ✅ 儲存到 DB
		UpmsUserBO savedUser = upmsUserService.save(userBO);
		// ✅ 轉換 DTO -> BO
		UpmsUserRoleBO upmsUserRoleBO = XkBeanUtils.copyProperties(request, UpmsUserRoleBO::new);
		upmsUserRoleBO.setUserId(savedUser.getId());
		// ✅ 儲存到 DB
		UpmsUserRoleBO saveUserRole = upmsUserRoleService.save(upmsUserRoleBO);
		// ✅ 轉換 PO -> DTO 回傳
		return XkBeanUtils.copyProperties(savedUser, UpmsUserResponseDTO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UpmsUserInitBO> createSampleUsers() {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<UpmsUserInitBO> users = new ArrayList<>();
	    users.add(new UpmsUserInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "ADMIN", "admin@example.com", "9099999999", passwordEncoder.encode("Aa123456")));
	    users.add(new UpmsUserInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "Louis", "louis@example.com", "0978628329", passwordEncoder.encode("Aa123456")));
	    users.add(new UpmsUserInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "Lisa", "lisa@example.com", "9099999998", passwordEncoder.encode("Aa123456")));
	    users.add(new UpmsUserInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "Tim", "tim@example.com", "9099999997", passwordEncoder.encode("Aa123456")));
	    users.add(new UpmsUserInitBO("system restAPI", ZonedDateTime.now(ZoneId.of("Asia/Taipei")), "Hank", "hank@example.com", "9099999996", passwordEncoder.encode("Aa123456")));

	    upmsUserService.saveAllUsers(users);
        return users;
    }

}
