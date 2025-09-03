package com.xk.upms.controller.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.common.base.BaseResult;
import com.xk.upms.application.usecase.UpmsRoleCreateUseCase;
import com.xk.upms.application.usecase.UpmsUserCreateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `UpmsInitRestController`
 * 
 * - 負責管理 **Upms data init API**
 * 
 * @author yuan Created on 2025/02/14.
 */
@Tag(name = "Upms data init Management", description = "提供 Upms 的相關功能 data init")
@RestController
@RequestMapping("/api/upms/init")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UpmsInitRestController {

	private final UpmsUserCreateUseCase upmsUserCreateUseCase;
	private final UpmsRoleCreateUseCase upmsRoleCreateUseCase;

	@Operation(summary = "倒入所有upms相關data", description = "返回。")
	@GetMapping("/all")
	public BaseResult<Object> initUpms() {
		upmsRoleCreateUseCase.createSampleRoles();
		upmsUserCreateUseCase.createSampleUsers();
		return BaseResult.success(true, "成功");
	}

	@Operation(summary = "新增所有upms相關data", description = "返回。")
	@GetMapping("/users")
	public BaseResult<Object> initUpmsUsers() {
		upmsUserCreateUseCase.createSampleUsers();
		return BaseResult.success(true, "成功");
	}

	@Operation(summary = "新增所有upms相關data", description = "返回。")
	@GetMapping("/roles")
	public BaseResult<Object> initUpmsRoles() {
		upmsRoleCreateUseCase.createSampleRoles();
		return BaseResult.success(true, "成功");
	}

}
