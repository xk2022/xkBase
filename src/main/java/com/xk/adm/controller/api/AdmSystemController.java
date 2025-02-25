package com.xk.adm.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.model.SystemDTO;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemController` - 負責管理 **系統資訊 API**
 * 
 * - 提供 `CRUD` 操作 - 支援分頁查詢 - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/25.
 */
@Tag(name = "System Management", description = "系統管理")
@RestController
@RequestMapping("/api/adm/system")
@RequiredArgsConstructor
@Slf4j
public class AdmSystemController {

//    private final AdmSystemUseCase admSystemUseCase;

	@Operation(summary = "獲取所有系統資訊", description = "查詢所有系統類型")
	@GetMapping("/all")
	public BaseResult<List<SystemDTO>> getAllSystems() {
//        return BaseResult.success(admSystemUseCase.getAllSystems(), "成功");
		return null;
	}

	@Operation(summary = "根據類型獲取系統資訊", description = "查詢特定類型的系統")
	@GetMapping("/{code}")
	public BaseResult<SystemDTO> getSystemByCode(@PathVariable String code) {
//        return BaseResult.success(admSystemUseCase.getSystemByCode(code), "成功");
		return null;
	}

	@Operation(summary = "新增系統資訊", description = "新增一條系統記錄")
	@PostMapping("/create")
	public BaseResult<SystemDTO> createSystem(@RequestBody SystemDTO systemDTO) {
//        return BaseResult.success(admSystemUseCase.createSystem(systemDTO), "成功");
		return null;
	}

	@Operation(summary = "更新系統資訊", description = "更新現有的系統記錄")
	@PutMapping("/update")
	public BaseResult<SystemDTO> updateSystem(@RequestBody SystemDTO systemDTO) {
//        return BaseResult.success(admSystemUseCase.updateSystem(systemDTO), "成功");
		return null;
	}

	@Operation(summary = "刪除系統資訊", description = "根據 ID 刪除系統")
	@DeleteMapping("/delete/{id}")
	public BaseResult<Boolean> deleteSystem(@PathVariable String id) {
//        return BaseResult.success(admSystemUseCase.deleteSystem(id), "成功");
		return null;
	}
}