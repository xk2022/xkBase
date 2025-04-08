package com.xk.upms.application.usecase;

import com.xk.upms.application.model.UpmsPermissionCreateDTO;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface UpmsPermissionCreateUseCase {
    void create(UUID systemUuid, Long roleId , UpmsPermissionUpdateDTO request );

}
