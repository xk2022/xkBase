package com.xk.upms.application.usecase.admin;

import com.xk.upms.application.model.UpmsUserRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;

/**
 * 📌 AuthorizationUseCase（應用層 Use Case 介面）
 * 
 * - **負責登入邏輯**
 * 
 * @author hank Created on 2025/02/24.
 * @author hank Updated on 2025/02/24 something note here.
 */
public interface AuthorizationUseCase {
	
	UpmsUserResponseDTO signin(UpmsUserRequestDTO userRequest) throws Exception;
	

}
