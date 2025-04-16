package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsUserFindRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * 📌 UpmsUserFindUseCase（應用層 Use Case 介面）
 * 
 * - **提供使用者查找功能**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserFindUseCase {

	List<UpmsUserResponseDTO> getList(UpmsUserFindRequestDTO request);

	UpmsUserResponseDTO getByUuid(UUID uuid);

}
