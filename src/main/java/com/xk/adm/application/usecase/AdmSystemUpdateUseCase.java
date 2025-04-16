package com.xk.adm.application.usecase;

import java.util.UUID;

import com.xk.adm.application.dto.AdmSystemRequest;
import com.xk.adm.application.dto.AdmSystemResponse;

/**
 * 📌 `AdmSystemUpdateUseCase`
 * 
 * - 提供 `AdmSystem` 的更新功能
 * - 讓 `AdmSystemReadUseCaseImpl` 專注於查詢
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemUpdateUseCase {

	/**
	 * 更新系統資訊
	 *
	 * @param uuid       需要更新的系統 `UUID`
	 * @param request  包含更新內容的 `AdmSystemRequest`
	 * @return 更新後的 `AdmSystemResponse`
	 */
	AdmSystemResponse update(UUID uuid, AdmSystemRequest request);
	
}
