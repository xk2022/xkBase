package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionUpdateDTO;

import java.util.UUID;

public interface UpmsPermissionUpdateUseCase {

	void update(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request);

}
