package com.xk.upms.application.usecase;

/**
 * 📌 `UpmsUserDeleteUseCase` - 使用者權限刪除應用層 Use Case 介面
 * 
 * - **提供刪除使用者權限的功能**
 * 
 * @author hank Created on 2025/02/04.
 * @author hank Updated on 2025/02/04 something note here.
 */
public interface UpmsRoleDeleteUseCase {

	/**
	 * 📌 刪除使用者
	 * 
	 * @param userId 使用者 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long userId);

}
