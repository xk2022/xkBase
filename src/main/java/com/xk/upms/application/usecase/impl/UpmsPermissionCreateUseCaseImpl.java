package com.xk.upms.application.usecase.impl;

import com.xk.adm.domain.service.AdmSystemService;
import com.xk.upms.application.model.UpmsPermissionResponseDTO;
import com.xk.upms.application.model.UpmsPermissionUpdateDTO;
import com.xk.upms.application.usecase.UpmsPermissionCreateUseCase;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.po.UpmsPermission;
import com.xk.upms.domain.service.UpmsPermissionService;
import com.xk.upms.domain.service.UpmsRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsPermissionCreateUseCaseImpl implements UpmsPermissionCreateUseCase {

    private final AdmSystemService admSystemService;

    private final UpmsRoleService upmsRoleService;

    private final UpmsPermissionService upmsPermissionService;


    @Override
    public void create(UUID systemUuid, Long roleId, UpmsPermissionUpdateDTO request) {
        Optional<UpmsPermissionBO> upmsPermissionBO  =upmsPermissionService.findBySystemId(systemUuid);


    }
}
