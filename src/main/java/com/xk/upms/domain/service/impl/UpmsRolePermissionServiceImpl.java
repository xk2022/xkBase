package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsRolePermissionRepository;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.service.UpmsRolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpmsRolePermissionServiceImpl implements UpmsRolePermissionService {

    private final UpmsRolePermissionRepository upmsRolePermissionRepository;

    @Override
    public List<UpmsRolePermission> findAll(UUID systemUuid, Long roleId) {
        return upmsRolePermissionRepository.findByIsDeletedFalseAndSystemUuidAndRoleId(systemUuid, roleId);
    }

}
