package com.xk.upms.application.usecase.impl.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.common.util.XkJwtUtil;
import com.xk.upms.application.model.UpmsUserRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.admin.AuthorizationUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsRoleService;
import com.xk.upms.domain.service.UpmsUserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 AuthorizationUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責登入操作**
 * 
 * @author hank Created on 2025/02/24.
 * @author hank Updated on 2025/02/24 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationUseCaseImpl implements AuthorizationUseCase {
	
	private final UpmsUserService upmsUserService;
	private final UpmsRoleService upmsRoleService;
	
	
	@Override
	public UpmsUserResponseDTO signin(UpmsUserRequestDTO userRequest) throws Exception {
		Optional<UpmsUserBO> userop  = upmsUserService.findByUsername(userRequest.username());
		if(userop.isPresent()) {
			UpmsUserBO user = userop.get();
			if(user.getEnabled()==true) {
				//檢核密碼
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean isMatch = passwordEncoder.matches(userRequest.password(), user.getPassword());
				if(isMatch) {
					//產token
					String token  = XkJwtUtil.generateToken(user.getId());
					UpmsUserResponseDTO response = XkBeanUtils.copyProperties(user, UpmsUserResponseDTO::new);
					response.setToken(token);
					return response;
					
				}else {
					//錯誤記錄
					int fail = user.getFailedAttempts();
					if(fail==3) {
						user.setLocked(true);
						user.setFailedAttempts(fail);
					}else {
						user.setFailedAttempts(fail++);
					}
					upmsUserService.save(user);
					throw new Exception("密碼輸入錯誤，請重新輸入");
				}
			}else {
				//狀態未啟用 請洽詢服務人員
				throw new Exception("狀態未啟用，請洽詢服務人員");
			}
		}else {
			//查不到此使用者 請先註冊
			throw new EntityNotFoundException("查不到此使用者，請先註冊");
		}
	}

}
