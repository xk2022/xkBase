package com.xk.adm.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.dto.LogEntryDTO;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmLoggingMonitorController` - 負責管理 **日誌與監控 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/25.
 */

@Tag(name = "Logging & Monitoring", description = "系統日誌與監控")
@RestController
@RequestMapping("/api/adm/logging")
@RequiredArgsConstructor
@Slf4j
public class AdmLoggingMonitorController {

//    private final AdmLoggingUseCase admLoggingUseCase;

	@Operation(summary = "獲取操作日誌", description = "查詢系統操作日誌")
	@GetMapping("/operations")
	public BaseResult<List<LogEntryDTO>> getOperationLogs() {
//        return BaseResult.success(admLoggingUseCase.getOperationLogs(), "成功");
		return null;
	}
	
}