package com.xk.upms.application.usecase;

import java.util.List;

import com.xk.upms.application.model.UpmsRoleCreateDTO;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.domain.model.bo.UpmsRoleInitBO;

/**
 * 📌 UpmsRoleCreateUseCase（應用層 Use Case 介面）
 * 
 * - **負責使用者角色創建邏輯**
 * 
 * @author hank Created on 2025/02/07.
 * @author yuan Updated on 2025/02/14 createSampleRoles().
 */
public interface UpmsRoleCreateUseCase {

	UpmsRoleResponseDTO create(UpmsRoleCreateDTO request);

	/**
	 * 📌 創建一組範例角色
	 * 
	 * @return 範例使用者列表（包含使用者 ID、名稱、Email）
	 */
	List<UpmsRoleInitBO> createSampleRoles();

}
