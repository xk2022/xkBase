package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;

import java.util.UUID;

public interface UpmsPermissionUpdateUseCase {

	UpmsPermissionResponseDTO update(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request);

}
