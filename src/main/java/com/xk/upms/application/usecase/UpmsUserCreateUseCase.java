package com.xk.upms.application.usecase;

import java.util.List;

import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.domain.model.bo.UpmsUserInitBO;

/**
 * 📌 UpmsUserCreateUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者創建邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/02/14 createSampleUsers().
 */
public interface UpmsUserCreateUseCase {

	/**
	 * 📌 創建新使用者
	 * 
	 * @param request 使用者請求 DTO
	 * @return 回應 DTO（包含使用者 ID、名稱、Email）
	 */
	UpmsUserResponseDTO create(UpmsUserCreateDTO request);

	/**
	 * 📌 創建一組範例使用者
	 * 
	 * @return 範例使用者列表（包含使用者 ID、名稱、Email）
	 */
	List<UpmsUserInitBO> createSampleUsers();

}
