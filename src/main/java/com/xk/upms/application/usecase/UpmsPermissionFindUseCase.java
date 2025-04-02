package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UpmsPermissionFindUseCase {

	List<UpmsPermissionResponseDTO> findAll(UUID systemUuid, Long roleId);

}
