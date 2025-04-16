package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsRolePermissionBO;
import com.xk.upms.domain.model.po.UpmsRolePermission;

import java.util.List;
import java.util.UUID;

public interface UpmsRolePermissionService {

    List<UpmsRolePermission> findAll(UUID systemUuid, UUID roleUuid);

    UpmsRolePermissionBO save(UpmsRolePermissionBO upmsRolePermissionBO);

    void deleteAll(List<UpmsRolePermission> upmsRolePermissionBOs);

    void saveAll(List<UpmsRolePermission> upmsRolePermissionBOs);

}
