package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsRolePermission;

import java.util.List;
import java.util.UUID;

public interface UpmsRolePermissionService {

    List<UpmsRolePermission> findAll(UUID systemUuid, Long roleId);

}
