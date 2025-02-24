package com.xk.upms.controller.api.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.xk.common.base.BaseResult;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.admin.AuthorizationUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
*
* @author hank Created on 2025/02/19.
*/
@Tag(name = "Authorization API", description = "管理身份验证相关的 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "Login Management", description = "登入相關檢核")
@Slf4j
public class AuthorizationRestController {
	
	private final AuthorizationUseCase authorizationUseCase;
	
	
	@PostMapping("/signin")
	public BaseResult<UpmsUserResponseDTO> signIn(){
		return null;
	}

}
