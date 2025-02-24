package com.xk.upms.controller.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.upms.application.usecase.UpmsActionFindUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * 📌 `UpmsActionRestController` - 負責管理 **動作 API**
 * 
 * - 提供 `CRUD` 操作 
 * - 支援分頁查詢 
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21 something note here.
 */
@RestController
@RequestMapping("/api/upms/actions")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "UpmsAction Management", description = "提供 UpmsAction 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class UpmsActionRestController {
	
	private final UpmsActionFindUseCase upmsActionFindUseCase;

}
