package com.xk.adm.application.usecase;

import java.util.List;

import com.xk.adm.application.dto.AdmSystemCreateDTO;
import com.xk.adm.application.dto.AdmSystemResponse;

/**
 * 📌 `AdmSystemManageUseCase`
 * 
 * - 提供 `AdmSystem` 的創建、更新、刪除功能
 * - 讓 `AdmSystemFindUseCaseImpl` 專注於查詢
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemCreateUseCase {

    /**
     * 📌 創建新系統
     *
     * @param createDTO 創建系統的請求 DTO
     * - **確保系統名稱唯一**（若已有相同名稱的系統，可能拋出異常）
     * @throws IllegalArgumentException 如果系統名稱已存在
     * - **將資料儲存至資料庫，並回傳創建成功的系統資訊**
     * @return 創建成功的 `AdmSystemResponse`
     */
    AdmSystemResponse create(AdmSystemCreateDTO createDTO);

	/**
	 * 📌 創建一組範例系統資料
	 * 
	 * @return 範例系統列表（包含使用者 ID、名稱、Email）
	 */
	List<AdmSystemResponse> createSampleSystems();
	
}
