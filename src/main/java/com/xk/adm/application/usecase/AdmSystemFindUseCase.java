package com.xk.adm.application.usecase;

import java.util.List;

import com.xk.adm.application.model.AdmSystemDTO;

/**
 * 📌 `AdmSystemFindUseCase`
 * 
 * - 定義 `ADM System`（Administrator System）的查詢業務邏輯
 * - 適用於系統資訊的查詢，不涉及修改、刪除操作
 * - 當 `CRUD` 方法超過 7 個時，可拆分為 `FindUseCase`、`UpdateUseCase` 等
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemFindUseCase {

	/**
	 * 獲取所有系統的列表
	 *
	 * @return 所有系統的 `AdmSystemDTO` 列表
	 */
	List<AdmSystemDTO> getList();

	/**
	 * 透過查詢條件篩選系統
	 *
	 * @param request 查詢條件封裝為 `AdmSystemDTO`
	 * @return 符合條件的 `AdmSystemDTO` 列表
	 */
	List<AdmSystemDTO> searchSystems(AdmSystemDTO request);

	/**
	 * 根據 `UUID` 獲取系統資訊
	 *
	 * @param uuid 系統的唯一標識
	 * @return `Optional<AdmSystemDTO>`，若無資料則返回 `Optional.empty()`
	 */
	AdmSystemDTO getSystemById(String uuid);

	/**
	 * 根據 `code` 獲取系統資訊
	 *
	 * @param code 系統的唯一標識
	 * @return `Optional<AdmSystemDTO>`，若無資料則返回 `Optional.empty()`
	 */
	AdmSystemDTO getSystemByCode(String code);

}