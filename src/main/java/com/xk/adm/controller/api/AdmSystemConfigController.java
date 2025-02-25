package com.xk.adm.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.model.SystemConfigDTO;
import com.xk.adm.application.usecase.AdmSystemConfigUseCase;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `SystemConfigController` - 負責管理 **使用者 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/24.
 */
@Tag(name = "系統管理", description = "提供系統參數配置 API")
@RestController
@RequestMapping("/api/adm/system")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Slf4j
public class AdmSystemConfigController {

	private final AdmSystemConfigUseCase admSystemConfigUseCase;

    @Operation(summary = "獲取系統設定", description = "返回當前系統的所有參數設定。")
    @GetMapping("/settings")
    public BaseResult<SystemConfigDTO> getSettings() {
        log.info("🔍 [SystemConfig] 正在獲取系統設定...");
        SystemConfigDTO settings = admSystemConfigUseCase.getSystemSettings();
        log.info("✅ [SystemConfig] 設定獲取成功: {}", settings);
        return BaseResult.success(settings, "成功獲取系統設定");
    }

    @Operation(summary = "更新系統設定", description = "更新系統的參數設定，返回更新後的結果。")
    @PutMapping("/settings") // ✅ 使用 PUT，符合 RESTful API 標準
    public BaseResult<SystemConfigDTO> updateSettings(
			@Parameter(description = "需要更新的用戶ID", required = true) @PathVariable Long id,
			@RequestBody SystemConfigDTO request) {
        log.info("✏️ [SystemConfig] 正在更新系統設定: {}", request);
        SystemConfigDTO updateSettings = admSystemConfigUseCase.updateSettings(id, request);
        log.info("✅ [SystemConfig] 設定更新成功: {}", updateSettings);
        return BaseResult.success(updateSettings, "系統設定已成功更新");
    }
}
