package com.xk.upms.application.usecase.impl;

import com.xk.common.util.dto.JwtUserDTO;
import com.xk.upms.application.model.UpmsAuthLoginRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsAuthUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserDetailsService;
import com.xk.upms.domain.service.UpmsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
public class UpmsAuthUseCaseImpl implements UpmsAuthUseCase {
	
	private final UpmsUserService upmsUserService;

	private final UpmsUserDetailsService upmsUserDetailsService;
	
	@Override
	public UpmsUserResponseDTO signin(UpmsAuthLoginRequestDTO upmsAuthLoginRequestDTO) {
		// 取得使用者
		Optional<UpmsUserBO> upmsUserBO  = upmsUserService.findByUsername(upmsAuthLoginRequestDTO.username());
		if(!upmsUserBO.isPresent()){
			return null;
		}
		JwtUserDTO jwtUserDTO = upmsUserDetailsService.extract(upmsUserBO.get());
		return new UpmsUserResponseDTO(
				jwtUserDTO.getUserUuid(),
				jwtUserDTO.getUsername(),
				jwtUserDTO.getEmail(),
				jwtUserDTO.getCellPhone(),
				jwtUserDTO.getRoleUuid(),
				jwtUserDTO.isEnable(),
				jwtUserDTO.isLock(),
				jwtUserDTO.getSystemDTOs(),
				jwtUserDTO.getToken()
		);
	}

}
