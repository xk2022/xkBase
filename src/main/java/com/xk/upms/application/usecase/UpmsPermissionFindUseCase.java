package com.xk.upms.application.usecase;

import java.util.List;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;

public interface UpmsPermissionFindUseCase {

	/**
	 * 📌 查詢所有權限
	 * 
	 * @param
	 * @return List<UpmsPermissionResponseDTO>
	 */
	List<UpmsPermissionResponseDTO> getAllPermission();

	/**
	 * 📌 查詢權限
	 * 
	 * @param permissionId
	 * @return UpmsPermissionResponseDTO
	 */
	UpmsPermissionResponseDTO findById(Long permissionId);

}
