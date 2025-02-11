package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsUserCreateDTO;

public interface UpmsPermissionCreateUseCase {

	UpmsPermissionResponseDTO create(UpmsUserCreateDTO request);

}
