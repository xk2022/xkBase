package com.xk.upms.application.usecase;

public interface UpmsPermissionDeleteUseCase {
	
	
	/**
	 * 📌 刪除權限
	 * 
	 * @param permissionId 權限 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long permissionId);

}
