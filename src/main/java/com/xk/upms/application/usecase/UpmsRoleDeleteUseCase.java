package com.xk.upms.application.usecase;

/**
 * 📌 `UpmsRoleDeleteUseCase` - 刪除應用層 Use Case 介面
 * 
 * - **提供刪除角色的功能**
 * 
 * @author hank Created on 2025/02/10.
 * @author hank Updated on 2025/02/10 something note here.
 */
public interface UpmsRoleDeleteUseCase {

	/**
	 * 📌 刪除角色
	 * 
	 * @param roleId ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long roleId);
}
