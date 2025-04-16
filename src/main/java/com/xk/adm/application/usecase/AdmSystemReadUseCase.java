package com.xk.adm.application.usecase;

import java.util.List;
import java.util.UUID;

import com.xk.adm.application.dto.AdmSystemRequest;
import com.xk.adm.application.dto.AdmSystemResponse;

/**
 * 📌 `AdmSystemReadUseCase`
 * 
 * - 定義 `ADM System`（Administrator System）的查詢業務邏輯  
 * - 適用於系統資訊的查詢，不涉及修改、刪除操作  
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemReadUseCase {

	/**
	 * 📌 獲取所有系統的列表
	 *
	 * @return 所有系統的 `AdmSystemResponse` 列表
	 */
	List<AdmSystemResponse> getList();

	/**
	 * 📌 透過查詢條件篩選系統
	 *
	 * @param request 查詢條件封裝為 `AdmSystemRequest`
	 * @return 符合條件的 `AdmSystemResponse` 列表
	 */
	List<AdmSystemResponse> searchSystems(AdmSystemRequest request);

	/**
	 * 📌 根據 `UUID` 獲取系統資訊
	 *
	 * @param uuid 系統的唯一標識
	 * @return `AdmSystemResponse`
	 * @throws EntityNotFoundException 若無資料則拋出錯誤
	 */
	AdmSystemResponse getSystemByUuid(UUID uuid);

	/**
	 * 📌 根據 `code` 獲取系統資訊
	 *
	 * @param code 系統的唯一標識
	 * @return `AdmSystemResponse`，若無資料則返回 `null`
	 */
	AdmSystemResponse getSystemByCode(String code);

}
