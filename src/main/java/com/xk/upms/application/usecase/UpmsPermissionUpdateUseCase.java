package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsUserUpdateDTO;

public interface UpmsPermissionUpdateUseCase {

	UpmsPermissionResponseDTO update(Long id, UpmsUserUpdateDTO request);

}
