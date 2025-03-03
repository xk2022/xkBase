package com.xk.adm.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.usecase.AdmSystemConfigUseCase;
import com.xk.adm.application.usecase.AdmSystemCreateUseCase;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmInitController`
 * 
 * - 負責管理 **Administrator 系統數據初始化 API**
 * 
 * @author yuan Created on 2025/02/25.
 */
@Tag(name = "Adm data init Management", description = "提供 Adm 的相關功能 data init")
@RestController
@RequestMapping("/api/adm/init")
@RequiredArgsConstructor
@Slf4j
public class AdmInitController {

	private final AdmSystemConfigUseCase admSystemConfigUseCase;
	private final AdmSystemCreateUseCase admSystemCreateUseCase;

	@Operation(summary = "初始化所有 UPMS 相關資料", description = "執行所有 UPMS 相關資料的初始化作業。")
	@GetMapping("/all")
	public BaseResult<Object> initUpms() {
		log.info("📌 開始初始化所有 UPMS 相關資料...");
		admSystemConfigUseCase.create();
		admSystemCreateUseCase.createSampleSystems();
		return BaseResult.success(true, "UPMS 資料初始化成功");
	}

	@Operation(summary = "初始化系統設定 (SystemConfig)", description = "建立預設的系統設定資料。")
	@GetMapping("/systemConfig")
	public BaseResult<Object> initSystemConfig() {
		log.info("📌 開始初始化系統設定...");
		admSystemConfigUseCase.create();
		return BaseResult.success(true, "系統設定初始化成功");
	}

	@Operation(summary = "初始化 AdmSystem 資料", description = "建立預設的 AdmSystem 設定資料。")
	@GetMapping("/admSystem")
	public BaseResult<Object> initAdmSystem() {
		log.info("📌 開始初始化 AdmSystem 資料...");
		admSystemCreateUseCase.createSampleSystems();
		return BaseResult.success(true, "AdmSystem 資料初始化成功");
	}

}