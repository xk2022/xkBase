package com.xk.upms.application.usecase.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;
import com.xk.upms.domain.service.UpmsRoleService;
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
	private final UpmsRoleService upmsRoleService;

	private final UpmsUserRoleService upmsUserRoleService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpmsUserResponseDTO create(UpmsUserCreateDTO request) {
		log.info("📌 開始創建新使用者: {}", request.username());
		// ✅ 轉換 DTO -> BO
		UpmsUserBO userBO = XkBeanUtils.copyProperties(request, UpmsUserBO::new);
		userBO.setDeleted(false);
		userBO.setEnabled(true);
		userBO.setLocked(false);
		// ✅ 儲存到 DB
		UpmsUserBO savedUser = upmsUserService.save(userBO);
		// ✅ 轉換 DTO -> BO
		UpmsUserRoleBO upmsUserRoleBO = XkBeanUtils.copyProperties(request, UpmsUserRoleBO::new);
		upmsUserRoleBO.setUserUuid(savedUser.getUuid());
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
	    users.add(new UpmsUserInitBO(UUID.randomUUID(),"ADMIN", "admin@example.com", "admin@example.com", "9099999999", "Aa123456",false ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST",ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST" ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")) ));
	    users.add(new UpmsUserInitBO(UUID.randomUUID(),"Louis", "louis@example.com", "louis@example.com", "0978628329", "Aa123456",false ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST",ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST" ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")) ));
	    users.add(new UpmsUserInitBO(UUID.randomUUID(),"Lisa", "lisa@example.com", "lisa@example.com", "9099999998", "Aa123456",false ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST",ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST" ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")) ));
	    users.add(new UpmsUserInitBO(UUID.randomUUID(),"Tim", "tim@example.com", "tim@example.com", "9099999997", "Aa123456",false ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST",ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST" ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")) ));
	    users.add(new UpmsUserInitBO(UUID.randomUUID(),"Hank", "hank@example.com", "hank@example.com", "9099999996", "Aa123456",false ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST",ZonedDateTime.now(ZoneId.of("Asia/Taipei")),"TEST" ,ZonedDateTime.now(ZoneId.of("Asia/Taipei")) ));


		List<UpmsUserBO> userBOs = upmsUserService.saveAllUsers(users);
		for (UpmsUserBO userBO : userBOs) {
			UpmsUserRoleBO upmsUserRoleBO = new UpmsUserRoleBO();
			upmsUserRoleBO.setUserUuid(userBO.getUuid());
			upmsUserRoleBO.setRoleUuid(upmsRoleService.findAll(null).get(0).getUuid());
			// ✅ 儲存到 DB
			upmsUserRoleService.save(upmsUserRoleBO);
		}
        return users;
    }

}
