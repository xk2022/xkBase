package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsActionResponseDTO;

/**
 * 📌 UpmsActionFindUseCase（應用層 Use Case 介面）
 * 
 * - **負責操作動作邏輯**
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21 something note here.
 */
public interface UpmsActionFindUseCase {
	
	UpmsActionResponseDTO findById(Long Id);
}
