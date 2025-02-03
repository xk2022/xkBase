package com.xk.upms.application.usecase;

/**
 * 📌 `UpmsUserDeleteUseCase` - 使用者刪除應用層 Use Case 介面
 * 
 * - **提供刪除使用者的功能**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserDeleteUseCase {

	/**
	 * 📌 刪除使用者
	 * 
	 * @param userId 使用者 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long userId);

}
