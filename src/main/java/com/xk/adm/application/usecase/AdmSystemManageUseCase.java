package com.xk.adm.application.usecase;

import com.xk.adm.application.model.AdmSystemDTO;

/**
 * 📌 `AdmSystemManageUseCase`
 * 
 * - 提供 `AdmSystem` 的創建、更新、刪除功能
 * - 讓 `AdmSystemFindUseCaseImpl` 專注於查詢
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemManageUseCase {

    /**
     * 創建新系統
     *
     * @param request 系統資料
     * @return 創建成功的 `AdmSystemDTO`
     */
    AdmSystemDTO create(AdmSystemDTO request);

	/**
	 * 更新系統資訊
	 *
	 * @param dto 需要更新的 `SystemDTO`
	 * @return 更新後的 `SystemDTO`
	 */
    AdmSystemDTO update(AdmSystemDTO request);

	/**
	 * 刪除系統
	 *
	 * @param id 需要刪除的系統 ID
	 * @return 是否成功刪除
	 */
	Boolean delete(String uuid);
	
}
